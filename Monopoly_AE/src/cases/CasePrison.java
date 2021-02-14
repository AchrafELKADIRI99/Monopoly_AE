package cases;


import java.util.Random;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;

/**
 * Crée l'action de la case Prison
*/
public class CasePrison extends Case {
	
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case
	 */
	public CasePrison() {
		super("Simple Visite", 0);
	}
	
	/**
	 * Méthode gérant tous les cas d'un joueur en prison : <br>
	 * <ul>
	 * <li>Si un joueur est resté 3 tours en prison, il doit payer 7500DH</li>
	 * <li>Si un joueur fait un double au lancé de dés, il peut sortir</li>
	 * <li>Si un joueur possède une carte Sortie de Prison et qu'il l'utilise, il se libère</li>
	 * </ul>
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		
		
		int lancé = plateau.des.lancerDes();
		int d1 = plateau.des.getDe1();
		int d2 = plateau.des.getDe2();
		
		if(joueur.getEstPrison() == true){
			
			if(fp != null) fp.afficherDes(plateau);
			
			
			if(getReponseQuestion()){
				joueur.retirerArgent(7500);
				reponseQuestion = false;
				joueur.setEstPrison(false);
				joueur.setToursEnPrison(1);
				plateau.deplacerJoueur(joueur, lancé);
				if(fp!= null) actionSortiePrison(plateau, joueur, fp);
			}
			else{
				if(joueur.getToursEnPrison() > 2) {
					joueur.retirerArgent(7500);
					joueur.setEstPrison(false);
					joueur.setToursEnPrison(1);
					plateau.deplacerJoueur(joueur, lancé);
					if(fp!=null) actionSortiePrison(plateau, joueur, fp);
				}
				else{
					if(d1 == d2){
						joueur.setEstPrison(false);
						joueur.setToursEnPrison(1);
						plateau.deplacerJoueur(joueur, lancé);
						if(fp!=null) actionSortiePrison(plateau, joueur, fp);
					}
					else{
						joueur.setToursEnPrison(joueur.getToursEnPrison() + 1);
					}
				}
			}
		}
		else{
			if(fp != null) fp.afficherMessage("Le joueur observe les criminels...");
		}
		
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(MainWindow fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(fp.getPartie().getPM().getJoueurActif().getEstPrison())
			fp.afficherFenetrePrison();
		else
			fp.getPartie().reprendrePartie();
	}
	
	@SuppressWarnings("static-access")
	public void actionSortiePrison(Boardmonop plateau, Playermonop joueur, MainWindow fp){
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plateau.getCase(joueur.getPosition()).fenetreAction(fp);
		fp.deplacerPion(joueur);
		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){ try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		
		plateau.getCase(joueur.getPosition()).actionCase(joueur, plateau, fp);
	}
	
	public static void main(String[] args){
		
		Playermonop j = new Playermonop("Yann", 0, 150000);
		Boardmonop p = new Boardmonop(4);
		
		CasePrison c = (CasePrison) p.getCase(10);
		
		j.setPosition(10);
		j.setEstPrison(true);
		c.actionCase(j, p, null);
		
		j.setEstPrison(true);
		c.setReponseQuestion(true);
		c.actionCase(j, p, null);
		
		c.actionCase(j, p, null);
		
	}
	
	/* ===========================
	   Méthodes abstraites de Case 
	   =========================== */
	
	@Override
	public Playermonop getProprietaire() {
		return null;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
		return 0;
	}

	@Override
	public int getPrixMaison() {
		return 0;
	}

	@Override
	public int getNbMaison() {
		return 0;
	}

	@Override
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(Playermonop j) {}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}

	@Override
	public String toString() {
		return "CasePrison [ " + super.toString() + ", reponseQuestion=" + reponseQuestion + "]";
	}
	
}
package cases;


import Controllers.Boardmonop;
import Controllers.Playermonop;
import model.Case;
import views.MainWindow;

/**
 * Cr�e l'action de la case Parc Gratuit
*/
public class CaseParcGratuit extends Case {
	
	/**
	 * Indique le nom de la case et initialise sa valeur
	 */
	public CaseParcGratuit() {
		super("Parc Gratuit", 0);
	}

	/**
	 * M�thode permettant � un joueur de r�cup�rer l'argent de la case Parc Gratuit, r�initialisant ensuite celle-ci � 0
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		
		if(fp!=null) 
			fp.afficherMessage(joueur.getNom() + " ramasse " + this.getPrix() + "DH du Parc Gratuit !");
		joueur.ajouterArgent(this.getPrix());
		this.setPrix(0);
	}
	
	public static void main(String[] args){
		
		Playermonop j = new Playermonop("Yann", 0, 150000);
		Boardmonop p = new Boardmonop(4);
		
		CaseParcGratuit c = (CaseParcGratuit) p.getCase(20);
		
		c.setPrix(45000);
		j.setPosition(20);
		c.actionCase(j, p, null);
		
	
	}

	@Override
	/**
	 * Reprend le cours de la partie
	 */
	public void fenetreAction(MainWindow fp) {
		fp.getPartie().reprendrePartie();
	}
	
	
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
		return false;
	}

	@Override
	public boolean getPeutMettreMaison() {
		return false;
	}

	@Override
	public void setProprietaire(Playermonop j) {}

	@Override
	public void setReponseQuestion(boolean b) {}

	@Override
	public String toString() {
		return "CaseParcGratuit [" + super.toString() + "]";
	}
	
}

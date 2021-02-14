package cases;



import model.Case;

import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;

/**
 * Cr�e l'action pour aller en prison
*@see Case
*/
public class CaseAllerPrison extends Case {
	
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case
	 */
	public CaseAllerPrison() {
		super("Aller en prison", 0);
	}
	
	/**
	 * M�thode permettant de g�rer l'action quand le joueur tombe sur la case AllerPrison
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
	
		
		if(joueur.getCarteSortiePrison()) {
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " utilise sa carte et �vite la prison !");
			joueur.setCarteSortiePrison(false);
			plateau.remettreCarteSortiePrisonDansPaquet();
		}
		else {
			joueur.setEstPrison(true);
			joueur.setPosition(10);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " est envoy� en prison!");
		}
	}

	@Override
	/**
	 * M�thode permettant de reprendre la partie
	 */
	public void fenetreAction(MainWindow fp) {
		fp.getPartie().reprendrePartie();
	}

	public static void main(String[] args){
		
		Playermonop j = new Playermonop("Yann", 0, 150000);
		Boardmonop p = new Boardmonop(4);
		
		CaseAllerPrison c = (CaseAllerPrison) p.getCase(30);
		j.setPosition(30);
		c.actionCase(j, p, null);
	}
	/* ===========================
	   M�thodes abstraites de Case 
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
		return "CaseAllerPrison [" + super.toString() + "]";
	}
	
}

package cases;


import io.Console;
import model.Case;

import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;

/**
 * Crée l'action pour aller en prison
*@author WEBERT MORVRANGE
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
	 * Méthode permettant de gérer l'action quand le joueur tombe sur la case AllerPrison
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		Console es = new Console();
		
		if(joueur.getCarteSortiePrison()) {
			es.println(" > " + joueur.getNom() + " utilise sa carte et évite la prison !");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " utilise sa carte et évite la prison !");
			joueur.setCarteSortiePrison(false);
			plateau.remettreCarteSortiePrisonDansPaquet();
		}
		else {
			joueur.setEstPrison(true);
			joueur.setPosition(10);
			es.println(" > " + joueur.getNom() + " est envoyé en prison!");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " est envoyé en prison!");
		}
	}

	@Override
	/**
	 * Méthode permettant de reprendre la partie
	 */
	public void fenetreAction(MainWindow fp) {
		fp.getPartie().reprendrePartie();
	}

	public static void main(String[] args){
		
		System.out.println("TEST DE LA CLASSE : CaseAllerPrison \n");
		Playermonop j = new Playermonop("Yann", 0, 150000);
		Boardmonop p = new Boardmonop(4);
		
		CaseAllerPrison c = (CaseAllerPrison) p.getCase(30);
		j.setPosition(30);
		System.out.println("\nLe joueur est sur la case "+ c.toString()+"\n");
		c.actionCase(j, p, null);
		System.out.println("\nMaintenant, le joueur est sur la case "+ p.getCase(j.getPosition()).toString()+" et est donc en prison\n");
		System.out.println(j.toString());
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
		return "CaseAllerPrison [" + super.toString() + "]";
	}
	
}

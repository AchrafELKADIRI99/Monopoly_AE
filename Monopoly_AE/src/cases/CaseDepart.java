package cases;


import io.Console;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import windows.MainWindow;


/**
 * Crée l'action d'une case départ
*@author WEBERT MORVRANGE
*/

public class CaseDepart extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseDepart() {
		super("Depart", 0);
	}
	
	/**
	 * Ajoute l'argent du passage sur la case
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		Console es = new Console();
		
		joueur.ajouterArgent(20000);
		es.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 20000€ supplémentaire !");
		if(fp!=null) fp.afficherMessage(joueur.getNom() + " s'arrête sur la case départ et reçoit 400€ !");
	}


	@Override
	/**
	 * Reprend la partie
	 */
	public void fenetreAction(MainWindow fp) {
		fp.getPartie().reprendrePartie();
	}

	
	public static void main(String[] args){
		
		System.out.println("TEST DE LA CLASSE : CaseDepart\n");
		Playermonop j = new Playermonop("Yann", 0, 1000);
		Boardmonop p = new Boardmonop(4);
		
		CaseDepart c = (CaseDepart) p.getCase(0);
		
		j.setPosition(38);
		System.out.println("\nLe joueur est sur la case " + p.getCase(j.getPosition()).toString()+"\n");
		p.deplacerJoueur(j, 2);
		c.actionCase(j, p, null);
		System.out.println("Le joueur possède : " + j.getArgent()+"€\n");
		
		j.setPosition(38);
		System.out.println("\nLe joueur est sur la case " + p.getCase(j.getPosition()).toString()+"\n");
		p.deplacerJoueur(j, 3);
		System.out.println("\nLe joueur est sur la case " + p.getCase(j.getPosition()).getNom()+"\n");
		System.out.println("Le joueur possède : " + j.getArgent()+"€\n");
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
		return "CaseDepart ["+super.toString()+"]";
	}
	
}

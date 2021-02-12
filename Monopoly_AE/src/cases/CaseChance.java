package cases;

import cartes.CartePayerArgent;
import cartes.CarteSortirPrison;

import io.Console;
import model.Carte;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;


/**
 * Crée l'action d'une case chance
*@author WEBERT MORVRANGE
*/
public class CaseChance extends Case {
	
	/**
	 * Indique le nom de la case
	 */
	public CaseChance() {
		super("Chance", 0);
	}

	@Override
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte chance
	 * @see Carte
	 */
	public void fenetreAction(MainWindow fp) {
		
		Console es = new Console();
		
		Carte carte = fp.getPartie().getPM().tirerCarteChance();
		es.println(" > " + fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteChance(carte.getNom(), carte.getDesc());
		
		fp.getPartie().pausePartie();
		while(fp.getPartie().getPausePartie() && !fp.getPartie().PARTIE_AUTO){ 
			try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} }
		
		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
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
		return "CaseChance [" + super.toString() + "]";
	}
	
	
	public static void main(String[] args) {
		
		System.out.println("TEST DE LA CLASSE : CaseChance");
		Playermonop j = new Playermonop("Yann", 0, 150000);
		Boardmonop p = new Boardmonop(4);
		CartePayerArgent payer = new CartePayerArgent("Amende", "Amende pour excès de vitesse : 2250Dh.", 2250);
		System.out.println(payer.toString());
		payer.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann perd 2250DH
		System.out.println(p.getCase(20).toString());
		
		CarteSortirPrison prison = new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)");
		System.out.println(prison.toString());
		prison.actionCarte(j, p, null);
		System.out.println(j.toString()); //Le joueur Yann possède la carte de sortie de prison
	}
}

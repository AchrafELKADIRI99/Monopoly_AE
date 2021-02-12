package cases;


import io.Console;
import model.Carte;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;


/**
 * Cr�e l'action d'une case communaut�
*@author WEBERT MORVRANGE
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communaut�", 0);
	}

	@Override
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communaut�
	 * @see Carte
	 */
	public void fenetreAction(MainWindow fp) {
		
Console es = new Console();
		
		Carte carte = fp.getPartie().getPM().tirerCarteCommunaut�();
		es.println(" > " + fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		
		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteCommunaut�(carte.getNom(), carte.getDesc());
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
		return "CaseCommunaute [" + super.toString() + "]";
	}
	
}

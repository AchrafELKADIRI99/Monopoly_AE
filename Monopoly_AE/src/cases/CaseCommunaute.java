package cases;


import Controllers.Boardmonop;
import Controllers.Playermonop;
import model.Carte;
import model.Case;
import views.MainWindow;


/**
 * Crée l'action d'une case communauté
*/

public class CaseCommunaute extends Case {

	/**
	 * Indique le nom de la case
	 */
	public CaseCommunaute() {
		super("Communauté", 0);
	}

	@Override
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Permet de tirer et afficher une carte communauté
	 * @see Carte
	 */
	public void fenetreAction(MainWindow fp) {
		
		
		Carte carte = fp.getPartie().getPM().tirerCarteCommunauté();
		fp.afficherMessage(fp.getPartie().getPM().getJoueurActif().getNom() + " tire la carte "+carte.getNom());
		
		carte.actionCarte(fp.getPartie().getPM().getJoueurActif(), fp.getPartie().getPM(), fp);
		
		if(fp.getPartie().PARTIE_AUTO)
			fp.getPartie().reprendrePartie();
		else
			fp.afficherFenetreCarteCommunauté(carte.getNom(), carte.getDesc());
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
		return "CaseCommunaute [" + super.toString() + "]";
	}
	
}

package cases;


import io.Console;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import windows.MainWindow;

/**
 * Crée l'action d'une case impôt
*@author WEBERT MORVRANGE
*/
public class CaseImpots extends Case {

	/**
	 * Indique le nom ainsi que le prix à payer d'une case Impôt
	 * @param nom String
	 * @param prix int
	 */
	public CaseImpots(String nom, int valeur) {
		super(nom, valeur);
	}
	
	/**
	 * Méthode retirant de l'argent à un joueur et l'ajoutant au Parc Gratuit
	 * @see CaseParcGratuit
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		Console es = new Console();
		
		es.println(" > " + joueur.getNom() + " dépose " + this.getPrix() + "€ au Parc Gratuit.");
		if(fp != null)
			fp.afficherMessage(joueur.getNom() + " dépose " + this.getPrix() + "€ au Parc Gratuit.");
		
		joueur.retirerArgent(this.getPrix());
		
		int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
		plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
	}
	
	public static void main(String[] args){
		
		System.out.println("TEST DE LA CLASSE : CaseImpots");
		Playermonop j = new Playermonop("Yann", 0, 1000);
		Boardmonop p = new Boardmonop(4);
		
		CaseImpots c = (CaseImpots) p.getCase(4);
		j.setPosition(4);
		c.actionCase(j, p, null);
		System.out.println(c.toString());
		System.out.println(p.getCase(20).toString());
		System.out.println(j.toString());
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
	
}

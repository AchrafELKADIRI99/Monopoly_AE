package cartes;


import io.Console;
import model.Carte;
import model2.Boardmonop;
import model2.Playermonop;
import windows.MainWindow;

/**
 * Type de {@link Carte} pour la sortie de prison. Cette carte est conservée par le joueur.
 * @see Carte
 */
public class CarteSortirPrison extends Carte {

	/**
	 * Unique constructeur de la clase {@link CarteSortirPrison}.
	 * @param titre String
	 * @param description String
	 */
	public CarteSortirPrison(String titre, String description) {
		super(titre, description);
	}

	/**
	 * Méthode réalisant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */
	@Override
	public void actionCarte(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		Console es = new Console();
		es.println(" > "+joueur.getNom()+" recoit la carte 'Sortir de prison' !");
		if(fp != null)
			fp.afficherMessage(joueur.getNom()+" recoit la carte 'Sortir de prison' !");
		
		joueur.setCarteSortiePrison(true);
	}
	@Override
	public String toString() {
		return "CarteSortirPrison [" + super.toString() +"]";
	}
}

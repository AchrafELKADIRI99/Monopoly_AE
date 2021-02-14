package cartes;


import model.Carte;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;

/**
 * Type de {@link Carte} pour les réceptions de payements des joueurs.<br><br>
 * &nbsp; <b>Liste des champs :</b>
 * <ul><li><b>montant</b> : int - montant à ajouter au joueur.</li></ul>
 * @see Carte
 */
public class CarteRecevoirArgent extends Carte {
	
	private int montant;
	
	/**
	 * Unique constructeur de la clase {@link CarteRecevoirArgent}.
	 * @param titre String
	 * @param description String
	 * @param montant int
	 */
	public CarteRecevoirArgent(String titre, String description, int montant) {
		super(titre, description);
		this.montant = montant;
	}

	/**
	 * Méthode réalisant l'action de la carte. 
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 */
	@Override
	public void actionCarte(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		
		if(getNom().equals("Anniversaire")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).retirerArgent(1500);
					joueur.ajouterArgent(1500);
				}
			}
			if(fp != null)
				fp.afficherMessage(joueur.getNom()+" reçoit 1500DHs de chaque joueur.");
		}
		else {
			joueur.ajouterArgent(montant);
		}
	}

	@Override
	public String toString() {
		return "CarteRecevoirArgent [" + super.toString() + " montant= " + montant + "]";
	}
	
}

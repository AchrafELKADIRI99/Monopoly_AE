package cartes;

import model.Carte;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;


/**
 * Type de {@link Carte} pour les payements des joueurs.<br><br>
 * &nbsp; <b>Liste des champs :</b>
 * <ul><li><b>montant</b> : int - montant à retirer au joueur.</li></ul>
 * @see Carte
 */
public class CartePayerArgent extends Carte {
	
	private int montant;
	
	/**
	 * Unique constructeur de la clase {@link CartePayerArgent}.
	 * @param titre String
	 * @param description String
	 * @param montant int
	 */
	public CartePayerArgent(String titre, String description, int montant) {
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
		
		
		if(getNom().equals("Président du conseil d'administration")) {
			for(int i=0; i<plateau.getNbJoueurs(); i++) {
				if(plateau.getJoueur(i) != joueur && !plateau.getJoueur(i).getEstBanqueroute()) {
					plateau.getJoueur(i).ajouterArgent(7500);
					joueur.retirerArgent(7500);
				}
			}
			if(fp!=null)
				fp.afficherMessage(joueur.getNom()+" verse 7500DHs à chaque joueur.");
		}
		else {
			joueur.retirerArgent(montant);
			plateau.getCase(20).setPrix(plateau.getCase(20).getPrix() + montant);
			if(fp !=null)
				fp.afficherMessage(joueur.getNom()+" dépose "+montant+"DHs au parc gratuit");
		}
	}
	public int getMontant(){
		return this.montant;
	}

	@Override
	public String toString() {
		return "CartePayerArgent ["+ super.toString() +", montant= " + montant + "]";
	}
}

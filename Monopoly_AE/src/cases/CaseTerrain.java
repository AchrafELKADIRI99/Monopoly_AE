package cases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Controllers.Boardmonop;
import Controllers.Playermonop;
import views.MainWindow;
import model.Case;

/**
 * Crée l'action de la case terrain
*/
	

public class CaseTerrain extends Case {

	private Playermonop proprietaire;
	private ArrayList<Integer> loyer = new ArrayList<Integer>();
	private String couleur;
	private int prixMaison;
	private int nbMaison = 0;
	private boolean peutMettreMaison = false;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom, le prix du terrain, la liste de ses loyers, le prix d'une maison, le nombre de maison, et la couleur que possède un terrain
	 * @param nom String
	 * @param montant int
	 * @param loyer ArrayList
	 * @param prixMaison int
	 * @param nbMaison int
	 * @param couleur String
	 */
	public CaseTerrain(String nom, int valeur, ArrayList<Integer> loyer, int prixMaison, int nbMaison, String couleur) {
		super(nom, valeur);
		this.couleur = couleur;
		this.loyer = loyer;
		this.prixMaison = prixMaison;
		this.nbMaison = nbMaison;
	}
	
	/**
	 * Méthode permettant de vérifier un terrain : <br>
	 * <ul>
	 * <li>Si personne ne possède le terrain, un joueur peut l'acheter</li>
	 * <li>Si un joueur tombe sur un terrain appartenant à un autre joueur, il paye un loyer au joueur qui le possède</li>
	 * <li>si un joueur tombe sur un de ses terrains il ne se passe rien, mais peut acheter des maisons</li>
	 * </ul>
	 * @throws notEnoughMoneyException 
	 * @see jeudeplateau.Joueur
	 * @see Case
	 */
	
	@SuppressWarnings({ "unused", "static-access" })
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter ce terrain.");
			}
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fp);
		
		else {
			fp.afficherMessage(joueur.getNom() + " est sur son propre terrain");
			
			if(this.getPeutMettreMaison() && fp.getPartie().PARTIE_AUTO) {
				this.ajouterMaison(fp);
				fp.setMaison(this);
			}
		}
	}
	
	
	public boolean acheterTerrain(Playermonop joueur, MainWindow fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterTerrain(this);
			joueur.retirerArgent(this.getPrix());
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "DH");
			return true;
		}
	}
	
	public void payerLoyer(Playermonop joueur, MainWindow fp) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			joueur.retirerArgent(getLoyer());
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(getLoyer());
				beneficiaire = this.getProprietaire().getNom();
			}
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + getLoyer() + "DH à " + beneficiaire);
		}
		else {
			if(fp!=null) fp.afficherMessage("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}
	
	/**
	 * Permet l'ajout d'une maison sur un terrain
	 */
	public void ajouterMaison(MainWindow fp){
		
		nbMaison++;
		proprietaire.retirerArgent(this.getPrixMaison());
		
		if(fp!=null) fp.afficherMessage(" > " + proprietaire.getNom() + " a posé une maison sur "+getNom()+" !");
	}
	
	/**
	 * Méthode permettant l'affichage d'une fenêtre pour l'achat d'un terrain, et reprenant le cours de la partie
	 */
	@SuppressWarnings("static-access")
	public void fenetreAction(MainWindow fp) {
		
		if(fp.getPartie().PARTIE_AUTO) {
			Random rand = new Random();
			if(rand.nextBoolean())
				reponseQuestion = true;
			fp.getPartie().reprendrePartie();
		}
		else if(this.getProprietaire() == null)
			fp.afficherFenetreAchatTerrain();
		else
			fp.getPartie().reprendrePartie();
	}
	
	
	/* ===========================
	   Méthodes abstraites de Case 
	   =========================== */
	
	public boolean getPeutMettreMaison() {
		if(proprietaire.getListeCouleur().contains(this.getCouleur())){

			ArrayList<Case> couleur = new ArrayList<Case>();
			for(Case c: proprietaire.getListeTerrains())
				if(c.getCouleur() == this.getCouleur() && c != this)
					couleur.add(c);
			
			this.peutMettreMaison = true;
			for(Case c:couleur) {
				if(!(this.getNbMaison() == c.getNbMaison() || this.getNbMaison() == c.getNbMaison() - 1))
					this.peutMettreMaison = false;
			}
			
			if(proprietaire.getArgent() < this.getPrixMaison()) {
				this.peutMettreMaison = false;
			}
			if(getNbMaison() == 4) {
				this.peutMettreMaison = false;
			}
		}
		else
			this.peutMettreMaison = false;
		
		return this.peutMettreMaison;
	}

	@Override
	public int getLoyer() {
		int aPayer = loyer.get(getNbMaison());
		if(proprietaire.getListeCouleur().contains(this.getCouleur()) && getNbMaison() == 0)
			aPayer*=2; // loyer double si le joueur possède tous les terrains d'une couleur mais sans maison.
		
		return aPayer;
	}

	@Override
	public String getCouleur() {
		return couleur;
	}
	
	@Override
	public int getPrixMaison() {
		return prixMaison;
	}

	@Override
	public int getNbMaison() {
		return nbMaison;
	}

	@Override
	public Playermonop getProprietaire() {
		return proprietaire;
	}

	@Override
	public boolean getReponseQuestion() {
		return reponseQuestion;
	}

	@Override
	public void setProprietaire(Playermonop j) {
		this.proprietaire = j;
	}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}
	
	@Override
	public String toString() {
		return "CaseTerrain ["+ super.toString() +", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + ", couleur=" + couleur + ", loyer=" + loyer
				+ ", prixMaison=" + prixMaison + ", peutMettreMaison=" + peutMettreMaison + ", nbMaison=" + nbMaison + "]";
	}

	public static void main(String[] args) {
		
		
		
		CaseTerrain c = new CaseTerrain("Avenue de la République", 120, new ArrayList<Integer>(Arrays.asList(8, 40, 100, 300, 450, 600)), 50, 0, "turquoise");
		Playermonop j1 = new Playermonop("Yann", 0, 150000);
		Playermonop j2 = new Playermonop("Benoit", 1, 150000);
		
		
		c.acheterTerrain(j1, null);
		
		
		c.payerLoyer(j2, null);
		
		c.ajouterMaison(null);
		c.ajouterMaison(null);
		c.ajouterMaison(null);
		
		c.payerLoyer(j2, null);
		
	}
	
}

package cases;

import java.util.Random;

import io.Console;
import model2.Boardmonop;
import model2.Playermonop;
import views.MainWindow;
import model.Case;

/**
 * Crée l'action d'une case gare
*@author WEBERT MORVRANGE
*/

public class CaseGare extends Case {

	private Playermonop proprietaire;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom et ajoute le prix d'une gare
	 * @param nom String
	 */
	public CaseGare(String nom) {
		super(nom, 30000);
	}

	@Override
	/**
	 * Méthode gérant l'appropriation d'une gare à un joueur <br />
	 * Gère le changement du loyer en fonction du nombre de gare possédé par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		Console es = new Console();
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				es.println(" > " + joueur.getNom() + " décide de ne pas acheter cette gare.");
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette gare.");
			}
		}
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, fp);

		else {
			es.println(" > " + joueur.getNom() + " est dans sa propre gare.");
			fp.afficherMessage(joueur.getNom() + " est dans sa propre gare.");
		}
	}
	
	
	public boolean acheterTerrain(Playermonop joueur, MainWindow fp) {
		if((joueur.getArgent() - this.getPrix()) <= 0) {
			System.out.println("Vous n'avez pas assez d'argent!");
			return false;
		}
		else {
			setProprietaire(joueur);
			joueur.ajouterTerrain(this);
			joueur.retirerArgent(this.getPrix());
			joueur.setNbGares(joueur.getNbGares() + 1);
			
			System.out.println(" > " + joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "DH");
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
			System.out.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "DH à " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + getLoyer() + "DH à " + beneficiaire);
		}
		else {
			System.out.println(" > Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fp!=null) fp.afficherMessage("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}
	

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre d'achat de terrain
	 */
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
	
	@Override
	public Playermonop getProprietaire() {
		return proprietaire;
	}

	@Override
	public String getCouleur() {
		return null;
	}

	@Override
	public int getLoyer() {
		return proprietaire != null ? 50 * this.getProprietaire().getNbGares() : 0;
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
	public void setProprietaire(Playermonop j) {
		this.proprietaire = j;
	}

	@Override
	public void setReponseQuestion(boolean b) {
		this.reponseQuestion = b;
	}
	
	@Override
	public String toString() {
		return "CaseGare [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}
	
	public static void main(String[] args) {
		
		Console es = new Console();
		es.println("TEST DE LA CLASSE : CaseGare");

		Playermonop j1 = new Playermonop("Yann", 0, 150000);
		Playermonop j2 = new Playermonop("Benoit", 1, 150000);
		Boardmonop pm = new Boardmonop(2);
		es.println(j1.toString());
		es.println(j2.toString()+"\n");
		
		CaseGare c = (CaseGare) pm.getCase(5);
		c.acheterTerrain(j1, null);

		es.println("== Nombres de gares de " + j1.getNom() + " : " + j1.getNbGares());
		
		c.payerLoyer(j2, null);
		es.println("");
		
		c = (CaseGare) pm.getCase(15);
		c.acheterTerrain(j1, null);
		c = (CaseGare) pm.getCase(25);
		c.acheterTerrain(j1, null);
		es.println("== Nombres de gares de " + j1.getNom() + " : " + j1.getNbGares());

		c.payerLoyer(j2, null);
		
		es.println("\n" + j1.toString());
		es.println(j2.toString());
	}

}

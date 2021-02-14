package cases;

import java.util.Random;

import Controllers.Boardmonop;
import Controllers.Playermonop;
import model.Case;
import views.MainWindow;


/**
 * Crée l'action de la case Service Public
*/

public class CaseServicePublic extends Case {

	private Playermonop proprietaire;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case et attribut un prix
	 * @param nom String
	 */
	public CaseServicePublic(String nom) {
		super(nom, 22500);
	}

	@Override
	/**
	 * Méthode gérant l'appropriation d'un service public à un joueur <br />
	 * Gère le changement du loyer en fonction du nombre de service public possédé par un joueur
	 * @see Joueur
	 * @see Case
	 */
	public void actionCase(Playermonop joueur, Boardmonop plateau, MainWindow fp) {
		
		
		if(this.getProprietaire() == null) {
			if(getReponseQuestion()) {
				if(acheterTerrain(joueur, fp))
					fp.setMarqueurProprietaire(joueur, this);
			}
			else {
				fp.afficherMessage(joueur.getNom() + " décide de ne pas acheter cette compagnie.");
			}
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, plateau, fp);
			
		else {
			if(fp!=null) fp.afficherMessage("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
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
			joueur.setNbServices(joueur.getNbServices() + 1);
			
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "DH");
			return true;
		}
	}
	
	public void payerLoyer(Playermonop joueur, Boardmonop pm, MainWindow fp) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			
			int loyer = pm.des.lancerDes()*100;
			if(fp!=null) {
				fp.effacerDes();
				fp.afficherDes(pm);
			}
			
			if(this.getProprietaire().getNbServices() == 2) loyer*=10;
			else loyer*=4;
			
			joueur.retirerArgent(loyer);
			
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(loyer);
				beneficiaire = this.getProprietaire().getNom();
			}
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + loyer + "DH à " + beneficiaire);
		}
		else {
			if(fp!=null) fp.afficherMessage("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fenêtre pour l'achat de la case et reprend le cours de la partie
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
		return "CaseServicePublic [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
	}
	
	
	public static void main(String[] args) {
		
		
		Playermonop j1 = new Playermonop("Yann", 0, 150000);
		Playermonop j2 = new Playermonop("Benoit", 1, 150000);
		Boardmonop pm = new Boardmonop(2);
		
		CaseServicePublic c = (CaseServicePublic) pm.getCase(12);
		c.acheterTerrain(j1, null);

		
		c.payerLoyer(j2, pm, null);
		
		c = (CaseServicePublic) pm.getCase(28);
		c.acheterTerrain(j1, null);

		c.payerLoyer(j2, pm, null);

	}

}

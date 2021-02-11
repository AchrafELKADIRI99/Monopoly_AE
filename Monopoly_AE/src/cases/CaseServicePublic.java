package cases;

import java.util.Random;
import model.Case;
import model2.Boardmonop;
import model2.Playermonop;
import windows.MainWindow;
import io.Console;


/**
 * Cr�e l'action de la case Service Public
*@author WEBERT MORVRANGE
*/

public class CaseServicePublic extends Case {

	private Playermonop proprietaire;
	private boolean reponseQuestion = false;
	
	/**
	 * Indique le nom de la case et attribut un prix
	 * @param nom String
	 */
	public CaseServicePublic(String nom) {
		super(nom, 150);
	}

	@Override
	/**
	 * M�thode g�rant l'appropriation d'un service public � un joueur <br />
	 * G�re le changement du loyer en fonction du nombre de service public poss�d� par un joueur
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
				es.println(" > " + joueur.getNom() + " d�cide de ne pas acheter cette compagnie.");
				fp.afficherMessage(joueur.getNom() + " d�cide de ne pas acheter cette compagnie.");
			}
		}
		
		else if(this.getProprietaire() != joueur)
			payerLoyer(joueur, plateau, fp);
			
		else {
			es.println(" > " + joueur.getNom() + " poss�de la compagnie.");
			if(fp!=null) fp.afficherMessage("Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
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
			joueur.setNbServices(joueur.getNbServices() + 1);
			
			System.out.println(" > " + joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " ach�te " + this.getNom() + " pour " + this.getPrix() + "�");
			return true;
		}
	}
	
	public void payerLoyer(Playermonop joueur, Boardmonop pm, MainWindow fp) {
		String beneficiaire = "la Banque";
		
		if(!this.getProprietaire().getEstPrison()) {
			
			int loyer = pm.des.lancerDes();
			if(fp!=null) {
				fp.effacerDes();
				fp.afficherDes(pm);
			}
			
			if(this.getProprietaire().getNbServices() == 2) loyer*=10;
			else loyer*=4;
			
			System.out.println(" > " + joueur.getNom() + " lance les d�s... [" + pm.des.getDe1() + "][" + pm.des.getDe2() + "]... et obtient un " + pm.des.getDes());
			joueur.retirerArgent(loyer);
			
			if(!this.getProprietaire().getEstBanqueroute()) {
				this.getProprietaire().ajouterArgent(loyer);
				beneficiaire = this.getProprietaire().getNom();
			}
			System.out.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "� � " + beneficiaire);
			if(fp!=null) fp.afficherMessage(joueur.getNom() + " paye un loyer de " + loyer + "� � " + beneficiaire);
		}
		else {
			System.out.println(" > Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
			if(fp!=null) fp.afficherMessage("Le propri�taire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	/**
	 * Affiche une fen�tre pour l'achat de la case et reprend le cours de la partie
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
	   M�thodes abstraites de Case 
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
		
		Console es = new Console();
		es.println("TEST DE LA CLASSE : CaseServicePublic");
		
		Playermonop j1 = new Playermonop("Yann", 0, 1000);
		Playermonop j2 = new Playermonop("Benoit", 1, 1000);
		Boardmonop pm = new Boardmonop(2);
		es.println(j1.toString()+"\n");
		
		CaseServicePublic c = (CaseServicePublic) pm.getCase(12);
		c.acheterTerrain(j1, null);

		es.println("== Nombres de SP de " + j1.getNom() + " : " + j1.getNbServices());
		
		c.payerLoyer(j2, pm, null);
		es.println("");
		
		c = (CaseServicePublic) pm.getCase(28);
		c.acheterTerrain(j1, null);
		es.println("== Nombres de SP de " + j1.getNom() + " : " + j1.getNbServices());

		c.payerLoyer(j2, pm, null);

		es.println("\n" + j1.toString());
	}

}

package model2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import cartes.CarteDeplacement;
import cartes.CartePayerArgent;
import cartes.CarteRecevoirArgent;
import cartes.CarteSortirPrison;
import model.Carte;
import cases.CaseAllerPrison;
import cases.CaseChance;
import cases.CaseCommunaute;
import cases.CaseDepart;
import cases.CaseGare;
import cases.CaseImpots;
import cases.CaseParcGratuit;
import cases.CasePrison;
import cases.CaseServicePublic;
import cases.CaseTerrain;
import model.Case;
import model.Board;
import model.Player;


/**
 * Initialise le plateau du monopoly avec toutes ses cases
*@author WEBERT MORVRANGE
*/
public class Boardmonop extends Board {

	/**
	 * @see JoueurMonopoly
	 */
	private ArrayList<Playermonop> joueurs = new ArrayList<Playermonop>();
	/**
	 * @see Carte
	 */
	private ArrayList<Carte> chance = new ArrayList<Carte>();
	private ArrayList<Carte> communauté = new ArrayList<Carte>();
	
	/**
	 * Crée un plateau avec un nombre de joueur
	 * @param nombreDeJoueurs
	 * @see Board.Plateau
	 * @see CaseDepart
	 * @see CaseCommunaute
	 * @see CaseImpots
	 * @see CaseGare
	 * @see CaseChance
	 * @see CasePrison
	 * @see CaseServicePublic
	 * @see CaseParcGratuit
	 * @see CaseAllerPrison
	 * @see CaseTerrain
	 * @see CarteDeplacement
	 * @see CartePayerArgent
	 * @see CarteRecevoirArgent
	 * @see CarteSortirPrison
	 */
	public Boardmonop(int nombreDeJoueurs) {
		super(nombreDeJoueurs, 40);
		
		
		/* INITIALISATION DES JOUEURS */
		for(int i = 0; i < this.getNbJoueurs(); i++) {
			this.joueurs.add(new Playermonop("Joueur"+(i+1), i, 150000));
		}
		
		/* INITIALISATION DES CASES*/
		setCase(0, new CaseDepart());
		setCase(2, new CaseCommunaute());
		setCase(4, new CaseImpots("Impots sur le revenu", 30000));
		setCase(5, new CaseGare("Gare SUPRATOUR"));
		setCase(7, new CaseChance());
		setCase(10, new CasePrison());
		setCase(12, new CaseServicePublic("Compagnie d'électricité"));
		setCase(15, new CaseGare("Gare BAB DOUKKALA"));
		setCase(17, new CaseCommunaute());
		setCase(20, new CaseParcGratuit());
		setCase(22, new CaseChance());
		setCase(25, new CaseGare("Gare CTM"));
		setCase(28, new CaseServicePublic("Compagnie des eaux"));
		setCase(30, new CaseAllerPrison());
		setCase(33, new CaseCommunaute());
		setCase(35, new CaseGare("Gare ONCF GUELIZ"));
		setCase(36, new CaseChance());
		setCase(38, new CaseImpots("Taxe de Luxe", 15000));
		
		/* INITIALISATION DES TERRAINS */
		setCase(1, new CaseTerrain("DOHA", 9000, new ArrayList<Integer>(Arrays.asList(300, 1500, 4500, 13500, 24000, 37500)), 75000, 0, "brun"));
		setCase(3, new CaseTerrain("SAADA", 9000, new ArrayList<Integer>(Arrays.asList(600, 3000, 9000, 27500, 48000, 67500)), 75000, 0, "brun"));
		
		setCase(6, new CaseTerrain("AZOUZIA", 15000, new ArrayList<Integer>(Arrays.asList(900, 4500, 13500, 40500, 60000, 82500)), 75000, 0, "turquoise"));
		setCase(8, new CaseTerrain("JNANE AWRAD", 15000, new ArrayList<Integer>(Arrays.asList(900, 4500, 13500, 40500, 60000, 82500)), 75000, 0, "turquoise"));
		setCase(9, new CaseTerrain("HAY ZITOUN", 18000, new ArrayList<Integer>(Arrays.asList(1200, 6000, 15000, 45000, 67500, 90000)), 75000, 0, "turquoise"));
		
		setCase(11, new CaseTerrain("MASSIRA 1", 21000, new ArrayList<Integer>(Arrays.asList(1500, 7500, 22500, 67500, 93750, 112500)), 15000, 0, "mauve"));
		setCase(13, new CaseTerrain("MASSIRA 2", 21000, new ArrayList<Integer>(Arrays.asList(1500, 7500, 22500, 67500, 93750, 112500)), 15000, 0, "mauve"));
		setCase(14, new CaseTerrain("MASSIRA 3", 24000, new ArrayList<Integer>(Arrays.asList(1800, 9000, 27000, 75000, 105000, 135000)), 15000, 0, "mauve"));
		
		setCase(16, new CaseTerrain("BAB DOUKKALA", 27000, new ArrayList<Integer>(Arrays.asList(2100, 10500, 3000, 82500, 112500, 142500)), 15000, 0, "orange"));
		setCase(18, new CaseTerrain("SOKOMA", 27000, new ArrayList<Integer>(Arrays.asList(2100, 10500, 30000, 82500, 112500, 142500)), 15000, 0, "orange"));
		setCase(19, new CaseTerrain("SIDI GHANEM", 30000, new ArrayList<Integer>(Arrays.asList(2400, 12000, 33000, 90000, 120000, 150000)), 15000, 0, "orange"));
		
		setCase(21, new CaseTerrain("AIN ITTI", 33000, new ArrayList<Integer>(Arrays.asList(2700, 13500, 37500, 105000, 131250, 157500)), 22500, 0, "rouge"));
		setCase(23, new CaseTerrain("M'HAMID", 33000, new ArrayList<Integer>(Arrays.asList(2700, 13500, 37500, 105000, 131250, 157500)), 22500, 0, "rouge"));
		setCase(24, new CaseTerrain("BIN LKCHALI", 36000, new ArrayList<Integer>(Arrays.asList(3000, 15000, 45000, 112500, 138750, 165000)), 22500, 0, "rouge"));
		
		setCase(26, new CaseTerrain("AIN MEZZOUAR", 39000, new ArrayList<Integer>(Arrays.asList(3300, 16500, 49500, 120000, 146250, 172500)), 22500, 0, "jaune"));
		setCase(27, new CaseTerrain("AZLI", 39000, new ArrayList<Integer>(Arrays.asList(3300, 16500, 49500, 120000, 146250, 172500)), 22500, 0, "jaune"));
		setCase(29, new CaseTerrain("IZDIHAR", 42000, new ArrayList<Integer>(Arrays.asList(3600, 18000, 54000, 127500, 153750, 180000)), 22500, 0, "jaune"));
		
		setCase(31, new CaseTerrain("Avenue ALLAL EL FASSI", 45000, new ArrayList<Integer>(Arrays.asList(3900, 19500, 58500, 135000, 165000, 191250)), 30000, 0, "vert"));
		setCase(32, new CaseTerrain("L'HIVERNAGE", 45000, new ArrayList<Integer>(Arrays.asList(3900, 19500, 58500, 135000, 165000, 191250)), 30000, 0, "vert"));
		setCase(34, new CaseTerrain("GUELIZ", 48000, new ArrayList<Integer>(Arrays.asList(4200, 22500, 67500, 150000, 180000, 210000)), 30000, 0, "vert"));
		
		setCase(37, new CaseTerrain("TARGA", 52500, new ArrayList<Integer>(Arrays.asList(5250, 26250, 75000, 165000, 195000, 225000)), 30000, 0, "bleu"));
		setCase(39, new CaseTerrain("NAKHIL", 60000, new ArrayList<Integer>(Arrays.asList(7500, 30000, 90000, 210000, 255000, 300000)), 30000, 0, "bleu"));
		
		
		/* INITIALISATION DES CARTES CHANCES */
		/*chance.add(new CartePayerArgent("Amende", "Amende pour excès de vitesse : 2250DH.", 2250));
		chance.add(new CartePayerArgent("Président du conseil d'administration", "Vous avez été élu président du conseil d'administration. \nVersez 7500DH à chaque joueur.", 7500));
		chance.add(new CartePayerArgent("Lanuel", "Vous avez manqué de respect à M. Lanuel. \nVersez 7500DH de dédommagement.", 7500));
		
		chance.add(new CarteRecevoirArgent("Versement", "La banque vous verse un dividende de 7500DH.", 750));
		chance.add(new CarteRecevoirArgent("Gain", "Vos terrains vous rapportent. Touchez 22500DH.", 22500));
		chance.add(new CarteRecevoirArgent("Mots croisés", "Vous avez gagné le prix de mots-croisés ! \nRecevez 15000DH.", 15000));*/
		
		chance.add(new CarteDeplacement("Case Départ", "Avancez jusqu'à la case départ. \n(Recevez 20000DH)", 0, false));
		chance.add(new CarteDeplacement("NAKHIL", "Rendez-vous à NAKHIL.", 39, false));
		chance.add(new CarteDeplacement("BIN LKCHALI", "Rendez-vous à BIN LKCHALI. \nSi vous passez par la case départ, recevez 20000DH.", 24, false));
		chance.add(new CarteDeplacement("MASSIRA 1", "Avancez à MASSIRA 1. \nSi vous passez par la case départ, 20000DH.", 11, false));
		chance.add(new CarteDeplacement("Gare BAB DOUKKALA", "Avancez à la Gare BAB DOUKKALA. \nSi vous passez par la case départ, recevez 20000DH.", 15, false));
		chance.add(new CarteDeplacement("Reculez", "Reculez de 3 cases.", -3, true));
		chance.add(new CarteDeplacement("Nv Depart", "Le joueur déménage et prend un \nnouveau départ au Technopole.", 0, false));

		/*chance.add(new CarteDeplacement("Prison", "Allez en prison. \nAvancez tout droit en prison. \nNe passez pas par la case départ, ne recevez pas 30000DH.", 10, false));
		chance.add(new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)"));
		*/
		Collections.shuffle(chance); //Mélange des cartes
		
		
		/* INITIALISATION DES CARTES COMMUNAUTÉS */
		communauté.add(new CartePayerArgent("Frais de scolarité", "Payez 22500DH de frais de scolarité.", 22500));
		communauté.add(new CartePayerArgent("Frais d'hospitalisation", "Payez 15000DH de frais d'hospitalisation.", 15000));
		communauté.add(new CartePayerArgent("Médecin", "Visite chez le médecin : payez 7500DH.", 7500));
		
		communauté.add(new CarteRecevoirArgent("Remboursement", "Les impôts vous remboursent 3000DH.", 3000));
		communauté.add(new CarteRecevoirArgent("Assurance vie", "Votre assurance vie vous rapporte 22500DH.", 22500));
		communauté.add(new CarteRecevoirArgent("Anniversaire", "C'est votre anniversaire ! \nChaque joueur doit vous donner 1500DH.", 1500));
		communauté.add(new CarteRecevoirArgent("Commission d'expert", "Commission d'expert immobilier. \nRecevez 3750.", 3750));
		communauté.add(new CarteRecevoirArgent("Prix de beauté", "Vous avez gagné le deuxième prix de beauté. \nRecevez 3000DH.", 3000));
		communauté.add(new CarteRecevoirArgent("Stock", "La vente de votre stock vous rapporte 7500DH.", 7500));
		communauté.add(new CarteRecevoirArgent("Héritage", "Vous héritez de 15000DH.", 15000));
		communauté.add(new CarteRecevoirArgent("Placement", "Votre placement vous rapporte. \nRecevez 15000DH.", 15000));
		communauté.add(new CarteRecevoirArgent("Erreur de la Banque", "Erreur de la Banque en votre faveur. \nRecevez 30000DH.", 30000));
		
		communauté.add(new CarteDeplacement("Prison", "Allez en prison. \nAvancez tout droit en prison. \nNe passez pas par la case départ, ne recevez pas 20000DH.", 10, false));
		communauté.add(new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)"));
		
		Collections.shuffle(communauté); //Mélange des cartes
	}
	
	/* PARTIE JOUEUR */
	
	/**
	 * Permet de déplacer un joueur d'un certain nombre de cases
	 * @param joueur
	 * @param nombreDeCases
	 * @see Joueur
	 * @see JoueurMonopoly
	 * @see Board.Plateau
	 */
	public void deplacerJoueur(Playermonop joueur, int nombreDeCases) {
		int pos;
		
		if((joueur.getPosition() + nombreDeCases) >= getNbCases()) {
			pos = (joueur.getPosition() + nombreDeCases) % getNbCases();
			System.out.println(" > " + joueur.getNom() + " passe par la case départ et gagne 20000DH !");
			joueur.ajouterArgent(20000);
		}
		else
			pos = joueur.getPosition() + nombreDeCases;
		
		if(!joueur.getEstBanqueroute()) {
			joueur.setPosition(pos);
		}
	}

	/**
	 * Renvoie au joueur qui doit jouer son tour
	 * @return joueur
	 */
	public Playermonop getJoueurActif() {
		return this.joueurs.get(getJoueurActifID());
	}
	
	/**
	 * Permet de changer de joueur en fonction de l'indice i
	 * @param i
	 * @return joueur
	 */
	public Playermonop getJoueur(int i) {
		return this.joueurs.get(i);
	}
	
	/**
	 * Renvoie le joueur vainqueur
	 * @return joueur
	 */
	@Override
	public Player estVainqueur() {
		int res = 0;
		for(int i=0; i<joueurs.size(); i++) {
			if(getJoueur(i).getArgent() > getJoueur(res).getArgent())
				res = i;
		}
		return getJoueur(res);
	}
	
	/* PARTIE CASE */
	
	/**
	 * Renvoie la case où est le joueur actif
	 * @return Case
	 */
	public Case getCaseActive() {
		return getCase(getJoueurActif().getPosition());
	}
	
	/* PARTIE JEU */
	
	/**
	 * Met fin à la partie
	 * return boolean
	 */
	@Override
	public boolean finPartie() {
		int nbJoueursEnJeu = 0;
		for(Playermonop j:joueurs) {
			if(!j.getEstBanqueroute()) nbJoueursEnJeu++;
		}
		return (nbJoueursEnJeu <= 1);
	}

	/*PARTIE CARTE */
	
	/**
	 * Renvoie la liste des cartes chances 
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteChance() {
		Carte c = chance.remove(0);
		if(!c.getNom().equals("Sortie"))
			chance.add(c);
		return c;
	}
	
	/**
	 * Renvoie la liste des cartes communautés 
	 * @return c
	 * @see Carte
	 */
	public Carte tirerCarteCommunauté() {
		Carte c = communauté.remove(0);
		if(!c.getNom().equals("Sortie"))
			communauté.add(c);
		return c;
	}
	
	/**
	 * Permet l'ajout de la carte Sortie Prison lorsqu'un joueur qui la possède l'utilise
	 * @see Carte
	 */
	public void remettreCarteSortiePrisonDansPaquet() {
		
		boolean carteDansPaquetChance = false;
		for(Carte c:chance) {
			if(c.getNom().equals("Sortie"))
				carteDansPaquetChance = true;  // TRÈS TRÈS MOCHE, OUI
		}
		
		if(carteDansPaquetChance)
			chance.add(new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)"));
		else
			communauté.add(new CarteSortirPrison("Sortie", "Vous êtes libéré de prison. \n(Cette carte doit être conservée)"));
	}

	
	/* TOSTRING */
	
	@Override
	/**
	 * fonction obligatoire
	 */
	public String toString() {
		return "PlateauMonopoly [toString()=" + super.getCase(1) + "]";
	}
	
	
	
}

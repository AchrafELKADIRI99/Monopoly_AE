package model;

import model2.Boardmonop;
import model2.Playermonop;
import windows.MainWindow;
//import jeumonopoly.JoueurMonopoly;
//import jeumonopoly.PlateauMonopoly;

public abstract class Carte {
	private String titre;
	private String description;
	
	/**
	 * Définit une carte avec un titre et une description
	 * @param titre String
	 * @param description String
	 */
	public Carte(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}
	
	/**
	 * Renvoie le titre de la carte
	 * @return titre
	 */
	public String getNom() {
		return this.titre;
	}
	
	/**
	 * Renvoie la description de la carte
	 * @return description
	 */
	public String getDesc() {
		return this.description;
	}
	
	/**
	 * Action de la carte en fonction du joueur, sur le plateau
	 * @param joueur JoueurMonopoly
	 * @param plateau PlateauMonopoly
	 * @param fp FenetrePrincipale
	 * @see cartes.CarteDeplacement
	 * @see cartes.CartePayerArgent
	 * @see cartes.CarteRecevoirArgent 
	 * @see cartes.CarteSortirPrison
	 */
	public abstract void actionCarte(Playermonop player, Boardmonop board, MainWindow mw);
	
	@Override
	public String toString() {
		return "Carte [titre=" + titre + ", description=" + description + "]";
	}
}

package views;


import java.util.ArrayList;
import java.util.Random;

import Controllers.Boardmonop;
import Controllers.Party;
import Controllers.Playermonop;
import cases.CaseTerrain;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import model.Case;
import state.Context;
import state.StartState;
import state.StopState;


/**
 * Fen�tre javafx principale pour l'affichage du jeu de Monopoly. 
 */
public class MainWindow {
	
	//cc
	
	StartState startState = new StartState();
	StopState stopState = new StopState();

	
	private Button b_Mute;
	private Stage stage;
	private StackPane root;
	private Label l_ParcGratuit = new Label("0DH");
	private Label l_Message = new Label("");
	private ArrayList <Label> l_Joueurs = new ArrayList <Label>();
	private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
	private ArrayList <Circle> l_Pions = new ArrayList <Circle>();
	private ArrayList<Label> l_Logs = new ArrayList<Label>();
	private ArrayList<Image> imageDes = new ArrayList<Image>();
	private ArrayList<ImageView> Des = new ArrayList<ImageView>();
	private Button tourSuivant = new Button("Tour suivant");
	private Button newPartie = new Button("Nouvelle partie");
	public Random rand = new Random();
	public Color[] Couleurs = new Color[] {Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN};
	private ViewDemarrage fd = new ViewDemarrage(this);
	private ViewCarteChance fch = new ViewCarteChance(this);
	private ViewCarteCommunaute fco = new ViewCarteCommunaute(this);
	private ViewAcheterTerrain fat = new ViewAcheterTerrain(this);
	private ViewSortirPrison fprison = new ViewSortirPrison(this);
	private ViewActionSurTerrain fact_ter = new ViewActionSurTerrain(this);
	private Party partie;
	
	
	private static MainWindow instance = new MainWindow();
	
	//private constructor
	private MainWindow() {}
	
	
	//retur instance
public static MainWindow getInstance() {
	return instance;
}

public  void chargeMainWindow(Stage primaryStage) {
	//Constructeur de la classe FenetrePrincipale
	
	this.stage = primaryStage;
	
	root = new StackPane();
	root.setOnMouseClicked(new EvtClicRoot());
	initRoot();
	
	Scene scene = new Scene(root,655,655);
	stage.setScene(scene);
	stage.setTitle("Monopoly");
	
	fd.getStage().show();
}
	/**
	 * Unique constructeur de la classe {@link MainWindow}, prenant en param�tre la Stage principale pass�e par le main. 
	 * Le constructeur n'affiche pas la Stage au d�marrage, mais une instance de {@link ViewDemarrage} pour choisir un nombre de joueur avant l'affichage.
	 * @param primaryStage Stage
	 * @see application.Main 
	 * @see ViewDemarrage
	 */
/*	public MainWindow(Stage primaryStage) {
		//Constructeur de la classe FenetrePrincipale
		
		this.stage = primaryStage;
		
		root = new StackPane();
		root.setOnMouseClicked(new EvtClicRoot());
		initRoot();
		
		Scene scene = new Scene(root,655,655);
		stage.setScene(scene);
		stage.setTitle("Monopoly");
		
		fd.getStage().show();
	}*/
	
	/**
	 * Initialise la StackPane root de la FenetrePrincipale avec les images, les labels et les boutons ad�quates au Monopoly.
	 */
	@SuppressWarnings("static-access")
	private void initRoot() {
		
		root.setStyle("-fx-background-image: url('images/plateau.png'); -fx-background-repeat: no-repeat");
		root.setAlignment(Pos.TOP_LEFT);
		
		for(int i=1; i<7; i++)
			imageDes.add(new Image("images/de"+i+".jpg"));

		Des.add(new ImageView());
		Des.add(new ImageView());
		Des.get(0).setTranslateX(247);
		Des.get(0).setTranslateY(400);
		Des.get(1).setTranslateX(337);
		Des.get(1).setTranslateY(400);
		root.getChildren().add(Des.get(0));
		root.getChildren().add(Des.get(1));
		
		l_ParcGratuit.setTranslateX(3);
		l_ParcGratuit.setTranslateY(68);
		l_ParcGratuit.setStyle("-fx-font-family:'Century Gothic'");
		root.getChildren().add(l_ParcGratuit);
		
		l_Message.setFont(Font.font("Consolas", 14));
		l_Message.setTranslateX(95);
		l_Message.setTranslateY(480);
		l_Message.setMaxWidth(470);
		l_Message.setStyle("-fx-font-family:'Century Gothic'");
		root.getChildren().add(l_Message);

		tourSuivant.setTranslateX(285);
		tourSuivant.setTranslateY(533);
		tourSuivant.setStyle("-fx-background-color: #ed1c24;-fx-text-fill: white;-fx-background-radius: 30px ;-fx-font-family:'Century Gothic'");
		tourSuivant.setMaxWidth(100);
		tourSuivant.setMaxHeight(30);
		tourSuivant.setOnAction(new EvtTourSuivant());
		tourSuivant.setDefaultButton(true);
		
		
			b_Mute = new Button("Musique");
		
	
		
		b_Mute.setTranslateX(100);
		b_Mute.setTranslateY(533);
		b_Mute.setStyle("-fx-background-radius: 30px;-fx-font-family:'Century Gothic';-fx-background-color: #1da64a;-fx-text-fill :#ffff");
		b_Mute.setOnAction(new EvtMute());
		b_Mute.setDefaultButton(true);
		b_Mute.setOnAction(new EvtMute());

		root.getChildren().add(b_Mute);
		
		
		
		if(!partie.PARTIE_AUTO)
			root.getChildren().add(tourSuivant);
	}
	
	/**
	 * Renvoie la StackPane root de {@link MainWindow}.
	 * @return root StackPane
	 */
	public StackPane getRoot() {
		return root;
	}
	
	/**
	 * Renvoie la Stage stage de {@link MainWindow}.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Renvoie le {@link Circle} pion du joueur actif dans la {@link Partie}.
	 * @return pion Circle
	 * @see Partie
	 */
	public Circle getPionActif() {
		return l_Pions.get(partie.getPM().getJoueurActifID());
	}
	
	/**
	 * Renvoie la {@link Partie} partie de la {@link MainWindow}.
	 * @return partie {@link Partie}
	 */
	public Party getPartie() {
		return partie;
	}
	
	/**
	 * M�thode permettant de lancer une partie. Elle instanciera une nouvelle {@link Partie} avec le bon nombre de joueurs 
	 * et se chargera de placer les �l�ments graphiques tels que : <br>les noms des joueurs, l'argent qu'ils poss�dent, 
	 * la liste de leurs terrains et les pions.
	 * @param nbJoueurs int
	 */
	public void setPartie(int nbJoueurs, ArrayList<String> nomsDesJoueurs) {

		partie = new Party(nbJoueurs, nomsDesJoueurs, this);

		for(int i=0; i<nbJoueurs; i++) {
			Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
			l_nomJoueur.setTextFill(Couleurs[i]);
			l_nomJoueur.setTranslateX(95+i*120);
			l_nomJoueur.setTranslateY(100);
			l_nomJoueur.setFont(Font.font("Century Gothic", 15));
			root.getChildren().add(l_nomJoueur);

			l_Joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"DH"));
			l_Joueurs.get(i).setTranslateX(95+i*120);
			l_Joueurs.get(i).setTranslateY(120);
			
			l_Joueurs.get(i).setFont(Font.font("Century Gothic", 15));
			root.getChildren().add(l_Joueurs.get(i));

			l_ListeTerrains.add(new Label("\n"));
			l_ListeTerrains.get(i).setTranslateX(95+i*120);
			l_ListeTerrains.get(i).setTranslateY(140);
			l_ListeTerrains.get(i).setMaxWidth(110);
			l_ListeTerrains.get(i).setFont(Font.font("Century Gothic", 15));
			root.getChildren().add(l_ListeTerrains.get(i));

			l_Pions.add(new Circle(7));
			l_Pions.get(i).setFill(Couleurs[i]);
			if(i<2) {
				l_Pions.get(i).setTranslateX(598 + i*15);
				l_Pions.get(i).setTranslateY(605);
			}
			else {
				l_Pions.get(i).setTranslateX(598 + (i-2)*15);
				l_Pions.get(i).setTranslateY(620);
			}
			root.getChildren().add(l_Pions.get(i));
		}

		refreshLabels(partie.getPM());
		partie.demarrerLaPartie();
	}
	
	/**
	 * Affiche le message pass� en param�tre dans la fen�tre.
	 * @param msg String
	 */	
	public void afficherMessage(String msg) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	l_Message.setTextFill(Couleurs[getPartie().getPM().getJoueurActifID()]);
            	
            	l_Message.setText(msg);
            }
        });
	}
	
	/**
	 * Cette m�thode est appel� � chaque fois qu'un rafrichissement des labels est n�cessaire. Elle va chercher les informations dans
	 * les champs de la partie pour mettre � jours les labels.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void refreshLabels(Boardmonop pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
        		l_ParcGratuit.setText(""+pm.getCase(20).getPrix()+"DH");
        		
        		for(int i=0; i<pm.getNbJoueurs(); i++) {
            		l_Joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"DH "+(pm.getJoueur(i).getCarteSortiePrison()?"":""));
            		
            		String listeTerrains = pm.getJoueur(i).getListeStringTerrains();
            		listeTerrains = listeTerrains.replaceAll(",", "\n");
            		l_ListeTerrains.get(i).setStyle("-fx-font-family:'Century Gothic'");
            		l_ListeTerrains.get(i).setText(listeTerrains);
            		
        		}
        		
            }
        });
	}
	
	/**
	 * Affiche la fen�tre {@link ViewAcheterTerrain}.
	 * @see ViewAcheterTerrain
	 */
	public void afficherFenetreAchatTerrain() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fat.afficherFenetre();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link ViewSortirPrison}.
	 * @see ViewSortirPrison
	 */
	public void afficherFenetrePrison() {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	fprison.afficherFenetre();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link ViewCarteChance}. <br>
	 * Les param�tres String titre et String description pass�s seront utilis�s dans la fen�tre pour indiquer qu'elle carte on a tir�.
	 * @param titre String
	 * @param description String
	 * @see ViewCarteChance 
	 */
	public void afficherFenetreCarteChance(String titre, String description) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
				fch.setTitre(titre);
				fch.setDescription(description);
				fch.afficherCarte();
            }
		});
	}
	
	/**
	 * Affiche la fen�tre {@link ViewCarteCommunaute}. <br>
	 * Les param�tres String titre et String description pass�s seront utilis�s dans la fen�tre pour indiquer qu'elle carte on a tir�.
	 * @param titre String
	 * @param description String
	 * @see ViewCarteCommunaute
	 */
	public void afficherFenetreCarteCommunaut�(String titre, String description) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
				fco.setTitre(titre);
				fco.setDescription(description);
				fco.afficherCarte();
            }
		});
	}
	
	/**
	 * M�thode pla�ant un marqueur d�signant le propri�taire du terrain quand le joueur ach�te le terrain.
	 * @param joueur JoueurMonopoly
	 * @param caze Case
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void setMarqueurProprietaire(Playermonop joueur, Case caze) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	caze.getMarqueur().setFill(getPionActif().getFill());
            	
            	double x = 100, y = 100;
        		int pos = joueur.getPosition();
        		
        		if(caze.getMarqueur().getPoints().isEmpty())
        			root.getChildren().add(caze.getMarqueur());
        		
        		if(pos > 0 && pos < 10) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 517 - ((pos-1) * 54);
        			y = 642;
        		}
        		else if(pos > 10 && pos < 20) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
        			x = 51;
        			y = 558 - ((pos-11) * 54);
        		}
        		else if(pos > 20 && pos < 30) {
        			if(caze.getMarqueur().getPoints().isEmpty())
        				caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
        			x = 85 + ((pos-21) * 54);
        			y = 51;
        		}
        		else if(pos > 30 && pos < 40) {
        				if(caze.getMarqueur().getPoints().isEmpty())
        			caze.getMarqueur().getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
        			x = 592;
        			y = 85 + ((pos-31) * 54);
        		}
        		
        		if(pos == 15 || pos == 12)
        			x+=21;
        		else if(pos == 25 || pos == 28)
        			y+=21;
        		else if(pos == 35)
        			x-=21;
        		
        		caze.getMarqueur().setTranslateX(x);
        		caze.getMarqueur().setTranslateY(y);
            }
        });
	}
	
	/**
	 * M�thode ajoutant un {@link Polygon} maison dans la fen�tre principale en fonction de la {@link Case} pass�e en param�tre.
	 * @param caze Case
	 * @see Case
	 */
	public void setMaison(CaseTerrain caze){
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	Polygon maison = caze.maisons.get(caze.getNbMaison());
            	
            	maison.setFill(Color.BLACK);
            	
            	int x = -50;
            	int y = -50;
            	int pos = caze.getId();
            	
            	if(caze.getMarqueur().getPoints().isEmpty())
            		root.getChildren().add(maison);
            	
            	maison.getPoints().addAll(new Double[] {0., 11., 0., 3., 5., 0., 10., 3., 10., 11.});
            	
            	if(pos > 0 && pos < 10) {
        			x = 520 - ((pos-1) * 54) + (caze.getNbMaison()-1)*12;
        			y = 577;
        		}
        		else if(pos > 10 && pos < 20) {
        			x = 69;
        			y = 519 - ((pos-11) * 54) + (caze.getNbMaison()-1)*13;
        		}
        		else if(pos > 20 && pos < 30) {
        			x = 87 + ((pos-21) * 54)  + (caze.getNbMaison()-1)*12;
        			y = 69;
        		}
        		else if(pos > 30 && pos < 40) {
        			x = 576;
        			y = 87 + ((pos-31) * 54) + (caze.getNbMaison()-1)*13;
        		}
            	
            	maison.setTranslateX(x);
            	maison.setTranslateY(y);
            	
            }
		});
	}
	
	/**
	 * Afficher les images des d�s dans la FenetrePrincipale.
	 * @param PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void afficherDes(Boardmonop pm) {
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	effacerDes();
				Des.get(0).setImage(imageDes.get(pm.des.getDe1()-1));
				Des.get(1).setImage(imageDes.get(pm.des.getDe2()-1));
				
            }
       });
	}
	
	public void effacerDes() {
		Des.get(0).setImage(null);
		Des.get(1).setImage(null);
	}
	
	/**
	 * D�place le pion du joueur actif en fonction de la position sur le plateau de joueur pass� en param�tre.
	 * @param joueur JoueurMonopoly
	 * @see JoueurMonopoly
	 */
	public void deplacerPion(Playermonop joueur){

		double x, y;
		int pos = joueur.getPosition();
		TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());
		
		if(joueur.getEstBanqueroute()) {
			x = 103;
			y = 538;
		}
		else if(pos == 0) {
			x = 604;
			y = 604;
		}
		else if(pos == 10) {
			if(joueur.getEstPrison()) {
				x = 47;
				y = 598;
			}
			else if(joueur.getID() == 0 || joueur.getID() == 1){
				x = 16;
				y = 644;
			}
			else /* idJoueur == 2 ou 3*/ {
				x = 48;
				y = 628;
			}
		}
		else if(pos == 20) {
			x = 30;
			y = 34;
		}
		else if(pos == 30) {
			x = 604;
			y = 34;
		}
		else if(pos > 0 && pos < 10) {
			x = 537 - ((pos-1) * 54);
			y = 620;
		}
		else if(pos > 10 && pos < 20) {
			x = 30;
			y = 538 - ((pos-11) * 54);
		}
		else if(pos > 20 && pos < 30) {
			x = 104 + ((pos-21) * 54);
			y = 30;
		}
		else if(pos > 30 && pos < 40) {
			x = 612;
			y = 106 + ((pos-31) * 54);
		}
		else {
			x = -50;
			y = -50;
		}
		
		switch(joueur.getID()) {
		case 0: x-=8; y-=8; break;
		case 1: x+=8; y-=8; break;
		case 2: x-=8; y+=8; break;
		case 3: x+=8; y+=8; break;
		default: break;
		}
		
	    tt.setToX(x);
	    tt.setToY(y);
	    tt.play();
	}
	
	/**
	 * Affiche le vainqueur de la partie. Ajoute �galement le bouton newPartie � la fen�tre princiaple.
	 * @param pm PlateauMonopoly
	 * @see PlateauMonopoly
	 */
	public void afficherVainqueur(Boardmonop pm) {
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
            	Label vainqueur = new Label("Le vainqueur est "+pm.estVainqueur().getNom()+" !");
            	vainqueur.setTextFill(l_Pions.get(pm.estVainqueur().getID()).getFill());
            	vainqueur.setFont(Font.font("Century Gothic", 18));
            	vainqueur.setTextFill(Color.RED);;
            	vainqueur.setTranslateX(160);
            	vainqueur.setTranslateY(525);
            	
        		root.getChildren().add(vainqueur);
        		
        		root.getChildren().remove(tourSuivant);
        		
        		newPartie.setTranslateX(463);
        		newPartie.setTranslateY(533);
        		newPartie.setStyle("-fx-background-color: #ed1c24;-fx-text-fill: white;-fx-background-radius: 30px ;-fx-font-family:'Century Gothic'");
        		newPartie.setOnAction(new EvtNewPartie());
        		root.getChildren().add(newPartie);
        		
            }
		});
	}
	
	/**
	 * R�initialise les �l�ments graphiques de la fen�tre tels que les labels, les pions et les logs.
	 */
	public void resetElementsGraphiques() {
		l_ParcGratuit.setText("0DH");
		l_Joueurs.clear();
		l_ListeTerrains.clear();
		l_Pions.clear();
		l_Logs.clear();
	}
	
	/**
	 * �v�nement lorque l'on appuie sur le bouton tourSuivant : la partie reprend.
	 */
	private class EvtTourSuivant implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			partie.reprendrePartie();
		}
	}
	/**
	 * �v�nement lorque l'on appuie sur le bouton newPartie : la fen�tre principale se ferme, les �l�ments graphiques sont
	 * r�initialis�s, la StackPane root est red�finie et on r�affiche la fen�tre de d�marrage.
	 * @see ViewDemarrage
	 */
	private class EvtNewPartie implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent event) {
			stage.close();
			resetElementsGraphiques();
			root = new StackPane();
			initRoot();
			Scene scene = new Scene(root,655,655);
			stage.setScene(scene);
			fd.getStage().show();
		}
	}
	/**
	 * �v�nement lorqu'on clic dans la StackPane root : 
	 * en fonction des coordonn�es du pointeurs, on peux obtenir la position de la case vis�e. <br>
	 * Si cette position est une position valide (c�d que l'on clic sur une {@link CaseTerrain} qui appartient au joueur dont
	 * c'est le tour), alors on peut d�clencher l'affichage d'une {@link ViewAcheterTerrain} avec en param�tre la position cliqu�e.
	 * @see CaseTerrain
	 * @see ViewAcheterTerrain
	 */
	private class EvtClicRoot implements EventHandler<MouseEvent> {
		
		@Override
		public void handle(MouseEvent event) {
			
			int pos = -1;
			
			if(event.getSceneX() < 84) {
				if(event.getSceneY() < 84)
					pos = 20;
				else if(event.getSceneY() < 570)
					pos = 19 - (int)((event.getSceneY()-84)/54);
				else
					pos = 10;
			}
			else if(event.getSceneX() < 570) {
				if(event.getSceneY() < 84)
					pos = 21 + (int)((event.getSceneX()-84)/54);
				else if(event.getSceneY() >= 570)
					pos = 9 - (int)((event.getSceneX()-84)/54);
			}
			else {
				if(event.getSceneY() < 84)
					pos = 30;
				else if(event.getSceneY() < 570)
					pos = 31 + (int)((event.getSceneY()-84)/54);
				else
					pos = 0;
			}
			
			ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
			for(int i=0; i<40; i++) {
				CasesInterdites.add(i);
			}
			CasesInterdites.add(-1);
			for(Case t:getPartie().getPM().getJoueurActif().getListeTerrains()) {
				CasesInterdites.remove((Object)(t.getId()));
			}
			
			if(!CasesInterdites.contains(pos)) {
				fact_ter.afficherFenetre(pos);
			}
		}
	}
	
	
	private class EvtMute implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
			if (ViewDemarrage.con().etat()==true) {	
				stopState.doAction(ViewDemarrage.con());
				b_Mute.setText("UNMUTE");
				
				}
		
			else {
				startState.doAction(ViewDemarrage.con());
				b_Mute.setText("MUTE");
				
			}
			
		}
	}
	
}
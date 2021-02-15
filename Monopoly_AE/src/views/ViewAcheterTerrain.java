package views;

import cases.CaseTerrain;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Fen�tre � afficher lorqu'on atterit sur une {@link CaseTerrain} sans propri�taire.<br><br>
 * ** <b>Action r�alisable : </b> Acheter le terrain
 * @see FenetrePrincipale
 */
public class ViewAcheterTerrain {
	
	private MainWindow fp;
	private Stage stage;
	private HBox root;
	private Label l_Texte;
	private Button b_Oui;
	private Button b_Non;
	
	/**
	 * Unique constructeur de la classe {@link ViewAcheterTerrain}, prenant en param�tre la {@link FenetrePrincipale} fp.
	 * @param fp FenetrePrincipale
	 * @see FenetrePrincipale
	 */
	public ViewAcheterTerrain(MainWindow fp) {
		
		this.fp = fp;
		
		this.stage = new Stage();
		this.stage.setTitle("Acheter ce terrain ?");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.setOnHiding(new EvtQuitter());
	}
	
	/**
	 * Initialise la HBox root de la FenetreAcheterTerrain avec une image, un label posant une question et des boutons Oui/Non.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(10);
		root.setStyle("-fx-background-color: #c9f9b6;-fx-font-family:'Century Gothic' ");
		
		Image i_terrain;
		
		switch(fp.getPartie().getPM().getCaseActive().getNom()) {
		case "Gare CTM": i_terrain = new Image("images/gare.jpg"); break;
		case "Gare ONCF GUELIZ": i_terrain = new Image("images/gare.jpg"); break;
		case "Gare BAB DOUKKALA": i_terrain = new Image("images/gare.jpg"); break;
		case "Gare SUPRATOUR": i_terrain = new Image("images/gare.jpg"); break;
		case "Compagnie des eaux": i_terrain = new Image("images/eau.jpg"); break;
		case "Compagnie d'�lectricit�": i_terrain = new Image("images/elec.jpg"); break;
		default: {
			String couleur = fp.getPartie().getPM().getCaseActive().getCouleur();
			i_terrain = new Image("images/m_"+couleur+".jpg");
		}; break;
		}
		
		ImageView iv_terrain = new ImageView(i_terrain);
		root.getChildren().add(iv_terrain);
		
		VBox aside = new VBox();
		aside.setSpacing(15);
		root.getChildren().add(aside);
		
		l_Texte = new Label("Voulez vous acheter propriete � " + fp.getPartie().getPM().getCaseActive().getNom() + " pour " + fp.getPartie().getPM().getCaseActive().getPrix() + "DH ?");
		aside.getChildren().add(l_Texte);

		HBox buttons_horiz = new HBox();
		buttons_horiz.setSpacing(10);
		
		b_Oui = new Button("Oui");
		b_Oui.setStyle("-fx-background-radius: 30px;-fx-font-family:'Century Gothic';-fx-background-color: #1da64a;-fx-text-fill :#ffff");
		b_Oui.setOnAction(new EvtOui());
		buttons_horiz.getChildren().add(b_Oui);
		
		b_Non = new Button("Non");
		b_Non.setStyle("-fx-background-color: #ed1c24;-fx-background-radius: 30px;-fx-font-family:'Century Gothic';-fx-text-fill :#ffff ");

		b_Non.setOnAction(new EvtNon());
		buttons_horiz.getChildren().add(b_Non);

		aside.getChildren().add(buttons_horiz);
		
		root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           if(b_Oui.isFocused())
	        	   b_Oui.fire();
	           else
	        	   b_Non.fire();
	           ev.consume(); 
	        }
	    });
	}
	
	/**
	 * Affiche la fen�tre en r�initialisant la HBox root � chaque appel.
	 */
	public void afficherFenetre() {
		root = new HBox();
		initRoot();
		
		Scene scene = new Scene(root,565,130);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Renvoie la Stage de la fen�tre.
	 * @return stage Stage
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * �v�nement qui ferme la fen�tre et change la r�ponse � vrai.
	 */
	private class EvtOui implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			fp.getPartie().getPM().getCaseActive().setReponseQuestion(true);
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * �v�nement qui ferme la fen�tre (la r�ponse n'est pas chang�e et reste � faux).
	 */
	private class EvtNon implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			stage.close();
			event.consume();
		}
	}
	
	/**
	 * �v�nement qui reprend la partie quand la fen�tre se ferme.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			fp.getPartie().reprendrePartie();
			event.consume();
		}
	}
}

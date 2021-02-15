package views;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;

import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import state.Context;
import state.StartState;
import state.StopState;

/**
 * Fenêtre à afficher au démarrage d'une nouvelle {@link jeumonopoly.Partie}, permettant de sélectionner le nombre de joueur.
 * @see FenetrePrincipale
 */
public class ViewDemarrage  {

	Context context = new Context();
	StartState startState = new StartState();
	StopState stopState = new StopState();
	private MainWindow fp;
	private Stage stage;
	private VBox root;
	private Label l_NbJoueurs;
	private ArrayList<TextField> listeJoueurs = new ArrayList<TextField>();
	private Button b_Valider;
	private Button b_Mute;
	
	private int choix = 0;


	/**
	 * Unique constructeur de la classe {@link FenetreDemarrage}, prenant en paramètre la {@link FenetrePrincipale} fp.
	 * @param fp FenetrePrincipale
	 * @see FenetrePrincipale
	
	 */
    
    
    
    
    
	public ViewDemarrage(MainWindow fp) {
		
		
		this.fp = fp;

		this.stage = new Stage();

		this.stage.setTitle("MONOPOLY MARRAKECH");
		this.stage.initOwner(fp.getStage());
		this.stage.initModality(Modality.APPLICATION_MODAL);


		//Scene scene = new Scene(AnchorPaneRoot, 884.0,495.0);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


		root = new VBox();

		initRoot();
	    Scene scene = new Scene(root/*, 884.0,495.0/*400,190*/);


		stage.setScene(scene);

		stage.setOnHiding(new EvtQuitter());
		
		startState.doAction(context);
		 
	}
//ezez
	/**
	 * Initialise la VBox root de la FenetreDemarrage avec une {@link ListView} de nombres de joueurs et un bouton de validation.
	 */
	private void initRoot() {
		root.setPadding(new Insets(10,10,10,10));
		root.setSpacing(5);
       // root.resize(884, 495);

		l_NbJoueurs = new Label("SAISIR LES NOMS DES JOUEURS (Au moins 2 joueurs) :");
		l_NbJoueurs.setStyle("-fx-font-family: 'Century Gothic'");
		


		VBox vBox1 =new VBox();

		vBox1.setPrefHeight(200.0);
		vBox1.setPrefWidth(200.);
		vBox1.setStyle("-fx-background-color:linear-gradient(to right top,#3A1C71,#FDBB2D)");

		//vBox1.setPrefHeight(884.0);
		//vBox1.setPrefWidth(495.);
		vBox1.setStyle("-fx-background-color:linear-gradient(to right top,#c9f9b6,#1da64a)");

		vBox1.setBlendMode(BlendMode.SRC_OVER);
		vBox1.setDepthTest(DepthTest.INHERIT);
		vBox1.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox1.setCenterShape(true);
		

		ImageView imageView1=new ImageView();
		imageView1.setFitHeight(400.0);
		imageView1.setFitWidth(400.0);
		imageView1.setPickOnBounds(true);
		imageView1.setPreserveRatio(true);
		
		imageView1.setImage(new Image("/images/monopmarrakech.png"));

		VBox.setMargin((imageView1), new Insets(10,10,10,10));

		vBox1.getChildren().add(imageView1);



		



		root.getChildren().add(vBox1);



		root.getChildren().add(l_NbJoueurs);
		for(int i=0; i<4; i++) {
			listeJoueurs.add(new TextField());
			listeJoueurs.get(i).setPromptText("Player N° "+(i+1));			
			listeJoueurs.get(i).setStyle("-fx-background-radius: 30px;-fx-font-family:'Century Gothic'");

			root.getChildren().add(listeJoueurs.get(i));
		}
		for(int i=0; i<2; i++) {

			listeJoueurs.get(i).setText("Player N° "+(i+1));
		
		}
		
		b_Valider = new Button("PLAY");
		b_Valider.setTranslateX(180);
		b_Valider.setTranslateY(6);
		b_Valider.setStyle("-fx-background-radius: 30px;-fx-font-family:'Century Gothic';-fx-background-color: #1da64a;-fx-text-fill :#ffff");
		b_Valider.setOnAction(new EvtValider());
		b_Valider.setDefaultButton(true);
		b_Valider.setOnAction(new EvtValider());

		root.getChildren().add(b_Valider);
		
		
		
		
		b_Mute = new Button("MUTE ");
		b_Mute.setTranslateX(360);
		b_Mute.setTranslateY(-21);
		b_Mute.setStyle("-fx-background-radius: 30px;-fx-font-family:'Century Gothic';-fx-background-color: #1da64a;-fx-text-fill :#ffff");
		b_Mute.setOnAction(new EvtMute());
		b_Mute.setDefaultButton(true);
		b_Mute.setOnAction(new EvtMute());

		root.getChildren().add(b_Mute);
	

		

		/*VBox vBox2 =new VBox();
		vBox2.setPrefHeight(884.0);
		vBox2.setPrefWidth(495.);
		vBox2.setStyle("-fx-background-color: #005395");
		vBox2.setBlendMode(BlendMode.SRC_OVER);
		vBox2.setDepthTest(DepthTest.INHERIT);
		vBox2.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		vBox2.setCenterShape(true);
		

		ImageView imageView2=new ImageView();
		imageView2.setFitHeight(300.0);
		imageView2.setFitWidth(300.0);
		imageView2.setPickOnBounds(true);
		imageView2.setPreserveRatio(true);

		

		VBox.setMargin((imageView2), new Insets(10,0,0,270));

		vBox2.getChildren().add(imageView2);

<<<<<<< HEAD
		root.getChildren().add(vBox2);
		
		
		
		
		
		
=======
		root.getChildren().add(vBox2);*/
	}


	public Stage getStage() {
		return stage;
	}

	/**
	 * Évènement qui récupère dans la {@link ListView} le nombre de joueurs désiré et lance une partie avec ce nombre.
	 * @see FenetrePrincipale
	 */
	private class EvtValider implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			
			ArrayList<String> champs = new ArrayList<String>();
			
			for(int i=0; i<4; i++) {
				if(listeJoueurs.get(i).getText() != null && !listeJoueurs.get(i).getText().isEmpty())
					champs.add(listeJoueurs.get(i).getText());
			}
			if(champs.size()>=2) {
				choix = 1;
				fp.setPartie(champs.size(), champs);
				fp.getStage().show();
				stage.close();
			}
			
			event.consume();
		
		}
	}

	/**
	 * Évènement qui ferme la fenêtre de démarrage et arrête le programme si l'on a pas cliqué sur le bouton Valider.
	 */
	private class EvtQuitter implements EventHandler<WindowEvent> {

		@Override
		public void handle(WindowEvent event) {
			if(choix == 0)
				System.exit(0);
			event.consume();
		}
	}


	

	private class EvtMute implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if (context.etat()==true) {	
				stopState.doAction(context);
				b_Mute.setText("UNMUTE");
				
				
				}
		
			else {
				startState.doAction(context);
				b_Mute.setText("MUTE");
				
			}
			
		}
	}


	
}

package windows;

import fenetres.FenetrePrincipale.EvtClicRoot;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainWindow {
	public MainWindow(Stage primaryStage) {
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
}

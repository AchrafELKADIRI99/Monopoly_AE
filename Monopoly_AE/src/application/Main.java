package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import views.MainWindow;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			new MainWindow(primaryStage);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}

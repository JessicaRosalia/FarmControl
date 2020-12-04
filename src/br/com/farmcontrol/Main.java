package br.com.farmcontrol;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/CrudAnimal.fxml"));
			Scene scene = new Scene(root);
  
			scene.getStylesheets().add(getClass().getResource("/br/com/farmcontrol/view/resources/styles/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
                        
                      
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

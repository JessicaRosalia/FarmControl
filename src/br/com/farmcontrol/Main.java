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
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("br/com/farmcontrol/view/resources/fxml/Layout.fxml"));
			Scene scene = new Scene(root);
  
			scene.getStylesheets().add(getClass().getClassLoader().getResource("br/com/farmcontrol/view/resources/styles/style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			

			primaryStage.setMinHeight(500.0);
			primaryStage.setMaxHeight(540.0);
			
			primaryStage.setMinWidth(820.0);
			primaryStage.setMaxWidth(880.0);
			
			
                        
                      
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

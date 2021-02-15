package uppgift_crm;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage mainStage;
	
	public static Stage getStage() {
		return mainStage;
	}
	
	public void init() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mainStage = primaryStage;
			mainStage.setTitle("CRM");
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		Filehandler.getInstance().saveCustomers();
		Filehandler.getInstance().saveSellers();
		Filehandler.getInstance().saveEvents();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

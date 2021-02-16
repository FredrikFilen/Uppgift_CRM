package uppgift_crm.Controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import uppgift_crm.Main;

public abstract class ControllerTools {
	public void changeScene(String fxml) throws IOException {
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
		Main.getStage().setScene(scene);
	}

}

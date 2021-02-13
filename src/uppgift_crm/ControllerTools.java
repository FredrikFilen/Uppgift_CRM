package uppgift_crm;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public abstract class ControllerTools {
	public void changeScene(String fxml) throws IOException {
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxml)));
		Main.getStage().setScene(scene);
	}

}

package uppgift_crm;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController extends ControllerTools implements Initializable  {

	@FXML
	private TextField idTextField;

	@FXML
	private PasswordField passwordTextfield;

	@FXML
	private Button loginButton;

	@FXML
	private Label infoLabel;

	@FXML
	private Button signupButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void login(ActionEvent event) throws IOException {
		for (int i = 0; i < LogicController.getInstance().getSellers().size(); i++) {
			Seller selectedSeller = LogicController.getInstance().getSellers().get(i);

			if (selectedSeller.getId().equals(idTextField.getText())
					&& selectedSeller.getPassword().equals(passwordTextfield.getText())) {
				// login successful
				

				// passes the selectedUser to main screen
				LogicController.getInstance().setSelectedSeller(selectedSeller);

				changeScene("PrimaryStage.fxml");

			} else {
				// login unsuccesful
				try {
					infoLabel.setText("Wrong username or password");
					infoLabel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	@FXML
    void createUser(ActionEvent event) throws IOException {
		changeScene("CreateSeller.fxml");
    }
}

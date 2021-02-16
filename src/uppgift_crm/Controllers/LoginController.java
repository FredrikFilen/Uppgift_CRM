package uppgift_crm.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uppgift_crm.Models.Seller;

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
		for (int i = 0; i < DAO.getInstance().getSellers().size(); i++) {
			Seller selectedSeller = DAO.getInstance().getSellers().get(i);

			if (selectedSeller.getId().equals(idTextField.getText())
					&& selectedSeller.getPassword().equals(passwordTextfield.getText())) {
				// login successful
				

				// passes the selectedUser to main screen
				DAO.getInstance().setSelectedSeller(selectedSeller);

				changeScene("/uppgift_crm/view/PrimaryStage.fxml");

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
		changeScene("/uppgift_crm/view/CreateSeller.fxml");
    }
}

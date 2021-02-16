package uppgift_crm.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class createSellerController extends ControllerTools {
	 	@FXML
	    private TextField idTextfield;

	    @FXML
	    private TextField passwordTextfield;

	    @FXML
	    private TextField nameTextfield;

	    @FXML
	    private TextField adressTextfield;

	    @FXML
	    private Button createSellerButton;
	    
	    @FXML
	    private Button backButton;

	    @FXML
	    void createButtonClicked(ActionEvent event) throws IOException {
	    	DAO.getInstance().addSeller(idTextfield.getText(), 
	    			passwordTextfield.getText(), 
	    			nameTextfield.getText(), 
	    			adressTextfield.getText());
	    	
	    	changeScene("/uppgift_crm/view/Login.fxml");
	    	
	    }
	    
	    @FXML
	    void backButtonClicked(ActionEvent event) throws IOException {
	    	changeScene("/uppgift_crm/view/Login.fxml");
	    }
}

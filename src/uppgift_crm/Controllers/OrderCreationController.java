package uppgift_crm.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OrderCreationController extends ControllerTools {
	 	@FXML
	    private TextField productTextField;

	    @FXML
	    private TextField priceTextField;

	    @FXML
	    private TextField amountTextField;

	    @FXML
	    private Button registerOrderButton;

	    @FXML
	    private Button cancelButton;

	    @FXML
	    void cancelOrderClicked(ActionEvent event) throws IOException {
	    	changeScene("/uppgift_crm/view/PrimaryStage.fxml");
	    }

	    @FXML
	    void registerOrderClicked(ActionEvent event) throws IOException {
	    	String product = productTextField.getText();
	    	double price = Double.parseDouble(priceTextField.getText());
	    	int amount = Integer.parseInt(amountTextField.getText());
	    	
	    	DAO.getInstance().createOrder(product, price, amount);
	    	
	    	changeScene("/uppgift_crm/view/PrimaryStage.fxml");
	    	
	    }
}

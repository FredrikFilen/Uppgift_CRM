package uppgift_crm.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import uppgift_crm.Models.Customer;

public class EditCustomersController extends ControllerTools implements Initializable {
	private static ObservableList<Customer> customersObservableList;
	Iterator<Customer> customersIterator;
	
	@FXML
    private TableView<Customer> customersTableview;
	
	@FXML
	private TableColumn<Customer, String> customerNameColumn;

	@FXML
	private TableColumn<Customer, String> customerIdColumn;

	@FXML
	private TableColumn<Customer, String> customerAdressColumn;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField adressTextField;

	@FXML
	private Button addSelectedCustomerButton;

	@FXML
	private Button backButton;
	
	@FXML
    private Button addCustomerButton;
	
	@FXML
	void addCustomerClicked(ActionEvent event) {
		DAO.getInstance().addCustomer(nameTextField.getText(), adressTextField.getText());
    	populateCustomerTableview();
	}

	@FXML
	void backClicked(ActionEvent event) throws IOException {
		changeScene("/uppgift_crm/view/PrimaryStage.fxml");
	}

	@FXML
	void selectedCustomerButtonClick(ActionEvent event) {
		DAO.getInstance().getSelectedSeller().addCustomerResponsibility(DAO.getInstance().getSelectedCustomer());
    	DAO.getInstance().getSelectedCustomer().addResponsibleSeller(DAO.getInstance().getSelectedSeller());
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//fill customers tableview
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		customerAdressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
		populateCustomerTableview();
		
		//selection listeners
		customersTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		DAO.getInstance().setSelectedCustomer(newSelection);
			
		});
		
	}
	
	public void populateCustomerTableview() {
		customersObservableList = FXCollections.observableArrayList(DAO.getInstance().getCustomers());
		customersIterator = customersObservableList.iterator();
		customersTableview.getItems().clear();
		while(customersIterator.hasNext()){
			customersTableview.getItems().add((Customer) customersIterator.next());
			customersTableview.refresh();
		}
		customersTableview.refresh();
	}

}

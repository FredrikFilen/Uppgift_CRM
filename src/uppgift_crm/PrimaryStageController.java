package uppgift_crm;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryStageController implements Initializable {
		private static ObservableList<Customer> customersObservableList;
		Iterator<Customer> customersIterator;
		
		private static ObservableList<Seller> sellersObservableList;
		Iterator<Seller> sellersIterator;
	
	  	@FXML
	    private TextField nameTextbox;

	    @FXML
	    private TextField adressTextbox;

	    @FXML
	    private Button addCustomerButton;

	    @FXML
	    private TableView<Customer> customersTableview;

	    @FXML
	    private TableColumn<Customer, String> customerNameColumn;

	    @FXML
	    private TableColumn<Customer, String> customerAdressColumn;

	    @FXML
	    private Button addSellerButton;
	    
	    @FXML
	    private Button addSelectedCustomerButton;

	    @FXML
	    private TableView<Seller> sellersTableview;

	    @FXML
	    private TableColumn<Seller, String> sellerNameColumn;

	    @FXML
	    private TableView<Customer> sellersCustomersTableview;

	    @FXML
	    private TableColumn<Customer, String> sellersCustomerNameColumn;

	    @FXML
	    private TableView<Event> sellersSalesTableview;

	    @FXML
	    private TableColumn<Event, String> sellersEventProductColumn;

	    @FXML
	    private TableColumn<Event, Integer> sellersEventAmountColumn;

	    @FXML
	    private TableColumn<Event, Double> sellersEventPriceColumn;

	    @FXML
	    void addCustomerClicked(ActionEvent event) {
	    	LogicController.getInstance().addCustomer(nameTextbox.getText(), adressTextbox.getText());
	    	populateCustomerTableview();
	    	
	    }

	   
	    
	    @FXML
	    void selectedCustomerButtonClick(ActionEvent event) {
	    	LogicController.getInstance().getSelectedSeller().addCustomerResponsibility(LogicController.getInstance().getSelectedCustomer());
	    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//fill customers tableview
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		customerAdressColumn.setCellValueFactory(new PropertyValueFactory<>("Adress"));
		populateCustomerTableview();
		
		//fill  sellers tableview
		sellerNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		populateSellerTableview();
		
		//selection listeners
		customersTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			LogicController.getInstance().setSelectedCustomer(newSelection);
		});
		
		sellersTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			LogicController.getInstance().setSelectedSeller(newSelection);
		});
		
		
		
		
		
	}
	
	public void populateCustomerTableview() {
		customersObservableList = FXCollections.observableArrayList(LogicController.getInstance().getCustomers());
		customersIterator = customersObservableList.iterator();
		customersTableview.getItems().clear();
		while(customersIterator.hasNext()){
			customersTableview.getItems().add((Customer) customersIterator.next());
			customersTableview.refresh();
		}
		customersTableview.refresh();
	}
	
	public void populateSellerTableview() {
		sellersObservableList = FXCollections.observableArrayList(LogicController.getInstance().getSellers());
		sellersIterator = sellersObservableList.iterator();
		sellersTableview.getItems().clear();
		while(sellersIterator.hasNext()){
			sellersTableview.getItems().add((Seller) sellersIterator.next());
			sellersTableview.refresh();
		}
		sellersTableview.refresh();
	}
	
	public void populateSelectedsellerTableview() {
		
	}
	
}

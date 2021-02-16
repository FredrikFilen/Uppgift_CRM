package uppgift_crm.Controllers;

import java.io.IOException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import uppgift_crm.Models.Customer;
import uppgift_crm.Models.Event;
import uppgift_crm.Models.Seller;

public class PrimaryStageController extends ControllerTools implements Initializable {
		
		
		private static ObservableList<Seller> sellersObservableList;
		Iterator<Seller> sellersIterator;
		
		private static ObservableList<Customer> sellersCustomersObservableList;
		Iterator<Customer> sellersCustomersIterator;
		
		private static ObservableList<Event> sellersEventsObservableList;
		Iterator<Event> sellersEventsIterator;
		
		private static ObservableList<Event> selectedCustomersEvents;
		Iterator<Event> selectedCustomersEventsIterator;
		
		private static ObservableList<String> notificationsObservableList;
		Iterator<String> notificationsIterator;
	
	  
	    @FXML
	    private Button registerOrderButton;
	    
	    @FXML
	    private Button createReportButton;
	    
	    @FXML
	    private Button logOutButton;
	    
	    @FXML
	    private Button clearButton;

	    @FXML
	    private Button addSellerButton;
	    
	    @FXML
	    private Button editCustomersButton;
	    
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
	    private TableColumn<Customer, String> sellersCustomerIdColumn;
	    
	    @FXML
	    private TableColumn<Customer, String> sellersCustomerAdressColumn;

	    @FXML
	    private TableView<Event> sellersEventsTableview;
	    
	    @FXML
	    private TableColumn<Event, String> sellersEventCustomerNameColumn;

	    @FXML
	    private TableColumn<Event, String> sellersEventProductColumn;

	    @FXML
	    private TableColumn<Event, Integer> sellersEventAmountColumn;

	    @FXML
	    private TableColumn<Event, Double> sellersEventPriceColumn;
	    
	    @FXML
	    private TableView<Event> selectedCustomerTableview;

	    @FXML
	    private TableColumn<Event, String> selectedCustomerProductColumn;

	    @FXML
	    private TableColumn<Event, Double> selectedCustomerPriceColumn;

	    @FXML
	    private TableColumn<Event, Integer> SelectedCustomerAmountColumn;

	    @FXML
	    private TableColumn<Event, String> SelectedCustomerSellerColumn;

	    @FXML
	    private TableColumn<Event, String> SelectedCustomerDateColumn;
	    

	    @FXML
	    private ListView<String> notificationsListview;
	  


	   
	    
	    @FXML
	    void createReportBUttonClicked(ActionEvent event) throws IOException {
	    	changeScene("/uppgift_crm/view/CreateReport.fxml");
	    }
	    
	    @FXML
	    void logOutClicked(ActionEvent event) throws IOException {
	    	
	    	DAO.getInstance().setSelectedSeller(null);
	    	DAO.getInstance().setSelectedCustomer(null);
	    	
	    	changeScene("/uppgift_crm/view/Login.fxml");
	    	
	    }
	    
	    @FXML
	    void registerOrderButtonClicked(ActionEvent event) throws IOException {
	    	changeScene("/uppgift_crm/view/OrderCreation.fxml");
	    }
	    
	    @FXML
	    void clearClicked(ActionEvent event) {
	    	DAO.getInstance().getSelectedSeller().clearNotifications();
	    	populateNotifications();
	    }
	    
	    @FXML
	    void editCustomersClicked(ActionEvent event) throws IOException {
	    	changeScene("/uppgift_crm/view/EditCustomers.fxml");
	    }



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		//fill sellers customers tableview
		sellersCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		sellersCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		sellersCustomerAdressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
		populateSellersCustomersTableview();
		
		//fill sales tableview
		sellersEventCustomerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
		sellersEventProductColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
		sellersEventPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		sellersEventAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		populateSellersEvents();
		
		//fill selected customers orders
		selectedCustomerProductColumn.setCellValueFactory(new PropertyValueFactory<>("product"));
		selectedCustomerPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		SelectedCustomerAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		SelectedCustomerSellerColumn.setCellValueFactory(new PropertyValueFactory<>("seller"));
		SelectedCustomerDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		
		//fill notifications tableview
		populateNotifications();
		
		//selection listeners
		sellersCustomersTableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			DAO.getInstance().setSelectedCustomer(newSelection);
			try {
				populateSelectedCustomersEvents();
			}catch(Exception e) {
				System.out.println("Finns inga events");
			}
			
		});
		
		
		
		
		
		
		
	}
	
	
	public void populateSellerTableview() {
		sellersObservableList = FXCollections.observableArrayList(DAO.getInstance().getSellers());
		sellersIterator = sellersObservableList.iterator();
		sellersTableview.getItems().clear();
		while(sellersIterator.hasNext()){
			sellersTableview.getItems().add((Seller) sellersIterator.next());
			sellersTableview.refresh();
		}
		sellersTableview.refresh();
	}
	
	public void populateSellersCustomersTableview() {
		sellersCustomersObservableList = FXCollections.observableArrayList(DAO.getInstance().getSelectedSeller().getResponsibleCustomers());
		sellersCustomersIterator = sellersCustomersObservableList.iterator();
		sellersCustomersTableview.getItems().clear();
		while(sellersCustomersIterator.hasNext()) {
			sellersCustomersTableview.getItems().add((Customer) sellersCustomersIterator.next());
			sellersCustomersTableview.refresh();
		}
		sellersCustomersTableview.refresh();
	}
	
	public void populateSellersEvents() {
		sellersEventsObservableList = FXCollections.observableArrayList(DAO.getInstance().getSelectedSeller().getSales());
		sellersEventsIterator = sellersEventsObservableList.iterator();
		sellersEventsTableview.getItems().clear();
		while(sellersEventsIterator.hasNext()) {
			sellersEventsTableview.getItems().add((Event) sellersEventsIterator.next());
			sellersEventsTableview.refresh();
		}
		sellersEventsTableview.refresh();
	}
	
	public void populateSelectedCustomersEvents() {
		selectedCustomersEvents = FXCollections.observableArrayList(DAO.getInstance().getSelectedCustomer().getSaleEvents());
		selectedCustomersEventsIterator = selectedCustomersEvents.iterator();
		selectedCustomerTableview.getItems().clear();
		while(selectedCustomersEventsIterator.hasNext()) {
			selectedCustomerTableview.getItems().add((Event) selectedCustomersEventsIterator.next());
			selectedCustomerTableview.refresh();
		}
		selectedCustomerTableview.refresh();
	}
	
	public void populateNotifications() {
		notificationsObservableList = FXCollections.observableArrayList(DAO.getInstance().getSelectedSeller().getNotifications());
		notificationsIterator = notificationsObservableList.iterator();
		
		notificationsListview.getItems().clear();
		while(notificationsIterator.hasNext()) {
			notificationsListview.getItems().add(notificationsIterator.next());
			notificationsListview.refresh();
		}
		notificationsListview.refresh();
		
	}
}

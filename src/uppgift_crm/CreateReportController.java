package uppgift_crm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateReportController extends ControllerTools implements Initializable {
	
	@FXML
	private ChoiceBox<String> reportTypeChoicebox;
	  	
	@FXML
	private Button reportTypeChoiceButton;

	@FXML
	private ChoiceBox<Customer> customerChoiceBox;
	    
	@FXML
	private ChoiceBox<Seller> sellerChoicebox;
	    
	@FXML
	private TextField productTextfield;

	@FXML
	private Button createChartButton;

	@FXML
	private TextField titleTextfield;

	@FXML
	private TextArea ingressTextarea;

	@FXML
	private ListView<String> displayList;

	@FXML
	private Button cancelButton;

	@FXML
	private Button createReportButton;
	
	@FXML
    private Label reportValidation;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reportTypeChoicebox.getItems().add("Customer");
		reportTypeChoicebox.getItems().add("Seller");
		reportTypeChoicebox.getItems().add("Product");
			
	}
		
	@FXML
	void cancelClicked(ActionEvent event) throws IOException {
		changeScene("PrimaryStage.fxml");
	}

	@FXML
	void createChartClicked(ActionEvent event) {
		switch(reportTypeChoicebox.getValue()) {
			
		case"Customer": {
			displayList.getItems().clear();
			Customer c = customerChoiceBox.getSelectionModel().getSelectedItem();
			for(Event e: c.getSaleEvents()) {
				displayList.getItems().add(e.getNotification());
			}
			displayList.refresh();
		break;
		}
		case"Seller":{
			displayList.getItems().clear();
			Seller s = sellerChoicebox.getSelectionModel().getSelectedItem();
			for(Event e:s.getSales()) {
				displayList.getItems().add(e.getNotification());
			}
			displayList.refresh();
		break;
		}
		case"Product":{
			displayList.getItems().clear();
			populateProducts();
			break;
		}
		}
	}

		@FXML
		void createReportClicked(ActionEvent event) {
			String title = titleTextfield.getText();
			String ingress = ingressTextarea.getText();
			
			ReportBuilder builder = new ReportBuilder();
			List<String> eventlist = displayList.getItems();
			Report report = builder
					.addTitle(title)
					.addIngress(ingress)
					.addReportType(reportTypeChoicebox.getValue())
					.addEvents(eventlist)
					.build();
			
			Filehandler.getInstance().writeReport(report);
			reportValidation.setVisible(true);
		}

		@FXML
		void reportTypechoiceClicked(ActionEvent event) {
			switch(reportTypeChoicebox.getValue()) {
			
				case"Customer": {
					customerChoiceBox.setVisible(true);
					sellerChoicebox.setVisible(false);
					productTextfield.setVisible(false);
					populateCustomerSelection();
				break;
				}
				case"Seller":{
					sellerChoicebox.setVisible(true);
					customerChoiceBox.setVisible(false);
					productTextfield.setVisible(false);
					populateSellerSelection();
				break;
				}
				case"Product":{
					productTextfield.setVisible(true);
					customerChoiceBox.setVisible(false);
					sellerChoicebox.setVisible(false);
					break;
				}
			}
		}
		
		public void populateCustomerSelection() {
			for(Customer c: LogicController.getInstance().getCustomers()) {
				customerChoiceBox.getItems().add(c);
			}
		}
		
		public void populateSellerSelection() {
			for(Seller s:LogicController.getInstance().getSellers()) {
				sellerChoicebox.getItems().add(s);
			}
		}
		
		public void populateProducts() {
			String product = productTextfield.getText();
			for(Event e:LogicController.getInstance().getOrders()) {
				if(e.getProduct().equals(product.toLowerCase())) {
					displayList.getItems().add(e.getNotification());
				}
			}
			displayList.refresh();
		}


}

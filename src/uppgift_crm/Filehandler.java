package uppgift_crm;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Filehandler {
	private static Filehandler instance;
	private String SellersXmlPath = "src/uppgift_crm/assets/sellers.xml";
	private String customersXmlPath ="src/uppgift_crm/assets/customers.xml";

	private Filehandler() {
		
	}
	
	public static Filehandler getInstance() {
		if(instance == null) {
			instance = new Filehandler();
		}
		return instance;
	}
	
	public ArrayList<Customer> loadCustomers() {
		XMLDecoder decoder = createDecoder(customersXmlPath);
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			customers = (ArrayList<Customer>) decoder.readObject();
			return customers;
		}catch(Exception e) {
			System.out.println("customers.xml not found");
		}
		decoder.close();
		return customers;
		
	}
	
	public ArrayList<Seller> loadSellers() {
		XMLDecoder decoder = createDecoder(SellersXmlPath);
		ArrayList<Seller> sellers = new ArrayList<>();
		try {
			sellers = (ArrayList<Seller>) decoder.readObject();
			return sellers;
		}catch(Exception e) {
			System.out.println("Sellers.xml not found");
		}
		decoder.close();
		return sellers;
		
	}
	
	public void saveCustomers() {
		ArrayList<Customer> customers = new ArrayList<>(LogicController.getInstance().getCustomers());
		XMLEncoder encoder = createEncoder(customersXmlPath);
		encoder.writeObject(customers);
		encoder.close();
		System.out.println("customers.xml saved");
	}
	
	public void saveSellers() {
		ArrayList<Seller> sellers = new ArrayList<>(LogicController.getInstance().getSellers());
		XMLEncoder encoder = createEncoder(SellersXmlPath);
		encoder.writeObject(sellers);
		encoder.close();
		System.out.println("sellers.xml saved");
	}
	
	private XMLEncoder createEncoder(String fileName) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
		}

		catch (FileNotFoundException fileNotFound) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Kunde inte hitta filen");
			alert.setTitle("Exception");
			alert.setGraphic(null);
			alert.show();
		}
		return encoder;
	}
	
	private XMLDecoder createDecoder(String fileName) {
		XMLDecoder decoder = null;

		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));

		}

		catch (FileNotFoundException fileNotFound) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("kunde inte hitta s�kv�gen");
			alert.setTitle("Exception");
			alert.setGraphic(null);
			alert.show();
		}
		return decoder;
	}
}

package uppgift_crm.Controllers;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import uppgift_crm.Models.Customer;
import uppgift_crm.Models.Event;
import uppgift_crm.Models.Report;
import uppgift_crm.Models.Seller;

public class Filehandler {
	private static Filehandler instance;
	final String SELLERS_XML_PATH = "src/uppgift_crm/assets/sellers.xml";
	final String CUSTOMERS_XML_PATH ="src/uppgift_crm/assets/customers.xml";
	final String ORDERS_XML_PATH = "src/uppgift_crm/assets/orders.xml";
	final String REPORT_PATH = "src/uppgift_crm/assets/";

	private Filehandler() {
		
	}
	
	//singleton lazy
	public static Filehandler getInstance() {
		if(instance == null) {
			instance = new Filehandler();
		}
		return instance;
	}
	
	public ArrayList<Customer> loadCustomers() {
		XMLDecoder decoder = createDecoder(CUSTOMERS_XML_PATH);
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			customers = (ArrayList<Customer>) decoder.readObject();
			return customers;
		}catch(Exception e) {
			System.out.println("customers.xml failed to load");
		}
		decoder.close();
		return customers;
		
	}
	
	public ArrayList<Seller> loadSellers() {
		XMLDecoder decoder = createDecoder(SELLERS_XML_PATH);
		ArrayList<Seller> sellers = new ArrayList<>();
		try {
			sellers = (ArrayList<Seller>) decoder.readObject();
			return sellers;
		}catch(Exception e) {
			System.out.println("Sellers.xml failed to load");
		}
		decoder.close();
		return sellers;
		
	}
	
	public ArrayList<Event> loadEvents(){
		XMLDecoder decoder = createDecoder(ORDERS_XML_PATH);
		ArrayList<Event> orders = new ArrayList<>();
		try {
			orders = (ArrayList<Event>) decoder.readObject();
			return orders;
		}catch(Exception e) {
			System.out.println("orders.xml failed to load");
		}
		decoder.close();
		return orders;
	}
	
	public void saveCustomers() {
		ArrayList<Customer> customers = new ArrayList<>(DAO.getInstance().getCustomers());
		XMLEncoder encoder = createEncoder(CUSTOMERS_XML_PATH);
		encoder.writeObject(customers);
		encoder.close();
		System.out.println("customers.xml saved");
	}
	
	public void saveSellers() {
		ArrayList<Seller> sellers = new ArrayList<>(DAO.getInstance().getSellers());
		XMLEncoder encoder = createEncoder(SELLERS_XML_PATH);
		encoder.writeObject(sellers);
		encoder.close();
		System.out.println("sellers.xml saved");
	}
	
	public void saveEvents() {
		ArrayList<Event> orders = new ArrayList<>(DAO.getInstance().getOrders());
		XMLEncoder encoder = createEncoder(ORDERS_XML_PATH);
		encoder.writeObject(orders);
		encoder.close();
		System.out.println("orders.xml saved");
	}
	
	public void writeReport(Report report) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(REPORT_PATH + report.getTitle() + ".txt"));
			
			bw.write("Reporttype: " + report.getReportType() + "\n");
			
			if(!report.getTitle().isEmpty()) {
				bw.write("Title: " + report.getTitle() + "\n");
			}
			
			if(!report.getIngress().isEmpty()) {
				bw.write("ingress: " + report.getIngress() + "\n");
			}
			
			bw.write("Data: \n");
			for(String s:report.getReportEvents()) {
				bw.write(s + "\n");
			}
			bw.close();
			System.out.println("report written");
			
			
		}catch(Exception e) {
			System.out.println("report failed to write");
		}
	}
	
	private XMLEncoder createEncoder(String fileName) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
		}

		catch (FileNotFoundException fileNotFound) {
			System.out.println("File not found");
		}
		return encoder;
	}
	
	private XMLDecoder createDecoder(String fileName) {
		XMLDecoder decoder = null;

		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));

		}

		catch (FileNotFoundException fileNotFound) {
			System.out.println("File not found");
		}
		return decoder;
	}
	
	
}

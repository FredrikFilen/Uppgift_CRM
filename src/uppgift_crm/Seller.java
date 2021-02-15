package uppgift_crm;

import java.io.Serializable;
import java.util.ArrayList;

public class Seller implements Serializable {
	private String id;
	private String name;
	private String adress;
	private String password;
	private ArrayList<Customer> customerResponsibilites = new ArrayList<>();
	private ArrayList<Event> sales = new ArrayList<>();
	private ArrayList<String> notifications = new ArrayList<>();
	
	
	public Seller() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public ArrayList<Customer> getResponsibleCustomers() {
		return customerResponsibilites;
	}


	public void setResponsibleCustomers(ArrayList<Customer> responsibleCustomers) {
		this.customerResponsibilites = responsibleCustomers;
	}


	public ArrayList<Event> getSales() {
		return sales;
	}


	public void setSales(ArrayList<Event> sales) {
		this.sales = sales;
	}
	
	public void addCustomerResponsibility(Customer customer) {
		this.customerResponsibilites.add(customer);
	}
	
	public void addSalesEvent(Event event) {
		this.sales.add(event);
	}


	public ArrayList<String> getNotifications() {
		return notifications;
	}


	public void setNotifications(ArrayList<String> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotification(String notification) {
		this.notifications.add(notification);
	}
	
	public void clearNotifications() {
		this.notifications.clear();
	}


	@Override
	public String toString() {
		return "Name: " + name;
	}
	
	 
	
	

}

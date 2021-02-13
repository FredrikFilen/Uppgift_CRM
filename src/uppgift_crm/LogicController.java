package uppgift_crm;

import java.util.ArrayList;
import java.util.Iterator;

public class LogicController {
	private static LogicController instance;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	Iterator<Customer> customersIterator = customers.iterator();
	private ArrayList<Seller> sellers = new ArrayList<Seller>();
	
	private Seller selectedSeller;
	private Customer selectedCustomer;
	
	private LogicController() {
		this.setCustomers(Filehandler.getInstance().loadCustomers());
		this.setSellers(Filehandler.getInstance().loadSellers());
		
	}
	
	public static LogicController getInstance() {
		if(instance == null) {
			instance = new LogicController();
		}
		return instance;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	public void addCustomer(String name, String adress) {
		Customer newCustomer = new Customer();
    	newCustomer.setName(name);
    	newCustomer.setAdress(adress);
		this.customers.add(newCustomer);
	}
	
	public ArrayList<Seller> getSellers() {
		return sellers;
	}
	public void setSellers(ArrayList<Seller> sellers) {
		this.sellers = sellers;
	}
	public void addSeller(String id, String password, String name, String adress) {
		Seller newSeller = new Seller();
		newSeller.setId(id);
		newSeller.setPassword(password);
		newSeller.setName(name);
		newSeller.setAdress(adress);
		this.sellers.add(newSeller);
	}

	public Seller getSelectedSeller() {
		return selectedSeller;
	}

	public void setSelectedSeller(Seller selectedSeller) {
		this.selectedSeller = selectedSeller;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}
	
	
}

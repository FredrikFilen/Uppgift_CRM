package uppgift_crm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    	newCustomer.createID();
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
	
	public void createOrder(String product, Double price, int amount) {
		Event newOrder = new Event();
		newOrder.setCustomer(getSelectedCustomer().getName());
		newOrder.setSeller(getSelectedSeller().getName());
		newOrder.setProduct(product);
		newOrder.setPrice(price);
		newOrder.setAmount(amount);
		newOrder.setDate(getDateAndTime());
		newOrder.setNotification(newOrder.createNotification());
		
		selectedCustomer.addSaleEvent(newOrder);
		selectedSeller.addSalesEvent(newOrder);
		
		selectedCustomer.notifyResponsibleSellers(newOrder);
	}
	
	public String getDateAndTime() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String formattedDate = sd.format(date);

		return formattedDate;
	}
	
	
}

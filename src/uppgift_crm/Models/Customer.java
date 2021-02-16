package uppgift_crm.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Customer implements Serializable {
	private String id;
	private String name;
	private String adress;
	private ArrayList<Seller> responsibleSellers = new ArrayList<>();
	private ArrayList<Event> saleEvents = new ArrayList<>();
	
	public Customer() {
		
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

	public ArrayList<Seller> getResponsibleSellers() {
		return responsibleSellers;
	}

	public void setResponsibleSellers(ArrayList<Seller> responsibleSellers) {
		this.responsibleSellers = responsibleSellers;
	}

	public ArrayList<Event> getSaleEvents() {
		return saleEvents;
	}

	public void setSaleEvents(ArrayList<Event> saleEvents) {
		this.saleEvents = saleEvents;
	}
	
	public void addResponsibleSeller(Seller seller) {
		this.responsibleSellers.add(seller);
		System.out.println(seller.getName() + " added to responsibilitylist.");
	}
	
	public void addSaleEvent(Event event) {
		this.saleEvents.add(event);
	}
	
	public void createID() {
		Random rand = new Random();
		String id = Integer.toString((rand.nextInt(10001)));
		this.id = id;
		
	}
	
	public void notifyResponsibleSellers(Event order) {
		for(Seller s: this.responsibleSellers) {
			s.addNotification(order.getNotification());
		}
		
	}
	

	@Override
	public String toString() {
		return "id: " + id + ", name: " + name;
	}
	
	
	
}

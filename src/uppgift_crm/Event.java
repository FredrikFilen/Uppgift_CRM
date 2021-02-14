package uppgift_crm;

public class Event {
	private String customer;
	private String seller;
	private String product;
	private double price;
	private int amount;
	private String date;
	private String notification;
	
	public Event() {
		
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getNotification() {
		return notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
		
	}
	
	public String createNotification() {
		String notification = "Customer: " + this.getCustomer() + " Seller: " + this.getSeller() + " Product: " + this.getProduct() + " Price: " + this.price + " Amount: " + this.amount + " Date: " + this.date;
		return notification;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	
	
	
	
}

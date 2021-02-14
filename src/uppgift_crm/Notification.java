package uppgift_crm;

import java.io.Serializable;

public class Notification implements Serializable {
	private String notificationMessage;
	
	public Notification() {
		
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	
	
}

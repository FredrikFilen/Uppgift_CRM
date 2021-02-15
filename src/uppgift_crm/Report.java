package uppgift_crm;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private String title;
	private String ingress;
	private String reportType;
	private List<String> reportEvents = new ArrayList<>();
	
	public Report() {
		
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIngress() {
		return ingress;
	}

	public void setIngress(String ingress) {
		this.ingress = ingress;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public List<String> getReportEvents() {
		return reportEvents;
	}

	public void setReportEvents(List<String> reportEvents) {
		this.reportEvents = reportEvents;
	}
	
	public void addEvent(String string) {
		this.reportEvents.add(string);
	}
	
	
}

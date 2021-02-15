package uppgift_crm;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {
	private Report report = new Report();
	
	public ReportBuilder addTitle(String title) {
		report.setTitle(title);
		return this;
	}
	
	public ReportBuilder addIngress(String ingress) {
		report.setIngress(ingress);
		return this;
	}
	
	public ReportBuilder addReportType(String reportType) {
		report.setReportType(reportType);
		return this;
	}
	
	public ReportBuilder addEvents(List<String> events) {
		report.setReportEvents(events);
		return this;
	}
	
	public Report build() {
		return report;
	}

}

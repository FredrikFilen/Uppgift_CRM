package uppgift_crm.Controllers;

import java.util.ArrayList;
import java.util.List;

import uppgift_crm.Models.Report;

public class ReportBuilder {
	private Report report = new Report();
	
	public ReportBuilder addTitle(String title) {
		if(title == null) {
			throw new RuntimeException("invalid title");
		}
		report.setTitle(title);
		return this;
	}
	
	public ReportBuilder addIngress(String ingress) {
		if(ingress == null) {
			throw new RuntimeException("invalid ingress");
		}
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
	
	public Report build() throws RuntimeException {
		
		if(report.getTitle()==null) {
			throw new RuntimeException("Missing title");
		}else if(report.getIngress()==null) {
			throw new RuntimeException("Missing ingress");
		}
		return report;
	}

}

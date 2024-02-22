package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG 
{
	  
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter repo = new ExtentSparkReporter(path);
		repo.config().setReportName("Web Automation Results");
		repo.config().setDocumentTitle("Test Results");
		
		ExtentReports ex= new ExtentReports();
		ex.attachReporter(repo);
		ex.setSystemInfo("Tester","Seetha");
		return ex;
	}
}

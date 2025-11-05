package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.config.Config;

public class Extentreportmanager implements ITestListener{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-report-"+timestamp+".html";
		sparkreporter=new ExtentSparkReporter(".\\Reports\\"+repName) ;
		sparkreporter.config().setDocumentTitle("Restassuredautomationproject");//title of report
		sparkreporter.config().setReportName("pet store user apis");//name of the report
		sparkreporter.config().setTheme(Theme.DARK);
		
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("application","pet store user api");
		extent.setSystemInfo("operationg system", System.getProperty("os.name"));
		extent.setSystemInfo("user name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","Qa");
		extent.setSystemInfo("user", "Thrinadh Ram");
		
		
		
		}
	public void onTestSuccess(ITestResult result) {
		
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		
		test.log(Status.PASS, "test pass");
		
	  }
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "test fail");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	  }
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "test skip");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	  }

	public void onFinish(ITestContext context) {
		extent.flush();
	  }

}

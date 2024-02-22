package testComponents;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener
{
	ExtentTest test;
	ExtentReports ex = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extest = new ThreadLocal<ExtentTest>();
	

	@Override
	public void onTestStart(ITestResult result) 
	{
		test = ex.createTest(result.getMethod().getMethodName());
		extest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extest.get().log(Status.PASS, "Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.log(Status.FAIL, "Test Failed");
		extest.get().fail(result.getThrowable());
		try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) 
		{
			e.printStackTrace();
		}
		String fp = null;
		try 
		{
			 fp = getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		extest.get().addScreenCaptureFromPath(fp, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TODO Auto-generated method stub
	
	}

	@Override
	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		ex.flush();
		
	}
}

package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest 
{
	public  WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver intializeDriver() throws IOException
	{

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\global.properties");
		prop.load(fis);
		String browserName= System.getProperty("browser")!= null?System.getProperty("browser"): prop.getProperty("browser");
		if (browserName.contains("chrome"))
		{	ChromeOptions opt = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless"))
			{	
				opt.addArguments("headless");
			}
			driver = new ChromeDriver(opt);
			driver.manage().window().setSize(new Dimension(1440,900));

		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", "C:\\Drivers_Sel\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		driver = intializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void teardown()
	{
		System.out.println("At the After Method");
		driver.quit();
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts= (TakesScreenshot)driver;
		File screenshotfile = ts.getScreenshotAs(OutputType.FILE);
		File savescreenshot = new File(System.getProperty("user.dir")+ "\\reports\\" + testcaseName + ".png");
		FileUtils.copyFile(screenshotfile, savescreenshot);
		return System.getProperty("user.dir")+ "\\reports\\" + testcaseName + ".png";
	}

}

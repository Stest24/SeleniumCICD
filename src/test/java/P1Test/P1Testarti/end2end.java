package P1Test.P1Testarti;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class end2end {
	public static void main(String[] args) throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		
		driver.findElement(By.id("userEmail")).sendKeys("stestelevate@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password1!");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		Thread.sleep(3000);
		
		
		List<WebElement> prods = driver.findElements(By.cssSelector(".mb-3"));
		System.out.println(prods.size());
		WebElement p1 = prods.stream().filter(p->p.findElement(By.cssSelector("b")).getText().contains("ZARA")).findFirst().orElse(null);
		p1.findElement(By.cssSelector("button[class='btn w-10 rounded']")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean Match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().contains("ZARA"));
		System.out.println(Match);
		Assert.assertTrue(Match);
		driver.findElement(By.cssSelector("li[class='totalRow'] button[class='btn btn-primary']")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String str = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		System.out.println(str);
		if (str.contains("FOR"))
		{
			System.out.println("Success");
		}
		else
		{
			System.out.println("Fail");
		}
				
		Thread.sleep(3000);
		driver.quit();
		
	}

}



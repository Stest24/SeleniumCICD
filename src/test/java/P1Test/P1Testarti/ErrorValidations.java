package P1Test.P1Testarti;
import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import java.io.IOException;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

public class ErrorValidations extends BaseTest
{
	@Test(retryAnalyzer= Retry.class)

	public void loginErrorValidation() throws IOException, InterruptedException
	{
		
		//LandingPage landingPage = launchApplication();
		landingPage.loginApplication("ststelevate@gmail.com", "Password1!");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		ProductCatalogue productPage = landingPage.loginApplication("stestelevate@gmail.com", "Password1!");

		productPage.addProductToCart(productName);
		CartPage cartPage=  productPage.goToCartPage();
		
		Boolean match =  cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
			

	}
}


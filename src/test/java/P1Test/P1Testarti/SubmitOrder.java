package P1Test.P1Testarti;
import java.io.IOException;
import org.junit.Assert;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;
import org.testng.annotations.Test;


public class SubmitOrder extends BaseTest
{
	@Test

	public void submitOrder() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		ProductCatalogue productPage = landingPage.loginApplication("stestelevate@gmail.com", "Password1!");

		
		productPage.addProductToCart(productName);
		CartPage cartPage=  productPage.goToCartPage();
		
		Boolean match =  cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage  checkoutpage =  cartPage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage  = checkoutpage.submitOrder();
		
		String confirmationmsg = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	

	
}

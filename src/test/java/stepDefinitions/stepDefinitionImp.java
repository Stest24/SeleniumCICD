package stepDefinitions;

import java.io.IOException;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class stepDefinitionImp extends BaseTest
{
	public LandingPage landingpage;
	public ProductCatalogue productPage;
	public ConfirmationPage confirmationpage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingpage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void loggeg_in_username_and_password(String Username, String password)
	{
		productPage = landingPage.loginApplication(Username,password);
	}
	 @When("^I add product (.+) to cart$")
	 public void i_add_product_to_cart(String productName) throws InterruptedException
	 {
		 productPage.addProductToCart(productName);
	 }
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_submit_order(String productName)
	 {
		 	CartPage cartPage=  productPage.goToCartPage();
			Boolean match =  cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage  checkoutpage =  cartPage.goToCheckout();
			checkoutpage.selectCountry("India");
			confirmationpage  = checkoutpage.submitOrder();
	 }
	 @Then ("{string}  message is displayed on ConfirmationPage")
	 public void message_displayed_ConfirmationPage(String string)
	 {
		 String confirmationmsg = confirmationpage.getConfirmationMessage();
			Assert.assertTrue(confirmationmsg.equalsIgnoreCase(string));
			driver.quit();
	 }
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string)
	 {
		 Assert.assertEquals(string,landingPage.getErrorMessage());
		 driver.quit();
	 }
	 
}

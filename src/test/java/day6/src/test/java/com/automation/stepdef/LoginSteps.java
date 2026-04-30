package com.automation.stepdef;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.automation.pom.CartPage;
import com.automation.pom.CheckoutPage;
import com.automation.pom.InventoryPage;
import com.automation.pom.LoginPage;
import com.automation.pom.ProductPage;
import com.automation.utils.DriverUtils;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver;
	LoginPage lp = new LoginPage();
	InventoryPage ip = new InventoryPage();
	ProductPage pp = new ProductPage();
	CartPage cp = new CartPage();
	CheckoutPage cop = new CheckoutPage();

	@Given("user in home page")
	public void user_in_home_page() {
		driver = DriverUtils.getDriver();
		driver.get("https://www.saucedemo.com/");
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "Swag Labs");
	}

	@When("user enter {string} and {string}")
	public void user_enter_and(String string, String string2) {
		lp.verifyLoginFields(string, string2);
	}

	@And("clicks loginBtn")
	public void clicks_login_btn() {
		lp.verifyLoginBtn();
	}

	@Then("products should be visible")
	public void products_should_be_visible() {
		assertTrue(driver.getCurrentUrl().contains("inventory"));
	}

	@Given("user selects a {string}")
	public void user_selects_a_(String product) {
		ip.clickProduct(product);
	}

	@When("user add product to cart")
	public void user_add_product_to_cart() {
		pp.clickAddToCartBtn();
	}

	@When("goes to cart page")
	public void goes_to_cart_page() {
		pp.clickCartIcon();
	}

	@Then("{string} visible in the cart")
	public void visible_in_the_cart(String product) {
		cp.isCartItemPresent(product);
	}

	@When("user has a {string} in the cart")
	public void user_has_a_in_the_cart(String product) {
		cp.isCartItemPresent(product);
	}

	@When("user proceeds to checkout")
	public void user_proceeds_to_checkout() {
		cp.selectCheckoutBtn();
	}

	@When("user enters {string}, {string} and {string}")
	public void user_enters_and(String string, String string2, String string3) {
		cop.enterYourInfo(string, string2, string3);
	}

	@When("user finishs the order")
	public void user_finishs_the_order() {
		cop.clickFinish();
	}

	@Then("order should be placed")
	public void order_should_be_placed() {
		assertTrue(cop.orderPlacedSuccessfully());
	}

}

package day4.com.amazon.test;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import day4.com.amazon.pom.CartPage;
import day4.com.amazon.pom.CheckoutPage;
import day4.com.amazon.pom.LoginEmailPage;
import day4.com.amazon.pom.LoginPasswordPage;
import day4.com.amazon.pom.LogoutPage;
import day4.com.amazon.pom.SearchPage;
import day4.com.amazon.pom.SelectPage;

import day4.com.amazon.utils.DriverUtils;
import io.github.cdimascio.dotenv.Dotenv;

@Listeners(day4.com.amazon.utils.MyListener.class)
public class TestAmazon {
	@BeforeTest
	public void setup(ITestContext context) {
		DriverUtils.getDriver().get("https://www.amazon.in/ref=nav_logo");
	}

	@Test(dataProvider = "loginData")
	public void validateLoginPage(String email, String password) {
		LoginEmailPage lep = new LoginEmailPage();
		lep.signinBtn.click();
		lep.verifyEmail(email);
		lep.verifyContinueBtn();
		LoginPasswordPage lpp = new LoginPasswordPage();
		lpp.verifyPassword(password);
		lpp.verifySigninBtn();
//		assert false;
	}

	@Test(dataProvider = "searchData")
	public void validateSearchPage(String productName) {
		SearchPage sp = new SearchPage();
		sp.verifySearch(productName);
		sp.verifyClickSearch();
	}

	@Test(dependsOnMethods = "validateSearchPage", dataProvider = "selectData")
	public void validateSelectProduct(String productName) {
		SelectPage sp = new SelectPage();
		sp.selectProduct(productName);
	}

	@Test(dependsOnMethods = "validateSelectProduct")
	public void validateAddToCart() {
		CartPage cp = new CartPage();
		cp.verifyAddToCart();
	}

	@Test(dependsOnMethods = "validateAddToCart", dataProvider = "checkoutData")
	public void validateCheckout(String creditCardNum, String accHolderName, String expiryMonth, String expiryYear) {
		CheckoutPage cp = new CheckoutPage();
		cp.verifyCheckout(creditCardNum, accHolderName, expiryMonth, expiryYear);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cp.clickCancel();
	}

	@Test(dependsOnMethods = { "validateLoginPage", "validateAddToCart" })
	public void validateLogout() {
		LogoutPage lp = new LogoutPage();
		lp.verifyLogout();
	}

	@AfterClass
	public void tearDown() {
		System.out.println("completed");
	}

	@DataProvider(name = "loginData")
	public Object[][] provideLoginData() {
		Dotenv dot = Dotenv.load();

		String password = dot.get("AMAZON_PASSWORD");

		return new Object[][] { { "loamramg@gmail.com", password } };
	}

	@DataProvider(name = "searchData")
	public Object[][] provideSearchData() {
		return new Object[][] { { "peanut butter chocolate" } };
	}

	@DataProvider(name = "selectData")
	public Object[][] provideSelectData() {
		return new Object[][] { { "DiSano Chocolate Peanut Butter" } };
	}

	@DataProvider(name = "checkoutData")
	public Object[][] creditCardData() {
		return new Object[][] { { "1234567890", "qwertyuio", "02", "2030" } };
	}
}

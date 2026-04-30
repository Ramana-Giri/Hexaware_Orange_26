package day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DepndsOnMethodTest {
	
	WebDriver driver;
	String baseUrl = "https://demowebshop.tricentis.com/";
	@BeforeTest
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	
	@Test(dataProvider = "loginData")
	public void login(String email, String password) {
		driver.get(baseUrl + "login/");
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
	}
	
	@Test(dependsOnMethods = {"login"}, dataProvider = "searchData") 
	public void search(String product) {
		driver.findElement(By.cssSelector("input[value='Search store']")).sendKeys(product);
		driver.findElement(By.cssSelector("input[value='Search']")).click();
		driver.findElement(By.xpath("//div[@class='product-item'][1]")).click();
		driver.findElement(By.xpath("//input[@value='Add to cart']")).click();
	}
	
	@Test(dependsOnMethods = {"search"})
	public void cart() {
		driver.findElement(By.partialLinkText("Shopping cart")).click();
		driver.findElement(By.name("termsofservice")).click();
		driver.findElement(By.id("checkout")).click();
	}
	
	@Test(dependsOnMethods = {"cart"})
	public void checkout() throws InterruptedException {
		Select billingAddressElement = new Select(driver.findElement(By.name("billing_address_id")));
		billingAddressElement.selectByVisibleText("New Address");
		driver.findElement(By.cssSelector("#BillingNewAddress_FirstName")).sendKeys("Ramana");
		driver.findElement(By.cssSelector("#BillingNewAddress_LastName")).sendKeys("Giri");
		// click new address
				WebElement addressSelectElement = driver.findElement(By.cssSelector("#billing-address-select"));
				Select selectNewAddress = new Select(addressSelectElement);
				selectNewAddress.selectByVisibleText("New Address");
				
				// select country
				WebElement countrySelectElement = driver.findElement(By.cssSelector("#BillingNewAddress_CountryId"));
				Select selectCountry = new Select(countrySelectElement);
				selectCountry.selectByVisibleText("India");
				
				// fill city
				WebElement cityElement = driver.findElement(By.xpath("//label[@for='BillingNewAddress_City']"));
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).toRightOf(cityElement)
						).sendKeys("Chennai");
				
				// fill address one
				WebElement addressOneElement = driver.findElement(RelativeLocator.with(By.tagName("input")).below(cityElement));
				addressOneElement.sendKeys("Sholinganallur");
				
				// fill phone number
				WebElement phoneElement = driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"));
				phoneElement.sendKeys("1234567890");
				
				// fill postal code
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).above(phoneElement)
						).sendKeys("600119");
				
				// click continue
				driver.findElement(By.cssSelector("input[value='Continue']")).click();
				
				// click continue for shipping address
				WebElement backElement = driver.findElement(By.linkText("Back"));
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).below(backElement)).click();
				Thread.sleep(5000);
				
				// click continue shipping method
				WebElement paymentMethodElement = driver.findElement(By.xpath("//h2[text()='Payment method']"));
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).above(paymentMethodElement)).click();
				Thread.sleep(5000);

				// click continue for payment method
				WebElement paymentInformationElement = driver.findElement(By.xpath("//h2[text()='Payment information']"));
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).above(paymentInformationElement)).click();
				Thread.sleep(5000);

//				// click continue for payment information
				WebElement confirmOrderElement = driver.findElement(By.xpath("//h2[text()='Confirm order']"));
				driver.findElement(
						RelativeLocator.with(By.tagName("input")).above(confirmOrderElement)).click();
				Thread.sleep(5000);

//				// click confirm for order confirmation
				driver.findElement(
						RelativeLocator.with(By.xpath("(//input[@type='button'])[7]"))
						).click();
	}
	
	@AfterTest
	public void teardown() {
		driver.findElement(By.partialLinkText("Log out")).click();
		driver.quit();
	}
	
	@DataProvider(name = "searchData")
	public Object[][] searchData() {
		return new Object[][] {{"Computer"}};
	}
	
	@DataProvider(name = "loginData")
	public Object[][] loginData() {
		return new Object[][] {{"somerandomemail@gmail.com", "ramana1234"}};
	}
}

package day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Demo_Website_Full_WorkFlow {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // this line is used to wait for max of 10 secs for each task to complete
		
		// id selector
		driver.findElement(By.cssSelector("#Email")).sendKeys("somerandomemail@gmail.com");
		// class selector
		driver.findElement(By.cssSelector(".password")).sendKeys("ramana1234");
		
		// attribute selector
		WebElement rememberMeLabel = driver.findElement(By.cssSelector("label[for='RememberMe']"));
		
		// toLeftOf xpath selector
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).toLeftOf(rememberMeLabel)
				).click();
		
		// combining attributes
		driver.findElement(By.cssSelector("input[value='Log in'][type='submit']")).click();
		
		// search functionality
		WebElement searchBoxElement = driver.findElement(By.cssSelector("input[value='Search store']"));
		searchBoxElement.sendKeys("Computer");
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).toRightOf(searchBoxElement)
				).click();
		
//		Thread.sleep(1000);
		
		// select functionality
		driver.findElement(By.xpath("(//div[@class='product-item'])[1]")).click();
		
		// add to cart  functionality
		driver.findElement(By.xpath("//input[starts-with(@id,'add-to-cart-button-')]")).click();
		
//		Thread.sleep(5000);
		
		// click shopping cart
		driver.findElement(By.partialLinkText("Shopping cart")).click();
		
		// modify the cart quantity to 1
//		WebElement quantityElement = driver.findElement(By.name("itemquantity6604162")); // name is changing so gotta find another way
		WebElement quantityElement = driver.findElement(By.xpath("//input[@class='qty-input']"));
		quantityElement.clear();
		quantityElement.sendKeys("1");
		
		//update the cart
		driver.findElement(By.name("updatecart")).click();
//		Thread.sleep(3000);
		
		// click terms button
		driver.findElement(By.cssSelector("#termsofservice")).click();
		
		// click checkout
		driver.findElement(By.name("checkout")).click();
		
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
//		Thread.sleep(5000);
		
		
		// click continue for shipping address
		WebElement backElement = driver.findElement(By.linkText("Back"));
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).below(backElement)).click();
//		Thread.sleep(5000);
		
		// click continue shipping method
		WebElement paymentMethodElement = driver.findElement(By.xpath("//h2[text()='Payment method']"));
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).above(paymentMethodElement)).click();
//		Thread.sleep(5000);

		// click continue for payment method
		WebElement paymentInformationElement = driver.findElement(By.xpath("//h2[text()='Payment information']"));
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).above(paymentInformationElement)).click();
//		Thread.sleep(5000);

//		// click continue for payment information
		WebElement confirmOrderElement = driver.findElement(By.xpath("//h2[text()='Confirm order']"));
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).above(confirmOrderElement)).click();
//		Thread.sleep(5000);

//		// click confirm for order confirmation
		driver.findElement(
				RelativeLocator.with(By.xpath("(//input[@type='button'])[7]"))
				).click();
	}
	
}

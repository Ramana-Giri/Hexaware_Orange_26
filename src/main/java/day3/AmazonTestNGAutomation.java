package day3;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTestNGAutomation {
	
	WebDriver driver;
	WebDriverWait wait;
	String parentHandle;
	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.get("https://www.amazon.in/login");
		driver.findElement(By.partialLinkText("Click here to go back")).click();
		parentHandle = driver.getWindowHandle();
	}
	
	@Test
	@Parameters({"email", "password"})
	public void login(String email, String password) {

		driver.findElement(By.cssSelector("a[data-nav-ref=\"nav_ya_signin\"]")).click();

		WebElement loginField = driver.findElement(By.id("ap_email_login"));

		loginField.sendKeys(email);

		driver.findElement(RelativeLocator.with(By.tagName("input")).below(loginField)).click();

		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.sendKeys(password);

		driver.findElement(RelativeLocator.with(By.tagName("input")).below(passwordField)).click();
	}
	
	@Test(dependsOnMethods = {"login"}, dataProvider = "searchProductData")
	void searchProduct(String product) {
		WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		searchBar.sendKeys(product);
		driver.findElement(RelativeLocator.with(By.tagName("input")).near(searchBar)).click();
	}
	
	@Test(dependsOnMethods = {"searchProduct"}, dataProvider = "selectProductData")
	void selectProduct(String selectProduct) throws InterruptedException {
		WebElement product = driver.findElement(By.partialLinkText(selectProduct));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
//		Thread.sleep(2000);
		product.click();
	}
	
	@Test(dependsOnMethods = {"selectProduct"})
	void switchToProductHandle() {
//		System.out.println(parent_handle);
		Set<String> handles = driver.getWindowHandles();
//		System.out.println(handles);
		for (String handle1 : handles) {
			if (!handle1.equals(parentHandle)) {
				driver.switchTo().window(handle1);
//				System.out.println("Swithced to new window : " + handle1);
				break;
			}
		}
	}
	
	@Test(dependsOnMethods = {"switchToProductHandle"})
	void addProductToCart() throws InterruptedException {
//		driver.get("https://www.amazon.in/vivo-X300-Pro-Additional-Exchange/dp/B0G26FN2MK/ref=sr_1_6?dib=eyJ2IjoiMSJ9.lFGsBaNdfbLuYPa9gAhRSJzz2sFp3xvlQpd453pMzHXgI7yH0KMFS3Vr47BkCuHtM_hcgTxXADdKS5wJQ9P2iKmJo-Yl17OVfD5f9hbukaB9uucr2_cPkMLvaoMkGfsJFtP33FPzhqKj7zcWUiY8UASEpdPenvEKv-5pQVttAbf1HxhTJAxYGof_Yp2tHPcJAQyD1RZyy5631ztBq2e95SgJE-xvdHaLtihZI56bjMM.y8tBa4Sjcg6B88J-9_OAJEcfZl0sbtGgq9GZYAlUKJk&dib_tag=se&keywords=vivo+x+series+phones&qid=1777117386&sr=8-6");
		WebElement buyButton = driver.findElement(By.xpath("//input[@title='Buy Now']"));
		System.out.println("buy button found");
		driver.findElement(RelativeLocator.with(By.tagName("input")).above(buyButton)).click();
		System.out.println("Clicked add to cart button");
		WebElement cartBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("#hctp-attach-side-sheet input.a-button-input")));

		cartBtn.click();

		driver.findElement(By.name("proceedToRetailCheckout")).click();
	}
	
	@Test(dependsOnMethods = {"addProductToCart"}, dataProvider = "creditCardData")
	void addPaymentMethod(String creditCardNumber, String name, String expiryMonth, String expiryYear) {
		driver.findElement(By.xpath("//input[@value='SelectableAddCreditCard']")).click();

		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.partialLinkText("Add a new credit or debit card")));
		driver.findElement(By.partialLinkText("Add a new credit or debit card")).click();

		wait.until(
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[name='ApxSecureIframe']")));
		driver.findElement(By.name("addCreditCardNumber")).sendKeys(creditCardNumber);
		driver.findElement(By.name("ppw-accountHolderName")).sendKeys(name);
		Select monthSelect = new Select(driver.findElement(By.name("ppw-expirationDate_month")));
		monthSelect.selectByVisibleText(expiryMonth);
		Select yearSelect = new Select(driver.findElement(By.name("ppw-expirationDate_year")));
		yearSelect.selectByVisibleText(expiryYear);
		driver.findElement(By.xpath("//button[text()='Cancel']")).click();
	}
	
	@DataProvider(name = "searchProductData")
	public Object[][] searchProductData() {
		return new Object[][] {{"vivo x series phones"}};
	}
	
	@DataProvider(name = "selectProductData")
	public Object[][] selectProductData() {
		return new Object[][] {{"X300 Pro 5G"}};
	}

	@DataProvider(name = "creditCardData")
	public Object[][] creditCardData() {
		return new Object[][] {{"1234567890", "qwertyuio", "02", "2030"}};
	}
	
	@AfterClass
	public void teardown() {
		System.out.println("completed");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@aria-label='Amazon.in']")).click();
		driver.findElement(By.xpath("//button[@aria-label='Expand Account and Lists']")).click();		
		driver.findElement(By.id("nav-item-signout")).click();
		driver.quit();
	}
}

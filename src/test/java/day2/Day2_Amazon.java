package day2;

import java.time.Duration;
import java.util.Set;

import io.github.cdimascio.dotenv.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2_Amazon {

	static WebDriver driver = new ChromeDriver();
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));

	static {
		WebDriverManager.chromedriver().setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().window().maximize();
	}

	public static void main(String[] args) throws InterruptedException {
		driver.get("https://www.amazon.in/login"); // Intentionally gave wrong url cuz when I give crt url it get
													// detected as bot
		String parent_handle = driver.getWindowHandle();
		goBackToHomePage();
		login();
		searchProduct();
		selectProduct();
		switchToProductHandle(parent_handle);
		addProductToCart();
		addPaymentMethod();
//		driver.quit();
	}

	private static void addPaymentMethod() {
		driver.findElement(By.xpath("//input[@value='SelectableAddCreditCard']")).click();

		wait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.partialLinkText("Add a new credit or debit card")));
		driver.findElement(By.partialLinkText("Add a new credit or debit card")).click();

		wait.until(
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[name='ApxSecureIframe']")));
		driver.findElement(By.name("addCreditCardNumber")).sendKeys("12131234567");
		driver.findElement(By.name("ppw-accountHolderName")).sendKeys("abcd");
		Select monthSelect = new Select(driver.findElement(By.name("ppw-expirationDate_month")));
		monthSelect.selectByVisibleText("05");
		Select yearSelect = new Select(driver.findElement(By.name("ppw-expirationDate_year")));
		yearSelect.selectByVisibleText("2028");
	}

	private static void addProductToCart() throws InterruptedException {
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

	private static void switchToProductHandle(String parent_handle) {
		System.out.println(parent_handle);
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		for (String handle1 : handles) {
			if (!handle1.equals(parent_handle)) {
				driver.switchTo().window(handle1);
				System.out.println("Swithced to new window : " + handle1);
				break;
			}
		}
	}

	private static void selectProduct() throws InterruptedException {
		WebElement product = driver.findElement(By.partialLinkText("X300 Pro 5G (Dune Gold, 16GB RAM, 512GB Storage)"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
//		Thread.sleep(2000);
		product.click();
	}

	private static void searchProduct() {
		WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		searchBar.sendKeys("vivo x series phones");

		driver.findElement(RelativeLocator.with(By.tagName("input")).near(searchBar)).click();
	}

	private static void goBackToHomePage() {
		driver.findElement(By.partialLinkText("Click here to go back")).click();
	}

	private static void login() {
		Dotenv dotenv = Dotenv.load();
		String password = dotenv.get("AMAZON_PASSWORD");

		driver.findElement(By.cssSelector("a[data-nav-ref=\"nav_ya_signin\"]")).click();

		WebElement loginField = driver.findElement(By.id("ap_email_login"));

		loginField.sendKeys("loamramg@gmail.com");

		driver.findElement(RelativeLocator.with(By.tagName("input")).below(loginField)).click();

		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.sendKeys(password);

		driver.findElement(RelativeLocator.with(By.tagName("input")).below(passwordField)).click();
	}
}

package day2;

import java.time.Duration;

import io.github.cdimascio.dotenv.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
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
		goBackToHomePage();
//		login();
		searchProduct();
		selectProduct();
		
//		driver.quit();
	}

	private static void selectProduct() throws InterruptedException {
		WebElement product = driver.findElement(By.partialLinkText("X300 Pro 5G (Dune Gold, 16GB RAM, 512GB Storage)"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
//		Thread.sleep(3000);
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

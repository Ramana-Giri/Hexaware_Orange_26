package day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2_Myntra {
	static WebDriver driver = new ChromeDriver();
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	static{
		WebDriverManager.chromedriver().setup();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().window().maximize();
	}
	
	public static void main(String[] args) throws InterruptedException {
		driver.get("https://www.myntra.com/login");
//		skipLoginPage();
//		login();
//		Thread.sleep(60000);
//		goToMobileCategory();
//		selectProduct();
//		scrolldown();
//		clickBuyNow();
//		driver.quit();
	}
	

	public static void clickBuyNow() {
		WebElement buyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[starts-with(text(), 'Buy now')]")));
		buyElement.click();
//		driver.findElement(By.xpath("//div[starts-with(text(), 'Buy now')]")).click();
		
	}

	public static void selectProduct() {
		driver.findElement(By.cssSelector("a[href^='/nothing-phone-3a-lite-black']")).click();
		
		
	}

	public static void scrolldown() {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0, 1000)");
	}

	public static void skipLoginPage() {
		driver.findElement(By.className("b3wTlE")).click();
		
	}

	public static void goToMobileCategory() {
		driver.findElement(By.xpath("//div[starts-with(text(),'Mobile')]")).click();
	}

	public static void login() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement loginField = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[starts-with(text(), 'By continuing')]"))
				);
		
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).above(loginField)
				).sendKeys("loamramg@gmail.com");
		
		driver.findElement(By.xpath("//button[contains(text(), 'Request OTP')]")).click();
		
	}
}

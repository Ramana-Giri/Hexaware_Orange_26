package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Testing_Login_Module {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		testLoginWithValidMailInvalidPassword(driver);
		Thread.sleep(1000);
		
		testLoginWithInvalidMailValidPassword(driver);
		Thread.sleep(1000);
		
		testLoginWithInvalidMailInvalidPassword(driver);
		Thread.sleep(1000);
		
		testLoginWithValidMailValidPassword(driver);
		Thread.sleep(1000);
	}
	
	public static void testLoginWithValidMailValidPassword(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		
		String validEmail = "somerandomemail@gmail.com";
		String validPassword = "ramana1234";
		
		driver.findElement(By.cssSelector("#Email")).sendKeys(validEmail);
		driver.findElement(By.cssSelector(".password")).sendKeys(validPassword);
		
		driver.findElement(By.cssSelector(".login-button")).click();
	}
	
	public static void testLoginWithValidMailInvalidPassword(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		String validEmail = "somerandomemail@gmail.com";
		String invalidPassword = "invalidPassword";
		
		driver.findElement(By.name("Email")).sendKeys(validEmail);
		driver.findElement(By.name("Password")).sendKeys(invalidPassword);
		
		driver.findElement(By.cssSelector(".login-button")).click();
	}
	
	public static void testLoginWithInvalidMailValidPassword(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		String invalidEmail = "invalid@gmail.com";
		String validPassword = "ramana1234";
		
		driver.findElement(By.name("Email")).sendKeys(invalidEmail);
		driver.findElement(By.name("Password")).sendKeys(validPassword);
		
		driver.findElement(By.cssSelector(".login-button")).click();
	}
	
	public static void testLoginWithInvalidMailInvalidPassword(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		String invalidEmail = "invalid@gmail.com";
		String invalidPassword = "invalidPassword";
		
		driver.findElement(By.name("Email")).sendKeys(invalidEmail);
		driver.findElement(By.name("Password")).sendKeys(invalidPassword);
		
		driver.findElement(By.cssSelector(".login-button")).click();
	}
}

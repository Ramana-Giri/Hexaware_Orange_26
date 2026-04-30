package day1;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Dynamic_Xpath {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// static
		WebElement usernameField = driver.findElement(By.name("username"));
		
		// dynamic
		
		WebElement passwordField = driver.findElement(
			    with(By.tagName("input")).below(usernameField)
			);
		
		WebElement loginButton = driver.findElement(
			    with(By.tagName("button")).below(passwordField)
			);
		

		WebElement passwordLabel = driver.findElement(
		    with(By.tagName("label")).above(passwordField)
		);
		
		System.out.println("Text above password field: " + passwordLabel.getText());


		usernameField.sendKeys("Admin");
		passwordField.sendKeys("admin123");
		loginButton.click();


		boolean isLoggedIn = driver.getCurrentUrl().contains("dashboard");
		System.out.println("Login Successful: " + isLoggedIn);
		
		
//		driver.quit();
		
		
	}
}

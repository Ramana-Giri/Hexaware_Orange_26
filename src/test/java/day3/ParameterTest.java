package day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterTest {
	
	
	 WebDriver driver;
	
//	public  void main(String[] args) {
//		createDriver();
//		loginTest("Admin", "admin123");
//	}
	
	@BeforeTest
	public  void createDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test
	@Parameters({"u", "p"})
	public  void loginTest(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.className("orangehrm-login-button")).click();	
	}
	
	@AfterTest
	public  void closeDriver() {
		if (driver != null)
			driver.quit();
	}
}

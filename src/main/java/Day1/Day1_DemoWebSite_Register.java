package Day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_DemoWebSite_Register {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/register");
		
		Thread.sleep(5000);
		
		driver.findElement(By.name("Gender")).click(); // this will click the first radio button
		
		// Absolute Xpath
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/form/div/div[2]/div[2]/div[2]/div[2]/input")).sendKeys("ramana");
		
		// Relative Xpath
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("giri");
		
		// Xpath using text()
		driver.findElement(By.id("Email")).sendKeys("ramana1@gmail.com");
		
		// Xpath using contains()
		driver.findElement(By.xpath("//input[contains(@name, 'Password')]")).sendKeys("password123");
		
		driver.findElement(By.xpath("//input[contains(@name, 'ConfirmPassword')]")).sendKeys("password123");
		
		
		driver.findElement(By.name("register-button")).click();
		Thread.sleep(10000);
		driver.quit();
		
	}
}

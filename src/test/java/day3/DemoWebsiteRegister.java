package day3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoWebsiteRegister {
	    WebDriver driver;
	    WebDriverWait wait;
        @BeforeTest
        public void StartPage() {
        	WebDriverManager.chromedriver().setup();
        	driver=new ChromeDriver();
        	driver.get("https://demowebshop.tricentis.com/");
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        	driver.manage().window().maximize();
        	wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        	driver.findElement(By.linkText("Register")).click();
        	wait.until(ExpectedConditions.urlContains("register"));
        }
        
        @Test()
        @Parameters({"gender","firstName","lastName","email","password","confirmPassword"})
        public void register(String gender,String firstName,String lastName,String email,String password,String confirmPassword) {
        	
        	//for Gender selection
        	WebElement gLabel=driver.findElement(By.xpath("//label[text()='"+ gender +"']"));
        	driver.findElement(with(By.tagName("input")).toLeftOf(gLabel)).click();
        	
        	//for First Name Input
        	driver.findElement(By.id("FirstName")).sendKeys(firstName);
        	
        	//for Last Name Input
        	driver.findElement(By.id("LastName")).sendKeys(lastName);
        	
        	//for Email Input
        	driver.findElement(By.cssSelector("#Email")).sendKeys(email);
        	
        	//for Password and Confirm Password
        	driver.findElement(By.xpath("//input[@name='Password']")).sendKeys(password);
        	driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys(confirmPassword);
        	
        	//Registration checking
        	driver.findElement(By.id("register-button")).click();
        	boolean isRegistered=wait.until(ExpectedConditions.urlContains("registerresult"));
        	System.out.println("Rehgistration Successful: "+isRegistered);
        }
        
        @AfterTest
        public void closeDown() {
        	if(driver!=null)
        		driver.quit();
        }
        
}
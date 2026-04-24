package day2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day2Wait {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
//		useImplicitWait(driver);
		
//		takeScreenShot(driver);
		
//		handleAlerts(driver);
		
//		playWithFrames(driver);
		
		playWithScroll(driver);
		driver.quit();
		
//		boolean logoutEle = wait.until(
//				ExpectedConditions.textToBePresentInElementLocated(By.className("ico-logout"), "Log out")
//				);
//		
		
		
//		WebElement ele = wait.until(
//		    ExpectedConditions.visibilityOfElementLocated(By.id("username"))
//		);
		
//		wait.until(
//		    ExpectedConditions.presenceOfElementLocated(By.id("username"))
//		);
		
		 
//		wait.until(
//		            ExpectedConditions.textToBePresentInElementLocated(
//		                By.className("ico-logout"), "Log out"
//		            )
//		        );
	}
	
	public static void useImplicitWait(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("Email")).sendKeys("somerandomemail@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("ramana1234");
		driver.findElement(By.className("login-button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("accoount"), "somerandomemail@gmail.com"))) {
			System.out.println("Login successful");
			driver.findElement(By.className("ico-logout")).click();
		} else {
			System.out.println("Login not successful");
		}
	}
	
	public static void  useExplicitWait(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			
		WebElement emailEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
		emailEle.sendKeys("somerandomemail@gmail.com");
		
		WebElement passwordEle = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
		passwordEle.sendKeys("ramana1234");
		
		WebElement btn = wait.until(
			    ExpectedConditions.elementToBeClickable(By.cssSelector(".login-button"))
			);
		btn.click();
	}
	
	public static void takeScreenShot(WebDriver driver) {
		
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.get("https://opensource-demo.orangehrmlive.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))
		        );
		
//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());
//        
//        try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		File dest = new File("Screenshots/loginpage.png");
		
		try {
			 FileHandler.copy(src, dest);
			System.out.println("Screenshot saved successfully");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void handleAlerts(WebDriver driver) {
		driver.get("https://demoqa.com/alerts");
		driver.findElement(By.id("confirmButton")).click();
		Alert alert = driver.switchTo().alert();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(alert.getText());
//		alert.accept();
		alert.dismiss();
		
	}
	
	public static void playWithFrames(WebDriver driver) {
		driver.get("https://demoqa.com/frames");
		// switching to child frame using index value
		driver.switchTo().frame(1);
		
		String frameText = driver.findElement(By.tagName("h1")).getText();
		System.out.println(frameText);
		
		driver.switchTo().frame("frame2");
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		
		
		driver.switchTo().parentFrame();
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		
		// switch back to main frame
		driver.switchTo().defaultContent();
		
	}
	
	public static void playWithScroll(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".title"))
				);
		
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0, 1000)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		j.executeScript("window.scrollBy(0,-1000)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

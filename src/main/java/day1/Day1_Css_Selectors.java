package day1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Css_Selectors {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demowebshop.tricentis.com/login");
		Thread.sleep(5000);
		
		// id selector
		driver.findElement(By.cssSelector("#Email")).sendKeys("somerandomemail@gmail.com");
		// class selector
		driver.findElement(By.cssSelector(".password")).sendKeys("ramana1234");
		
		// attribute selector
		WebElement rememberMeLabel = driver.findElement(By.cssSelector("label[for='RememberMe']"));
		
		// toLeftOf xpath selector
		driver.findElement(
				RelativeLocator.with(By.tagName("input")).toLeftOf(rememberMeLabel)
				).click();
		
		
		
		// combining attributes
		driver.findElement(By.cssSelector("input[value='Log in'][type='submit']")).click();
		
		// partial link
		// ^ means starts with
		// $ means ends with
		// * means contains
		
//		WebElement shopingCartElement = driver.findElement(By.cssSelector("a[href^='cart']"));
//		shopingCartElement.click();
		
		// partial link
		WebElement shopingCartElement = driver.findElement(By.partialLinkText("cart"));
		shopingCartElement.click();
		
		// link text
		System.out.println(driver.findElement(By.linkText("Search")).getText());
	}
}

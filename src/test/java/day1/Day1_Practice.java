package day1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Day1_Practice {
	public static void main(String[] args) throws InterruptedException {
		// Connecting chrome driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
//		exploreDriverMethods(driver);
		exploreByClassMethods(driver);
	}
	
	public static void exploreByClassMethods(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/login");
		
		String emailId = driver.findElement(By.id("Email")).getText();
		System.out.println(emailId);
		
		String linkText = driver.findElement(By.linkText("Forgot password?")).getText();
		System.out.println(linkText);
		
		String partialLinkText = driver.findElement(By.partialLinkText("Shopping")).getText();
		System.out.println(partialLinkText);
		
		String nameText = driver.findElement(By.name("RememberMe")).getText();
		System.out.println(nameText);
		
		String tagNameText = driver.findElement(By.tagName("p")).getText();
		System.out.println(tagNameText);
		
		// relative xpath
		String xpathText = driver.findElement(By.xpath("//label[@for='Email']")).getText();
		System.out.println(xpathText);
		
		// absolute xpath
		String absoluteXpathText = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[3]/label")).getText();
		System.out.println(absoluteXpathText);
		
		List<WebElement> classNameTextList = driver.findElements(By.className("title"));
		for(WebElement classNameText : classNameTextList) System.out.println(classNameText.getText());
		
		// . means class
		// # id
		// no prefix means tag
		// tagName[attribute=value]
		// tagName[attribute=value][attribute2=value2]
		String cssSelectorText = driver.findElement(By.cssSelector(".buttons")).getText();
		System.out.println("Css selector : "+ cssSelectorText); // we are not getting any text output 
		
		driver.quit();
	}
	
	public static void exploreDriverMethods(WebDriver driver) {
		driver.get("https://demowebshop.tricentis.com/");
		
		// get the title of the website you are on
		String title = driver.getTitle();
		System.out.println(title);
		
		// get url of the website
		String url = driver.getCurrentUrl();
		System.out.println(url);
		
		// get html code of the website 
//		String htmlCode = driver.getPageSource();
//		System.out.println(htmlCode);
		
		// get the current window or tab unique value
//		String explore = driver.getWindowHandle();
//		System.out.println(explore);
		
		List<WebElement> elements = driver.findElements(By.tagName("h2"));
		for(WebElement element : elements) {System.out.println(element.getText());}
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		driver.get("https://bing.com");

		driver.close();
		
		driver.quit();
	}
	
}

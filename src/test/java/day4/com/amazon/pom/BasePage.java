package day4.com.amazon.pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import day4.com.amazon.utils.DriverUtils;

public class BasePage {
	public WebDriver driver;
	public String parentHandle;
	public WebDriverWait wait;
	public BasePage() {
		driver = DriverUtils.getDriver();
		parentHandle = driver.getWindowHandle();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
}
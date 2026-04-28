package day4.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import day4.utils.DriverUtils;

public class BasePage {
	WebDriver driver;
	
	public BasePage() {
		driver = DriverUtils.getDriver();
		PageFactory.initElements(driver,this);
	}
}

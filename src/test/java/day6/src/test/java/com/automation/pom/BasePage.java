package com.automation.pom;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utils.DriverUtils;

public class BasePage {
	WebDriver driver;
	String parentHandle;
	WebDriverWait wait;

	BasePage() {
		driver = DriverUtils.getDriver();
		parentHandle = driver.getWindowHandle();
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		PageFactory.initElements(driver, this);
	}
}

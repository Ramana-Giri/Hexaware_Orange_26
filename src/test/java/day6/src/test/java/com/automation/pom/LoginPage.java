package com.automation.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	@FindBy(id = "user-name")
	WebElement usernameField;

	@FindBy(name = "password")
	WebElement passwordField;
	
	@FindBy(xpath = "//input[@data-test=\"login-button\"]")
	WebElement loginBtn;
	
	public void verifyLoginFields(String username, String password) {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}
	
	public void verifyLoginBtn() {
		loginBtn.click();
	}
}

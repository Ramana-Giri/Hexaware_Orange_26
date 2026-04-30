package day4.com.amazon.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPasswordPage extends BasePage{
	@FindBy(id = "ap_password")
	WebElement passwordField;
	
	@FindBy(css = "input[aria-labelledby='auth-signin-button-announce']")
	WebElement signinBtn;
	
	public void verifyPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void verifySigninBtn() {
		signinBtn.click();
	}
}
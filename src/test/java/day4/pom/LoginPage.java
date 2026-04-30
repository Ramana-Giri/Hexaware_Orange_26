package day4.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	@FindBy(xpath = "//input[@name='Email']")
	WebElement email;

	@FindBy(name = "Password")
	WebElement password;

	@FindBy(xpath = "//input[@value='Log in']")
	WebElement loginBtn;

	public void verifyEmail(String str) {
		email.sendKeys(str);
	}

	public void verifyPassword(String str) {
		password.sendKeys(str);
	}

	public void loginBtnclick() {
		loginBtn.click();
	}
}

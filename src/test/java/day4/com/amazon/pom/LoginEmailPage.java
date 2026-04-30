package day4.com.amazon.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginEmailPage extends BasePage{
	@FindBy(css = "a[data-nav-ref=\"nav_ya_signin\"]")
	public WebElement signinBtn;
	
	@FindBy(id = "ap_email_login")
	WebElement emailField;
	
	@FindBy(css = "input[aria-labelledby='continue-announce']")
	WebElement continueBtn;
	
	public void verifyEmail(String email) {
		emailField.sendKeys(email);
	}
	
	public void verifyContinueBtn() {
		continueBtn.click();
	}
	
}
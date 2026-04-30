package day4.com.amazon.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{
	@FindBy(xpath ="//a[@aria-label='Amazon.in']")
	WebElement amazonLogoBtn;
	
	@FindBy(xpath = "//button[@aria-label='Expand Account and Lists']")
	WebElement expandAccBtn;
	
	@FindBy(id = "nav-item-signout")
	WebElement signoutBtn;
	
	public void verifyLogout() {
		System.out.println("completed");
		driver.switchTo().defaultContent();
		amazonLogoBtn.click();
		expandAccBtn.click();		
		signoutBtn.click();
		driver.quit();
	}
}

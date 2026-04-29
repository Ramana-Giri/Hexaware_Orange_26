package day4.com.amazon.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	@FindBy(xpath = "//input[@name='submit.add-to-cart'][1]")
	WebElement cartBtn;

	@FindBy(name = "proceedToRetailCheckout")
	WebElement proceedToBuyBtn;

	public void verifyAddToCart() {
		cartBtn.click();
		proceedToBuyBtn.click();
	}
}
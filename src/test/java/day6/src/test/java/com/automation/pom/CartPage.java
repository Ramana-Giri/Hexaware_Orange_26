package com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	
	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	public boolean isCartItemPresent(String product) {
		if (driver.findElement(By.partialLinkText(product)) != null)
			return true;
		return false;
	}
	
	public void selectCheckoutBtn() {
		checkoutBtn.click();
	}
}

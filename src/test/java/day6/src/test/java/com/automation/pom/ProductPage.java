package com.automation.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
	@FindBy(xpath = "//button[@id = 'add-to-cart']")
	WebElement addToCartBtn;
	
	@FindBy(className = "shopping_cart_link")
	WebElement cartIcon;
	
	public void clickAddToCartBtn() {
		addToCartBtn.isDisplayed();
		addToCartBtn.click();
	}
	
	public void clickCartIcon() {
		cartIcon.click();
	}
}

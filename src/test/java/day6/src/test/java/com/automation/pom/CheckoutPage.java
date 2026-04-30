package com.automation.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
	@FindBy(id = "first-name")
	WebElement firstNameField;

	@FindBy(id = "last-name")
	WebElement lastNameField;
	
	@FindBy(id = "postal-code")
	WebElement postalCodeField;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "finish")
	WebElement finish;
	
	public void enterYourInfo(String firstName, String lastName, String postalCode) {
		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		postalCodeField.sendKeys(postalCode);
		continueBtn.click();
	}
	
	public void clickFinish() {
		finish.click();
	}
	
	public boolean orderPlacedSuccessfully() {
		return driver.findElement(By.xpath("//h2[text() = 'Thank you for your order!']")).isDisplayed();
	}

}

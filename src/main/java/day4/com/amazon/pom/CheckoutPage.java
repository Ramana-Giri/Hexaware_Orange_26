package day4.com.amazon.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	@FindBy(xpath = "//input[@value='SelectableAddCreditCard']")
	WebElement selectCreditCardBtn;

	@FindBy(partialLinkText = "Add a new credit or debit card")
	WebElement addNewCreditCard;

	@FindBy(name = "addCreditCardNumber")
	WebElement addCreditCardNumber;

	@FindBy(name = "ppw-accountHolderName")
	WebElement addAccountHolderName;

	@FindBy(name = "ppw-expirationDate_month")
	WebElement expiryMonthElement;
	
	@FindBy(name = "ppw-expirationDate_year")
	WebElement expiryYearElement;
	
	@FindBy(xpath = "//button[text() = 'Cancel']")
	WebElement cancelBtn;

	public void verifyCheckout(String creditCardNum, String accHolderName, String expiryMonth, String expiryYear) {
		selectCreditCardBtn.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.partialLinkText("Add a new credit or debit card")));

		addNewCreditCard.click();

		wait.until(
				ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[name='ApxSecureIframe']")));

		addCreditCardNumber.sendKeys(creditCardNum);
		addAccountHolderName.sendKeys(accHolderName);

		Select monthSelect = new Select(expiryMonthElement);
		monthSelect.selectByVisibleText(expiryMonth);

		Select yearSelect = new Select(expiryYearElement);
		yearSelect.selectByVisibleText(expiryYear);
	}
	
	public void clickCancel() {
		cancelBtn.click();
	}
}

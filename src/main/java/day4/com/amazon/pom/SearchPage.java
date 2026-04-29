package day4.com.amazon.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{
	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(id = "nav-search-submit-button")
	WebElement searchBtn;
	
	public void verifySearch(String productName) {
		searchBar.sendKeys(productName);
//		searchBtn.click();
	}
	
	public void verifyClickSearch() {
		searchBtn.click();
	}
}

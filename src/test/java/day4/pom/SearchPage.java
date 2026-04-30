package day4.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {

    @FindBy(id = "small-searchterms")
    WebElement searchBox;

    @FindBy(xpath = "//input[@value='Search']")
    WebElement searchBtn;

    public void searchItem(String item) {
        searchBox.sendKeys(item);
        searchBtn.click();
    }
}

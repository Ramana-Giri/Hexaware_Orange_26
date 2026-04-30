package day4.com.amazon.pom;



import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SelectPage extends BasePage{
	
	public void selectProduct(String productName) {
		
		WebElement product = driver.findElement(By.partialLinkText(productName));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
		product.click();
		
		Set<String> handles = driver.getWindowHandles();
		
		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
				System.out.println("Switched to product handle");
			}
		}
	}
}

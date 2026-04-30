package com.automation.pom;

import org.openqa.selenium.By;

public class InventoryPage extends BasePage{
	
	public void clickProduct(String productName) {
		driver.findElement(By.partialLinkText(productName)).click();
	}
	
}

package com.shaanstraining.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage{
	public HomePage() {
		super();
	}
	
	public void search(String searchTerm){
		WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(searchTerm);
        element.submit();
	}
}

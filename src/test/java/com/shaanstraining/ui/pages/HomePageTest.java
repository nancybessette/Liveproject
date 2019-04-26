package com.shaanstraining.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest{
	static final Logger log = LogManager.getLogger(HomePageTest.class);
	HomePage homePage = new HomePage();
	
	public HomePageTest() {
		super();
	}
	
	@Test
	public void search(){
		log.debug("before calling the search..");
		homePage.search(driver, "Selenium WebDriver");
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("Selenium WebDriver");
            }
        });
	}
	
	@AfterMethod
    public void cleanUpAfterTestMethod(ITestResult result) {
        ((JavascriptExecutor)driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
        driver.quit();
    }
}

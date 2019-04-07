package com.shaanstraining.ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.shaanstraining.ui.pages.HomePage;

public class HomePageTest{
	static final Logger log = LogManager.getLogger(HomePageTest.class);
	HomePage homePage = new HomePage();
	
	@Test
	public void search(){
		log.debug("before calling the search..");
		homePage.search("Selenium WebDriver");
		
		log.debug("after calling the search..");
	}
}

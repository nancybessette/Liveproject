package com.shaanstraining.ui.pages;

import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.shaanstraining.ui.util.DriverUtils;

public abstract class BasePage {
	protected WebDriver driver;
	private Properties props = getProperties();
	
	public BasePage() {
		try {
			String baseUrl = props.getProperty("baseUrl");
			String browser = props.getProperty("defaultBrowser");
			driver = DriverUtils.getDriver(driver, browser, baseUrl);
			
			long implicitWaitTimeout = Long.parseLong(props.getProperty("implicitWaitTimeout"));
			driver.manage().timeouts().implicitlyWait(implicitWaitTimeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public Properties getProperties() {
		InetAddress host = null;
		props = new Properties();
		try {
            host = InetAddress.getLocalHost();            
            if(host.getHostName().startsWith("qa")) { 
    				props.load(new FileInputStream("src/main/resources/env-qa.properties"));
	    		} else{
	    			props.load(new FileInputStream("src/main/resources/env-regression.properties"));
	    		}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return props;
		
	}

}

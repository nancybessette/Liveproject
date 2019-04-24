package com.shaanstraining.ui.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverUtils {
	
	private static Properties props;
	private static final String DRIVER_PROP_FILE = "src/main/resources/driver.properties";
	
	public static RemoteWebDriver getDriver(RemoteWebDriver driver, String browser, String baseUrl) throws IOException,
	FileNotFoundException, Exception {
		
		props = new Properties();
		props.load(new FileInputStream(DRIVER_PROP_FILE));
		
		if (isWindows()) {
			if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.setCapability(CapabilityType.BROWSER_VERSION, 48);
				
				System.setProperty("webdriver.gecko.driver", props.getProperty(Constants.FIREFOX_DRIVER_WIN));
				driver = new FirefoxDriver();
			}
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", props.getProperty(Constants.CHROME_DRIVER_WIN));
				driver = new ChromeDriver();
			}
			if (browser.equalsIgnoreCase("iexplore")) {
				InternetExplorerOptions options = new InternetExplorerOptions();
				options.introduceFlakinessByIgnoringSecurityDomains();
				options.ignoreZoomSettings();
				
				System.setProperty("webdriver.ie.driver", props.getProperty(Constants.IE_DRIVER_WIN));	
				driver = new InternetExplorerDriver(options);
			}	
			
		}else if (isMac()){
			if (browser.equalsIgnoreCase("firefox")) {
				FirefoxOptions options = new FirefoxOptions();
				options.setCapability(CapabilityType.BROWSER_VERSION, 48);
				
				System.setProperty("webdriver.gecko.driver", props.getProperty(Constants.FIREFOX_DRIVER_MAC));	
				driver = new FirefoxDriver();
				
			}
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", props.getProperty(Constants.CHROME_DRIVER_MAC));
				driver = new ChromeDriver();
			}	
			
		}		
		driver.get(baseUrl);
		//driver.manage().window().maximize();
		
		return driver;
	}
	
	public static RemoteWebDriver getDriver(RemoteWebDriver driver, String hub, String browser, String baseUrl) throws IOException,
	FileNotFoundException, Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			driver = new RemoteWebDriver(new URL(hub),options);
		}	
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			//options.setCapability("version", "");
			driver = new RemoteWebDriver(new URL(hub),options);
		}
		driver.get(baseUrl);
		
		return driver;
	}
		
	private static boolean isWindows() {
		String os = System.getProperty("os.name");
		return os.startsWith("Windows");
	}
	
	private static boolean isMac() {
		String os = System.getProperty("os.name");
		return os.startsWith("Mac");
	}

}

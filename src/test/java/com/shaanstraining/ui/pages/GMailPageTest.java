package com.shaanstraining.ui.pages;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

public class GMailPageTest {
	static final Logger log = LogManager.getLogger(GMailPageTest.class);
	
	private static final String YAML_DATA =
						"username: testuser \n" + 
						"password: testpwd\n";
	
	@DataProvider(name = "dp")
	public Object[][] parseYaml() {
	    Yaml yaml = new Yaml();	    
	    Map<String, String> map = new HashMap<String, String>();
	    map = (Map<String, String>) yaml.load(YAML_DATA);
	    
	    return new Object[][] {map.values().toArray()};	   
	}
	
	@Test(dataProvider="dp")
	public void login(String user, String pwd) {		
		log.info("gmailPageTest: username: " + user + "\t password: " + pwd);
	}
	
}

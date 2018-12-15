package com.shaanstraining.api;

import static io.restassured.RestAssured.given;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class TranslateAPITest {
	static final String JSON_FILE = "src/test/resources/translate-api.json";
	static String TOKEN = "ya29.c.ElpsBrrj0GGyRPdYRoHQEbgUet5UZp47RTJMiEakVaOW1wIDX5Dmjo4pcAPLkUk01Eka5eJL4ScwQpIF50T9PiuN-_DRxqCyAp5F1MqEY_Cmvx9w_AK9n3QOmS4";

	@Test
	public void traslateTest() throws Exception {
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(JSON_FILE));

        JSONObject jsonObject = (JSONObject) obj;
                
		Response response = 
		given().
			header("Authorization", "Bearer " + TOKEN).
			header("contentType", "application/json").and().
			body(jsonObject.toJSONString()).
        when().
        	post("https://translation.googleapis.com/language/translate/v2");
		
		if(response.statusCode() != 200) {
			throw new Exception("Unexpected Response code: " + response.getStatusCode());
		}
		
		ResponseBody responseBody = response.body();
		responseBody.print();
       
	}
}

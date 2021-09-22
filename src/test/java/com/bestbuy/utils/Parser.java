package com.bestbuy.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Parser {
	
	public JsonPath parseResponse(Response response) {
	    String responseString = response.asString();
	    JsonPath responseJSON = new JsonPath(responseString);
	    return responseJSON;
	}
	
}

package com.bestbuy.context;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Context {
	
	
	static RequestSpecification apiRequest;
	
	static Response response;
	
	static String requestResource;
	
	static String storeId;	
	

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		Context.response = response;
	}

	public RequestSpecification getApiRequest() {
		return apiRequest;
	}

	public void setApiRequest(RequestSpecification apiRequest) {
		Context.apiRequest = apiRequest;
	}
	
	public String getApiResource() {
		return requestResource;
	}

	public void setApiResource(String requestResource) {
		Context.requestResource = requestResource;
		
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		Context.storeId = storeId;
	}
	


}

package com.bestbuy.missions;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.bestbuy.context.Context;
import com.bestbuy.utils.Parser;
import com.bestbuy.utils.PayLoads;

import io.magentys.cinnamon.conf.Env;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StoresMission {

	private Env env;
	private Context context;
	private PayLoads payLoads;
	private Parser parser;
	public String headerKey = "Content-Type";
	public String headerValue = "application/json";

	@Inject
	public StoresMission(final Env env, final Context context, final PayLoads payLoads, final Parser parser) {
		this.env = env;
		this.context = context;
		this.payLoads = payLoads;
		this.parser = parser;
	}

	public void formRequestForGettingStores(Logger log) {
		RequestSpecification apiRequest = given();
	    apiRequest.header(headerKey, headerValue);
	    log.info("\tHeaders : "+headerKey+" = "+headerValue);
	    context.setApiRequest(apiRequest);
	}

	public void getRequestForStores() {
		RequestSpecification apiRequest = context.getApiRequest();
		String apiResource = env.config.getString("getStoresResource");
		Response response = apiRequest.get(apiResource);
		context.setResponse(response);
	}

	public void formRequestForGettingSpecificStore(Logger log) {
		RequestSpecification apiRequest = given();
	    apiRequest.header(headerKey, headerValue);
	    log.info("\tHeaders : "+headerKey+" = "+headerValue);
	    context.setApiRequest(apiRequest);
	}

	public void getRequestForSpecificStore(String storeId) {
		RequestSpecification apiRequest = context.getApiRequest();
	    String requestResource = env.config.getString("getSpecificStoreResource");
	    String apiResource = requestResource.replace("store_id",storeId);
	    Response response = apiRequest.get(apiResource);
	    context.setResponse(response);	
	}

	public void postStore() {
		RequestSpecification apiRequest = context.getApiRequest();
		String apiResource = env.config.getString("postStoreResource");
		Response response = apiRequest.post(apiResource);
		context.setResponse(response);
	}

	public void formRequestForPostStore(String name, String type, String address, String city, String state, String zip,
			Integer lat, Integer lng, String hours, Logger log) {
		RequestSpecification apiRequest = given();
    	apiRequest.header(headerKey, headerValue);
	    log.info("\tHeaders : "+headerKey+" = "+headerValue);
	    apiRequest.body(payLoads.postStoreBodyData(name, type, address, city, state, zip, lat, lng, hours));
	    String requestBody = payLoads.postStoreBodyData(name, type, address, city, state, zip, lat, lng, hours).replace("\n", "").replace("\r", "");
	    log.info("\tBody : "+requestBody);
	    context.setApiRequest(apiRequest);
	}

	public void putStore(String storeId) {
		RequestSpecification apiRequest = context.getApiRequest();
		String requestResource = env.config.getString("putStoreResource");
		String apiResource = requestResource.replace("store_id",storeId);
		Response response = apiRequest.put(apiResource);
		context.setResponse(response);
	}

	public int getValidStoreID() {
		RequestSpecification apiRequest = given();
		apiRequest.header(headerKey, headerValue);
		String name = "Pune 9-11";
		String type = "Medicine";
		String address = "Mahatma Socitey";
		String city = "Pune";
		String state = "Maharashtra";
		String zip = "411038";
		Integer lat = 0;
		Integer lng = 0;
		String hours = "9 AM to 6 PM";
		apiRequest.body(payLoads.postStoreBodyData(name, type, address, city, state, zip, lat, lng, hours));
		String apiResource = env.config.getString("postStoreResource");
		Response response = apiRequest.post(apiResource);
	    JsonPath storeResponseJSON = parser.parseResponse(response);
	    int responseStoreId = storeResponseJSON.get("id");
		return responseStoreId;
	}

	public void formRequestForPutStore(String name, String type, String address, String city, String state, String zip,
			Integer lat, Integer lng, String hours, Logger log) {
		RequestSpecification apiRequest = given();
    	apiRequest.header(headerKey, headerValue);
	    log.info("\tHeaders : "+headerKey+" = "+headerValue);
	    apiRequest.body(payLoads.putStoreBodyData(name, type, address, city, state, zip, lat, lng, hours));
	    String requestBody = payLoads.putStoreBodyData(name, type, address, city, state, zip, lat, lng, hours).replace("\n", "").replace("\r", "");
	    log.info("\tBody : "+requestBody);
	    context.setApiRequest(apiRequest);
	}

	public void formRequestForDeletingSpecificStore(Logger log) {
		RequestSpecification apiRequest = given();
	    apiRequest.header(headerKey, headerValue);
	    log.info("\tHeaders : "+headerKey+" = "+headerValue);
	    context.setApiRequest(apiRequest);
	}

	public void deleteRequestForSpecificStore(String storeId) {
		RequestSpecification apiRequest = context.getApiRequest();
		String requestResource = env.config.getString("deleteStoreResource");
		String apiResource = requestResource.replace("store_id",storeId);
		Response response = apiRequest.delete(apiResource);
		context.setResponse(response);
	}

}
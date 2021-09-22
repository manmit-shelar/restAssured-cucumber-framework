package com.bestbuy.utils;

public class PayLoads {

	public String mandatoryCheckBodyData() {
		String bodyData = "{\n" + 
				"}";
		return bodyData;
	}

	public String postStoreBodyData(String requestName, String requestType, String requestAddress, String requestCity, String requestState, String requestZip,
			Integer requestLat, Integer requestLng, String requestHours) {
		String bodyStoreData = "{\n" + 
				"\"name\": \"" + requestName+"\",\n" + 
				"\"type\": \"" + requestType+"\",\n" + 
				"\"address\": \"" + requestAddress+"\",\n" + 
				"\"address2\": \"\","+
				"\"city\": \"" + requestCity+"\",\n" + 
				"\"state\": \"" + requestState+"\",\n" + 
				"\"zip\": \"" + requestZip+"\",\n" + 
				"\"lat\" : " + requestLat + ",\n"+
				"\"lng\" : " + requestLng + ",\n"+
				"\"hours\": \"" + requestHours+"\",\n" + 
				" \"services\": {}" +
				"}";
		return bodyStoreData;
	}

	public String putStoreBodyData(String requestName, String requestType, String requestAddress, String requestCity, String requestState, String requestZip,
			Integer requestLat, Integer requestLng, String requestHours) {
		String bodyStoreData = "{\n" + 
				"\"name\": \"" + requestName+"\",\n" + 
				"\"type\": \"" + requestType+"\",\n" + 
				"\"address\": \"" + requestAddress+"\",\n" + 
				"\"address2\": \"\","+
				"\"city\": \"" + requestCity+"\",\n" + 
				"\"state\": \"" + requestState+"\",\n" + 
				"\"zip\": \"" + requestZip+"\",\n" + 
				"\"lat\" : " + requestLat + ",\n"+
				"\"lng\" : " + requestLng + ",\n"+
				"\"hours\": \"" + requestHours+"\",\n" + 
				" \"services\": {}" +
				"}";
		return bodyStoreData;
	}
	
}

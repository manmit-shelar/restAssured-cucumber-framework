package com.bestbuy.stepdefs;

import static io.restassured.RestAssured.baseURI;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

import com.bestbuy.context.Context;
import com.bestbuy.missions.CommonMission;
import com.bestbuy.missions.StoresMission;
import com.bestbuy.utils.Parser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.magentys.cinnamon.conf.Env;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StoresStepDefs {

	private final StoresMission storesMission;
	private final CommonMission commonMission;
	private final Env env;
	private final Context context;
	private final Parser parser;
	private static Logger log = LogManager.getLogger(StoresStepDefs.class.getName());

	@Inject
	public StoresStepDefs(final StoresMission storesMission, final Env env, final Context context, final Parser parser,
			final CommonMission commonMission) {
		this.storesMission = storesMission;
		this.env = env;
		this.context = context;
		this.parser = parser;
		this.commonMission = commonMission;
	}

	@Given("Request for getting Stores is formed")
	public void request_for_getting_Stores_is_formed() {
		log.info("Scenario : Multiple records are returned in response of Get Stores API");
		log.info("Given : Request for getting Stores is formed");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("getStoresResource");
		log.info("\tRequest URI : " + baseURI + requestResource);
		storesMission.formRequestForGettingStores(log);
	}

	@When("Get call is made for Stores API")
	public void get_call_is_made_for_Stores_API() {
		log.info("When : Get call is made for Stores API");
		storesMission.getRequestForStores();
	}

	@Then("Multiple records are received in response of Stores API")
	public void multiple_records_are_received_in_response_of_Stores_API() {
		log.info("Then : Multiple records are received in response of Stores API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		Assert.assertEquals("Status is not 200.", 200, response.getStatusCode());
	}

	@Given("Request for getting specific Store is formed with invalid parameter")
	public void request_for_getting_specific_Store_is_formed_with_invalid_parameter() {
		log.info("Scenario : Invalid parameter is passed for Get Specific Store API");
		log.info("Given : Request for getting specific Store is formed with invalid parameter");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("getSpecificStoreResource");
		String storeId = "abc";
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForGettingSpecificStore(log);
	}

	@When("Get call is made for fetching specific Store API")
	public void get_call_is_made_for_fetching_specific_Store_API() {
		log.info("When : Get call is made for fetching specific Store API");
		String store_id = context.getStoreId();
		storesMission.getRequestForSpecificStore(store_id);
	}

	@Then("Error response for invalid parameter should be received for Store API")
	public void error_response_for_invalid_parameter_should_be_received_for_Store_API() {
		log.info("Then : Error response for invalid parameter should be received for Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		int responseCode = storeResponseJSON.get("code");
		String responseClassName = storeResponseJSON.get("className");
		Assert.assertEquals("Status is not 404.", 404, response.getStatusCode());
		Assert.assertEquals("Name is not NotFound.", "NotFound", responseName);
		Assert.assertEquals("Code is not 404.", 404, responseCode);
		Assert.assertEquals("className is not not-found.", "not-found", responseClassName);
	}

	@Given("Request for getting specific Store is formed with non existing store id")
	public void request_for_getting_specific_Store_is_formed_with_non_existing_store_id() {
		log.info("Scenario : Non existing store id is passed to Get Specific Store API");
		log.info("Given : Request for getting specific Store is formed with non existing store id");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("getSpecificStoreResource");
		String storeId = "100000";
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForGettingSpecificStore(log);
	}

	@Then("Error response for non existing store id should be received for Store API")
	public void error_response_for_non_existing_store_id_should_be_received_for_Store_API() {
		log.info("Then : Error response for non existing store id should be received for Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		int responseCode = storeResponseJSON.get("code");
		String responseClassName = storeResponseJSON.get("className");
		Assert.assertEquals("Status is not 404.", 404, response.getStatusCode());
		Assert.assertEquals("Name is not NotFound.", "NotFound", responseName);
		Assert.assertEquals("Code is not 404.", 404, responseCode);
		Assert.assertEquals("className is not not-found.", "not-found", responseClassName);
	}

	@Given("Request for getting specific Store is formed")
	public void request_for_getting_specific_Store_is_formed() {
		log.info("Scenario : Get details of the Specific Store");
		log.info("Given : Request for getting specific Store is formed");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("getSpecificStoreResource");
		String storeId = "4";
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForGettingSpecificStore(log);
	}

	@Then("Details of specific store should be received in response of Get Specific Store API")
	public void details_of_specific_store_should_be_received_in_response_of_Get_Specific_Store_API() {
		log.info("Then : Details of specific store should be received in response of Get Specific Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		String responseZipCode = storeResponseJSON.get("zip");
		String responseCity = storeResponseJSON.get("city");
		Assert.assertEquals("Status is not 200.", 200, response.getStatusCode());
		Assert.assertEquals("Name is not matching.", "Minnetonka", responseName);
		Assert.assertEquals("Zipcode is not matching.", "55305", responseZipCode);
		Assert.assertEquals("City name is not matching.", "Hopkins", responseCity);
	}

	@Given("Request for Post Store is formed without mandatory parameters")
	public void request_for_Post_Store_is_formed_without_mandatory_parameters() {
		log.info("Scenario : Mandatory parameters are not passed for Create Store API");
		log.info("Given : Request for Post Store is formed without mandatory parameters");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("postStoreResource");
		log.info("\tRequest URI : " + baseURI + requestResource);
		commonMission.formRequestForMandatoryCheck(log);
	}

	@When("Post call is made for Store API")
	public void post_call_is_made_for_Store_API() {
		log.info("When : Post call is made for Store API");
		storesMission.postStore();
	}

	@Then("Error response for missing mandatory parameters should be received for Post Store API")
	public void error_response_for_missing_mandatory_parameters_should_be_received_for_Post_Store_API() {
		log.info("Then : Error response for missing mandatory parameters should be received for Post Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		String responseMessage = storeResponseJSON.get("message");
		int responseCode = storeResponseJSON.get("code");
		List<String> responseError = storeResponseJSON.get("errors");
		Assert.assertEquals("Status is not 400.", 400, response.getStatusCode());
		Assert.assertEquals("Name is not matching.", "BadRequest", responseName);
		Assert.assertEquals("Message is not matching.", "Invalid Parameters", responseMessage);
		Assert.assertEquals("Code is not matching.", 400, responseCode);
		Assert.assertTrue("Mandatory error for name is not displayed", responseError.contains("should have required property 'name'"));
		Assert.assertTrue("Mandatory error for address is not displayed", responseError.contains("should have required property 'address'"));
		Assert.assertTrue("Mandatory error for city is not displayed", responseError.contains("should have required property 'city'"));
		Assert.assertTrue("Mandatory error for state is not displayed", responseError.contains("should have required property 'state'"));
		Assert.assertTrue("Mandatory error for zip is not displayed", responseError.contains("should have required property 'zip'"));

		Assert.assertTrue("Mandatory error for name is not displayed", responseError.get(0).contains("should have required property 'name'"));
		Assert.assertTrue("Mandatory error for address is not displayed", responseError.get(1).contains("should have required property 'address'"));
		Assert.assertTrue("Mandatory error for city is not displayed", responseError.get(2).contains("should have required property 'city'"));
		Assert.assertTrue("Mandatory error for state is not displayed", responseError.get(3).contains("should have required property 'state'"));
		Assert.assertTrue("Mandatory error for zip is not displayed", responseError.get(4).contains("should have required property 'zip'"));
	}

	@Given("Request for Post Store is formed")
	public void request_for_Post_Store_is_formed() {
		log.info("Scenario : New store is created successfully");
		log.info("Given : Request for Post Store is formed");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("postStoreResource");
		log.info("\tRequest URI : " + baseURI + requestResource);
		String name = "Pune 9-11";
		String type = "Medicine";
		String address = "Mahatma Socitey";
		String city = "Pune";
		String state = "Maharashtra";
		String zip = "411038";
		Integer lat = 0;
		Integer lng = 0;
		String hours = "9 AM to 6 PM";
		storesMission.formRequestForPostStore(name, type, address, city, state, zip, lat, lng, hours, log);
	}

	@Then("Success response for new store created should be received for Post Store API")
	public void success_response_for_new_store_created_should_be_received_for_Post_Store_API() {
		log.info("Then : Success response for new store created should be received for Post Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		Assert.assertEquals("Status is not 201.", 201, response.getStatusCode());
	}

	@Given("Request for Put Store is formed without mandatory parameters")
	public void request_for_Put_Store_is_formed_without_mandatory_parameters() {
		log.info("Scenario : Mandatory parameters are not passed for Put Store API");
		log.info("Given : Request for Put Store is formed without mandatory parameters");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("putStoreResource");
		int newStoreId = storesMission.getValidStoreID();
		String storeId = String.valueOf(newStoreId);
		context.setStoreId(storeId);
		String requestResource1 = requestResource.replace("store_id", storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		commonMission.formRequestForMandatoryCheck(log);
	}

	@When("Put call is made for Store API")
	public void put_call_is_made_for_Store_API() {
		log.info("When : Put call is made for Store API");
		String storeID = context.getStoreId();
		storesMission.putStore(storeID);
	}

	@Then("Error response for missing mandatory parameters should be received for Put Store API")
	public void error_response_for_missing_mandatory_parameters_should_be_received_for_Put_Store_API() {
		log.info("Then : Error response for missing mandatory parameters should be received for Put Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		String responseMessage = storeResponseJSON.get("message");
		int responseCode = storeResponseJSON.get("code");
		List<String> responseError = storeResponseJSON.get("errors");
		Assert.assertEquals("Status is not 400.", 400, response.getStatusCode());
		Assert.assertEquals("Name is not matching.", "BadRequest", responseName);
		Assert.assertEquals("Message is not matching.", "Invalid Parameters", responseMessage);
		Assert.assertEquals("Code is not matching.", 400, responseCode);
		Assert.assertTrue("Mandatory error for name is not displayed", responseError.contains("should have required property 'name'"));
		Assert.assertTrue("Mandatory error for address is not displayed", responseError.contains("should have required property 'address'"));
		Assert.assertTrue("Mandatory error for city is not displayed", responseError.contains("should have required property 'city'"));
		Assert.assertTrue("Mandatory error for state is not displayed", responseError.contains("should have required property 'state'"));
		Assert.assertTrue("Mandatory error for zip is not displayed", responseError.contains("should have required property 'zip'"));
	}

	@Given("Request for Put Store is formed with non existing store id")
	public void request_for_Put_Store_is_formed_with_non_existing_store_id() {
		log.info("Scenario : Non existing store id is passed for Put Store API");
		log.info("Given : Request for Put Store is formed with non existing store id");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("putStoreResource");
		String storeId = "1000000";
		context.setStoreId(storeId);
		String requestResource1 = requestResource.replace("store_id", storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		String name = "Pune 9-11";
		String type = "Medicine";
		String address = "Mahatma Socitey";
		String city = "Pune";
		String state = "Maharashtra";
		String zip = "411038";
		Integer lat = 0;
		Integer lng = 0;
		String hours = "9 AM to 6 PM";
		storesMission.formRequestForPutStore(name, type, address, city, state, zip, lat, lng, hours, log);
	}

	@Then("Error response for non existing store id should be received for Put Store API")
	public void error_response_for_non_existing_store_id_should_be_received_for_Put_Store_API() {
		log.info("Then : Error response for non existing store id should be received for Put Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		int responseCode = storeResponseJSON.get("code");
		String responseClassName = storeResponseJSON.get("className");
		Assert.assertEquals("Status is not 404.", 404, response.getStatusCode());
		Assert.assertEquals("Name is not NotFound.", "NotFound", responseName);
		Assert.assertEquals("Code is not 404.", 404, responseCode);
		Assert.assertEquals("className is not not-found.", "not-found", responseClassName);
	}

	@Given("Request for Put Store is formed")
	public void request_for_Put_Store_is_formed() {
		log.info("Scenario : Existing store is updated successfully");
		log.info("Given : Request for Put Store is formed");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("putStoreResource");
		int newStoreId = storesMission.getValidStoreID();
		String storeId = String.valueOf(newStoreId);
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		String name = "Pune 24-7";
		String type = "Grocery";
		String address = "Kothrud";
		String city = "Pune City";
		String state = "Maharashtra";
		String zip = "411058";
		Integer lat = 0;
		Integer lng = 0;
		String hours = "7 AM to 7 PM";
		storesMission.formRequestForPutStore(name, type, address, city, state, zip, lat, lng, hours, log);
	}

	@Then("Success response for store updated should be received for Put Store API")
	public void success_response_for_store_updated_should_be_received_for_Put_Store_API() {
		log.info("Then : Success response for store updated should be received for Put Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		Assert.assertEquals("Status is not 200.", 200, response.getStatusCode());
	}
	
	@Given("Request for deleting specific Store is formed with invalid parameter")
	public void request_for_deleting_specific_Store_is_formed_with_invalid_parameter() {
		log.info("Scenario : Invalid parameter is passed for Delete Store API");
		log.info("Given : Request for deleting specific Store is formed with invalid parameter");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("deleteStoreResource");
		String storeId = "abc";
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForDeletingSpecificStore(log);
	}

	@When("Delete call is made for deleting specific Store API")
	public void delete_call_is_made_for_deleting_specific_Store_API() {
		log.info("When : Delete call is made for deleting specific Store API");
		String store_id = context.getStoreId();
		storesMission.deleteRequestForSpecificStore(store_id);
	}

	@Then("Error response for invalid parameter should be received for Delete Store API")
	public void error_response_for_invalid_parameter_should_be_received_for_Delete_Store_API() {
		log.info("Then : Error response for invalid parameter should be received for Delete Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		int responseCode = storeResponseJSON.get("code");
		String responseClassName = storeResponseJSON.get("className");
		Assert.assertEquals("Status is not 404.", 404, response.getStatusCode());
		Assert.assertEquals("Name is not NotFound.", "NotFound", responseName);
		Assert.assertEquals("Code is not 404.", 404, responseCode);
		Assert.assertEquals("className is not not-found.", "not-found", responseClassName);
	}

	@Given("Request for deleting specific Store is formed with non existing store id")
	public void request_for_deleting_specific_Store_is_formed_with_non_existing_store_id() {
		log.info("Scenario : Non existing store id is passed to Delete Specific Store API");
		log.info("Given : Request for deleting specific Store is formed with non existing store id");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("deleteStoreResource");
		String storeId = "100000";
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForDeletingSpecificStore(log);
	}

	@Then("Error response for non existing store id should be received for Delete Store API")
	public void error_response_for_non_existing_store_id_should_be_received_for_Delete_Store_API() {
		log.info("Then : Error response for non existing store id should be received for Delete Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		JsonPath storeResponseJSON = parser.parseResponse(response);
		String responseName = storeResponseJSON.get("name");
		int responseCode = storeResponseJSON.get("code");
		String responseClassName = storeResponseJSON.get("className");
		Assert.assertEquals("Status is not 404.", 404, response.getStatusCode());
		Assert.assertEquals("Name is not NotFound.", "NotFound", responseName);
		Assert.assertEquals("Code is not 404.", 404, responseCode);
		Assert.assertEquals("className is not not-found.", "not-found", responseClassName);
	}

	@Given("Request for deleting specific Store is formed")
	public void request_for_deleting_specific_Store_is_formed() {
		log.info("Scenario : Delete a specific Store");
		log.info("Given : Request for deleting specific Store is formed");
		baseURI = env.config.getString("apiURI");
		String requestResource = env.config.getString("deleteStoreResource");
		int newStoreId = storesMission.getValidStoreID();
		String storeId = String.valueOf(newStoreId);
		String requestResource1 = requestResource.replace("store_id", storeId);
		context.setStoreId(storeId);
		log.info("\tRequest URI : " + baseURI + requestResource1);
		storesMission.formRequestForDeletingSpecificStore(log);
	}

	@Then("Delete success response should be received in response of Delete Store API")
	public void delete_success_response_should_be_received_in_response_of_Delete_Store_API() {
		log.info("Then : Delete success response should be received in response of Delete Store API");
		Response response = context.getResponse();
		log.info("\tResponse : " + response.asString() + "\n");
		Assert.assertEquals("Status is not 200.", 200, response.getStatusCode());
	}

}
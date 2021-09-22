package com.bestbuy.missions;

import static io.restassured.RestAssured.given;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.bestbuy.context.Context;
import com.bestbuy.utils.PayLoads;

import io.restassured.specification.RequestSpecification;

public class CommonMission {

	private Context context;
	private PayLoads payLoads;
	public String headerKey = "Content-Type";
	public String headerValue = "application/json";

	@Inject
	public CommonMission(final Context context, final PayLoads payLoads) {
		this.context = context;
		this.payLoads = payLoads;
	}

	public void formRequestForMandatoryCheck(Logger log) {
	      RequestSpecification apiRequest = given();
	      apiRequest.header(headerKey, headerValue);
	      log.info("\tHeaders : "+headerKey+" = "+headerValue);
	      apiRequest.body(payLoads.mandatoryCheckBodyData());
	      String requestBody = payLoads.mandatoryCheckBodyData().replace("\n", "").replace("\r", "");
	      log.info("\tBody : "+requestBody);
	      context.setApiRequest(apiRequest);
	  }

}
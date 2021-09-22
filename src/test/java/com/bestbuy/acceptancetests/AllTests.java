package com.bestbuy.acceptancetests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
			glue = { "com.bestbuy" }, 
			features = "src/test/resources/features", 
			plugin = { "json:target/cucumber-reports/cucumber.json" }, 
			tags = { "@stores" })

public class AllTests {
}

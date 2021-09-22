import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = {"/Users/cafis_mac_1/restassured-cucumber-framework/src/test/resources/features/Stores.feature"},
        plugin = {"json:/Users/cafis_mac_1/restassured-cucumber-framework/target/cucumber-reports/1.json"},
        monochrome = false,
        tags = {"@stores"},
        glue = {"com.bestbuy"})
public class StoresIT {
}

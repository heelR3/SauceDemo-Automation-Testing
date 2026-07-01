package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/resources/features",

		glue = { "stepdefinitions", "hooks" },

		plugin = {

				"pretty",

				"html:target/CucumberReport.html",

				"json:target/Cucumber.json"

		},

		monochrome = true

)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)

	public Object[][] scenarios() {

		return super.scenarios();

	}

}
package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

		features = "src/test/resources/features/PerformanceAccessibility.feature",

		glue = { "stepdefinitions", "hooks" },

		plugin = {

				"pretty",

				"html:target/cucumber-reports/cucumber.html",

				"json:target/cucumber-reports/cucumber.json",

				"junit:target/cucumber-reports/cucumber.xml",
				
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
				
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

		},

		monochrome = true,

		publish = false

)

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {

		return super.scenarios();

	}

}
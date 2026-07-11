package runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utils.BrowserContext;

@CucumberOptions(

        features = "src/test/resources/features/login.feature",

        glue = { "stepdefinitions", "hooks" },

        plugin = {
                "pretty",
                "html:target/cucumber-reports/login.html",
                "json:target/cucumber-reports/login.json",
                "junit:target/cucumber-reports/login.xml",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },

        monochrome = true,
        publish = false

)
public class LoginRunner extends AbstractTestNGCucumberTests {

	@Parameters("browser")
	@BeforeClass
	public void setupBrowser(String browser) {

	    BrowserContext.setBrowser(browser);
	}
	
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
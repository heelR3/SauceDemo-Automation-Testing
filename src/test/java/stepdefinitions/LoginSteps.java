package stepdefinitions;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
 
import java.util.Map;
 
import io.cucumber.java.en.*;
 
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExcelReader;
 
public class LoginSteps {
 
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
 
    @Given("User is on the SauceDemo login page")
    public void user_is_on_login_page() {
 
        DriverFactory.getDriver().get(
                ConfigReader.getProperty("url"));
 
    }
 
    @When("User executes login test case {string}")
    public void user_executes_login_test_case(String tcId) {
 
        Map<String,String> data =
                ExcelReader.getTestData(
                        ConfigReader.getProperty("loginSheet"),
                        tcId);
 
        loginPage.enterUsername(data.get("Username"));
 
        loginPage.enterPassword(data.get("Password"));
 
        loginPage.clickLogin();
 
    }
 
    @Then("User should be redirected to the Inventory page")
    public void inventory_page() {
 
        assertTrue(
                loginPage.getCurrentUrl().contains("inventory"));
 
    }
 
    @Then("User should see an error message for invalid credentials")
    public void invalid_credentials() {
 
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
 
    }
 
    @Then("User should see a required field error message")
    public void required_field() {
 
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required");
 
    }
 
    @Then("User should see a locked out user error message")
    public void locked_user() {
 
        assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.");
 
    }
 
}
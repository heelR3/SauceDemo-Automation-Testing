package stepdefinitions;
 
import static org.testng.Assert.assertTrue;
 
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LogoutPage;
import utils.DriverFactory;
import utils.ExcelWriter;
import utils.ScreenshotUtil;
 
public class LogoutSteps {
 
    private LogoutPage logoutPage;
 
    public LogoutSteps() {
 
        logoutPage = new LogoutPage(DriverFactory.getDriver());
 
    }
 
    // ==========================================
    // Logout
    // ==========================================
 
    @When("User clicks Menu")
    public void user_clicks_menu() {
 
        logoutPage.clickMenu();
 
    }
 
    @When("User clicks Logout")
    public void user_clicks_logout() {
 
        logoutPage.clickLogout();
 
    }
 
    @Then("User should be redirected to Login page")
    public void user_should_be_redirected_to_login_page() {
 
        assertTrue(logoutPage.isLoginPageDisplayed());
 
    }
 
    // ==========================================
    // Excel
    // ==========================================
 
    @Then("Logout result should be written into Logout Excel sheet with status {string}")
    public void logout_result_should_be_written_into_logout_excel_sheet(String result) {
 
        ExcelWriter.writeLogoutResult(
                "SD_TC_025",
                result
        );
 
    }
 
    // ==========================================
    // Screenshot
    // ==========================================
 
    @Then("User captures logout screenshot {string}")
    public void user_captures_logout_screenshot(String screenshotName) {
 
        ScreenshotUtil.captureScreenshot(
                "Logout",
                screenshotName
        );
 
    }
 
}
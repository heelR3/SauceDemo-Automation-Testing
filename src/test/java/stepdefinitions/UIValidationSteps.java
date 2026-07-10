package stepdefinitions;
 
import static org.testng.Assert.assertTrue;
 
import org.openqa.selenium.Point;
 
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import pages.UIValidationPage;
import utils.DriverFactory;
import utils.ExcelWriter;
 
public class UIValidationSteps {
 
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    UIValidationPage uiPage = new UIValidationPage(DriverFactory.getDriver());
 
    // ===========================
    // Background
    // ===========================
 
    @Given("User is logged into SauceDemo")
    public void user_is_logged_into_saucedemo() {
 
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
 
    }
 
    // ===========================
    // SD_TC_043
    // ===========================
 
    @Then("User should see SauceDemo logo")
    public void user_should_see_saucedemo_logo() {
 
        boolean status = uiPage.isLogoDisplayed();
 
        ExcelWriter.writeUIValidationResult(
                "SD_TC_043",
                "SauceDemo Logo",
                status ? "PASS" : "FAIL");
 
        assertTrue(status);
 
    }
 
    // ===========================
    // SD_TC_044
    // ===========================
 
    @Given("User is on Login page")
    public void user_is_on_login_page() {
 
        DriverFactory.getDriver().navigate().back();
 
    }
 
    @Then("Login button should be properly aligned")
    public void login_button_should_be_properly_aligned() {
 
        Point location = uiPage.getLoginButtonLocation();
 
        boolean status = location.getX() > 0 && location.getY() > 0;
 
        ExcelWriter.writeUIValidationResult(
                "SD_TC_044",
                "Login Button Alignment",
                status ? "PASS" : "FAIL");
 
        assertTrue(status);
 
    }
 
    // ===========================
    // SD_TC_045
    // ===========================
 
    @Then("Inventory page layout should be correct")
    public void inventory_page_layout_should_be_correct() {
 
        boolean status =
                uiPage.isInventoryTitleDisplayed()
                && uiPage.areProductsDisplayed()
                && uiPage.isShoppingCartDisplayed()
                && uiPage.isMenuButtonDisplayed();
 
        ExcelWriter.writeUIValidationResult(
                "SD_TC_045",
                "Inventory Layout",
                status ? "PASS" : "FAIL");
 
        assertTrue(status);
 
    }
 
    // ===========================
    // SD_TC_046
    // ===========================
 
    @Then("Cart icon should be visible")
    public void cart_icon_should_be_visible() {
 
        boolean status = uiPage.isCartIconVisible();
 
        ExcelWriter.writeUIValidationResult(
                "SD_TC_046",
                "Cart Icon",
                status ? "PASS" : "FAIL");
 
        assertTrue(status);
 
    }
 
    // ===========================
    // SD_TC_047
    // ===========================
 
    @Then("Twitter, Facebook and LinkedIn links should be displayed")
    public void social_links_should_be_displayed() {
 
        boolean status =
                uiPage.isTwitterLinkDisplayed()
                && uiPage.isFacebookLinkDisplayed()
                && uiPage.isLinkedInLinkDisplayed();
 
        ExcelWriter.writeUIValidationResult(
                "SD_TC_047",
                "Footer Links",
                status ? "PASS" : "FAIL");
 
        assertTrue(status);
 
    }
 
}
 
package stepdefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SessionManagementPage;
import utils.DriverFactory;
import utils.ExcelWriter;

public class SessionManagementSteps {

    SessionManagementPage sessionPage =
            new SessionManagementPage(
                    DriverFactory.getDriver());

    // ==========================
    // SD_TC_048
    // ==========================

    @When("User refreshes the browser")
    public void user_refreshes_the_browser() {

        sessionPage.refreshBrowser();
    }

    @Then("User session should remain active")
    public void user_session_should_remain_active() {

        Assert.assertTrue(
                sessionPage.isInventoryPageDisplayed());

        ExcelWriter.writeSessionResult(
                "SD_TC_048",
                "Verify session after browser refresh",
                "PASS");
    }

    // ==========================
    // SD_TC_049
    // ==========================

    @When("User navigates between pages")
    public void user_navigates_between_pages() {

        sessionPage.clickCart();

        Assert.assertTrue(
                sessionPage.getCurrentUrl()
                .contains("cart"));

        sessionPage.navigateBack();
    }

    @Then("User should remain logged in")
    public void user_should_remain_logged_in() {

        Assert.assertTrue(
                sessionPage.isInventoryPageDisplayed());

        ExcelWriter.writeSessionResult(
                "SD_TC_049",
                "Verify session after navigating pages",
                "PASS");
    }

    // ==========================
    // SD_TC_050
    // ==========================

    @When("User directly enters inventory URL")
    public void user_directly_enters_inventory_url() {

        sessionPage.openInventoryUrl();
    }

    @Then("User should not access inventory page after logout")
    public void user_should_not_access_inventory_page_after_logout() {

        Assert.assertFalse(
                sessionPage.getCurrentUrl()
                .contains("inventory"));

        ExcelWriter.writeSessionResult(
                "SD_TC_050",
                "Verify direct access after logout",
                "PASS");
    }
}
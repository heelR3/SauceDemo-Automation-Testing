package stepdefinitions;

import io.cucumber.java.en.Then;
import pages.AccessibilityPage;
import utils.DriverFactory;

public class AccessibilitySteps {

    AccessibilityPage accessibilityPage =
            new AccessibilityPage(DriverFactory.getDriver());

    @Then("User checks accessibility")
    public void user_checks_accessibility() {

        accessibilityPage.checkLoginElements();
    }
}
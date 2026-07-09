package stepdefinitions;

import io.cucumber.java.en.Then;
import pages.PerformancePage;
import utils.DriverFactory;

public class PerformanceSteps {

    PerformancePage performancePage =
            new PerformancePage(DriverFactory.getDriver());

    @Then("User checks page performance")
    public void user_checks_page_performance() {

        long loadTime = performancePage.getPageLoadTime();

        System.out.println("Page Load Time: "
                + loadTime + " ms");
    }
}
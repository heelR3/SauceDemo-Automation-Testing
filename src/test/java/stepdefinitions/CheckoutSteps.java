package stepdefinitions;
 
import static org.testng.Assert.assertEquals;
 
import java.util.List;
 
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CheckoutPage;
import utils.DriverFactory;
import utils.ExcelWriter;
import utils.ScreenshotUtil;
 
public class CheckoutSteps {
 
    private CheckoutPage checkoutPage;
 
    private String testCaseId;
    private String firstName;
    private String lastName;
    private String postalCode;
 
    public CheckoutSteps() {
 
        checkoutPage =
                new CheckoutPage(
                        DriverFactory.getDriver());
 
    }
 
    // ==========================================
    // Checkout
    // ==========================================
 
    @When("User clicks Checkout")
    public void userClicksCheckout() {
 
        checkoutPage.clickCheckout();
 
    }
 
    @Then("Checkout Information page should be displayed")
    public void checkoutInformationPageShouldBeDisplayed() {
 
        assertEquals(
 
                checkoutPage.getPageTitle(),
 
                "Checkout: Your Information"
 
        );
 
    }
 
    @When("User enters checkout details for test case {string}")
    public void userEntersCheckoutDetailsForTestCase(
            String tcId,
            DataTable table) {
 
        testCaseId = tcId;
 
        List<List<String>> data =
                table.asLists();
 
        firstName = data.get(0).get(0);
        lastName = data.get(0).get(1);
        postalCode = data.get(0).get(2);
 
        checkoutPage.enterFirstName(firstName);
 
        checkoutPage.enterLastName(lastName);
 
        checkoutPage.enterPostalCode(postalCode);
 
    }
 
    @When("User clicks Continue")
    public void userClicksContinue() {
 
        checkoutPage.clickContinue();
 
    }
 
    // ==========================================
    // Overview
    // ==========================================
 
    @Then("Checkout Overview page should be displayed")
    public void checkoutOverviewPageShouldBeDisplayed() {
 
        assertEquals(
 
                checkoutPage.getPageTitle(),
 
                "Checkout: Overview"
 
        );
 
    }
 
    // ==========================================
    // Finish
    // ==========================================
 
    @When("User clicks Finish")
    public void userClicksFinish() {
 
        checkoutPage.clickFinish();
 
    }
 
    @Then("Checkout Complete page should be displayed")
    public void checkoutCompletePageShouldBeDisplayed() {
 
        assertEquals(
 
                checkoutPage.getCompleteHeader(),
 
                "Thank you for your order!"
 
        );
 
    }
 
    // ==========================================
    // Error
    // ==========================================
 
    @Then("Checkout error message should be {string}")
    public void checkoutErrorMessageShouldBe(
            String expectedMessage) {
 
        assertEquals(
 
                checkoutPage.getErrorMessage(),
 
                expectedMessage
 
        );
 
    }
 
    // ==========================================
    // Excel
    // ==========================================
 
    @Then("Checkout result should be written into Checkout Excel sheet with status {string}")
    public void checkoutResultShouldBeWrittenIntoCheckoutExcelSheet(
            String status) {
 
        String error = "";
 
        if (status.equalsIgnoreCase("FAIL")) {
 
            error =
                    checkoutPage.getErrorMessage();
 
        }
 
        ExcelWriter.writeCheckoutResult(
 
                testCaseId,
 
                firstName,
 
                lastName,
 
                postalCode,
 
                status,
 
                error
 
        );
 
    }
 
    // ==========================================
    // Screenshot
    // ==========================================
 
    @Then("User captures checkout screenshot {string}")
    public void userCapturesCheckoutScreenshot(
            String screenshotName) {
 
        ScreenshotUtil.captureScreenshot(
 
                "Checkout",
 
                screenshotName
 
        );
 
    }
 
}
 
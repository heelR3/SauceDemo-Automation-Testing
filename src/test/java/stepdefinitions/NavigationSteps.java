package stepdefinitions;
 
import static org.testng.Assert.assertTrue;
 
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NavigationPage;
import utils.DriverFactory;
import utils.ScreenshotUtil;
 
public class NavigationSteps {
 
    private NavigationPage navigationPage;
 
    public NavigationSteps() {
 
        navigationPage =
                new NavigationPage(
                        DriverFactory.getDriver());
    }
 
    // =========================================
    // SD_TC_030
    // =========================================
 
    @When("User opens product details page for {string}")
    public void user_opens_product_details_page_for(
            String productName) {
 
        navigationPage.openProductDetails();
    }
 
    @When("User clicks Add to Cart from product details page")
    public void user_clicks_add_to_cart_from_product_details_page() {
 
        navigationPage.clickAddToCart();
    }
 
    @Then("Remove button should be displayed")
    public void remove_button_should_be_displayed() {
 
        assertTrue(
 
                navigationPage.isRemoveButtonDisplayed(),
 
                "Remove button is not displayed."
        );
    }
 
    // =========================================
    // SD_TC_031
    // =========================================
 
    @When("User clicks Remove button on product details page")
    public void user_clicks_remove_button_on_product_details_page() {
 
        navigationPage.clickRemove();
    }
 
    @Then("Add to Cart button should be displayed")
    public void add_to_cart_button_should_be_displayed() {
 
        assertTrue(
 
                navigationPage.isAddToCartButtonDisplayed(),
 
                "Add To Cart button is not displayed."
        );
    }
 
    // =========================================
    // SD_TC_032
    // =========================================
 
    @When("User clicks Back to Products button")
    public void user_clicks_back_to_products_button() {
 
        navigationPage.clickBackToProducts();
    }
 
    @Then("User should be redirected to Inventory page")
    public void user_should_be_redirected_to_inventory_page() {
 
        assertTrue(
 
                navigationPage.isInventoryPageDisplayed(),
 
                "Inventory page is not displayed."
        );
    }
 
    // =========================================
    // SD_TC_033
    // =========================================
 
    @When("User clicks Cart icon")
    public void user_clicks_cart_icon() {
 
        navigationPage.clickCartIcon();
    }
 
    // =========================================
    // SD_TC_036
    // =========================================
 
    @When("User clicks Back Home button")
    public void user_clicks_back_home_button() {
 
        navigationPage.clickBackHome();
    }
 
    // =========================================
    // Excel Placeholder
    // =========================================
 
    @Then("Navigation result should be written into Navigation Excel sheet with status {string}")
    public void navigation_result_should_be_written_into_navigation_excel_sheet_with_status(
            String status) {
 
        System.out.println(
                "Navigation Result : "
                        + status);
    }
 
    // =========================================
    // Screenshot
    // =========================================
 
    @Then("User captures navigation screenshot {string}")
    public void user_captures_navigation_screenshot(
            String screenshotName) {
 
        ScreenshotUtil.captureScreenshot(
 
                "Navigation",
 
                screenshotName
        );
    }
}
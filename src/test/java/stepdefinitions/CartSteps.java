package stepdefinitions;
 
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
 
import java.util.List;
 
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Product;
import pages.CartPage;
import utils.DriverFactory;
import utils.ExcelWriter;
import utils.ScreenshotUtil;
 
public class CartSteps {
 
    private CartPage cartPage;
 
    private Product singleProduct;
 
    private Product removedProduct;
 
    private List<Product> addedProducts;
 
    public CartSteps() {
 
        cartPage = new CartPage(DriverFactory.getDriver());
 
    }
 
    // =========================================
    // Add Single Product
    // =========================================
 
    @When("User adds a single product to the cart")
    public void user_adds_a_single_product_to_the_cart() {
 
        singleProduct =
                cartPage.addSingleProduct();
 
    }
 
    // =========================================
    // Add Multiple Products
    // =========================================
 
    @When("User adds {int} more products to the cart")
    public void user_adds_more_products_to_the_cart(Integer count) {
 
        addedProducts =
                cartPage.addMultipleProducts(count);
 
    }
 
    // =========================================
    // Badge
    // =========================================
 
    @Then("Cart badge count should be {string}")
    public void cart_badge_count_should_be(String expected) {
 
        assertEquals(
 
                cartPage.getCartBadgeCount(),
 
                Integer.parseInt(expected),
 
                "Cart badge count mismatch."
 
        );
 
    }
 
    // =========================================
    // Cart
    // =========================================
 
    @When("User opens the cart")
    public void user_opens_the_cart() {
 
        cartPage.openCart();
 
    }
 
    @Then("Cart page should be displayed")
    public void cart_page_should_be_displayed() {
 
        assertTrue(
 
                cartPage.isCartPageDisplayed(),
 
                "Cart page is not displayed."
 
        );
 
    }
 
    @Then("Cart title should be {string}")
    public void cart_title_should_be(String expectedTitle) {
 
        assertEquals(
 
                cartPage.getCartTitle(),
 
                expectedTitle
 
        );
 
    }
 
    @Then("Cart should contain {int} products")
    public void cart_should_contain_products(Integer expectedCount) {
 
        assertEquals(
 
                cartPage.getCartItemCount(),
 
                expectedCount.intValue()
 
        );
 
    }
 
    // =========================================
    // Excel
    // =========================================
 
    @Then("Cart details should be written into Cart Excel sheet with action {string}")
    public void cart_details_should_be_written_into_cart_excel_sheet_with_action(String action) {
 
        ExcelWriter.writeCartAction(
 
                singleProduct,
 
                action
 
        );
 
    }
 
    @Then("Newly added products should be written into Cart Excel sheet with action {string}")
    public void newly_added_products_should_be_written_into_cart_excel_sheet_with_action(String action) {
 
        for (Product product : addedProducts) {
 
            ExcelWriter.writeCartAction(
 
                    product,
 
                    action
 
            );
 
        }
 
    }
 
    // =========================================
    // Remove Product
    // =========================================
 
    @When("User removes the first product")
    public void user_removes_the_first_product() {
 
        removedProduct =
                cartPage.removeFirstProduct();
 
    }
 
    @Then("Removed product should be written into Cart Excel sheet with action {string}")
    public void removed_product_should_be_written_into_cart_excel_sheet_with_action(String action) {
 
        ExcelWriter.writeCartAction(
 
                removedProduct,
 
                action
 
        );
 
    }
 
    // =========================================
    // Continue Shopping
    // =========================================
 
    @When("User clicks Continue Shopping")
    public void user_clicks_continue_shopping() {
 
        cartPage.clickContinueShopping();
 
    }
 
    // =========================================
    // Screenshot
    // =========================================
 
    @Then("User captures screenshot {string}")
    public void user_captures_screenshot(String screenshotName) {
 
        ScreenshotUtil.captureScreenshot(
 
                "Cart",
 
                screenshotName
 
        );
 
    }
 
}
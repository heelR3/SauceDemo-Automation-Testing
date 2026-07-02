package stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import model.Product;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExcelReader;
import utils.ExcelWriter;

public class InventorySteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private List<Product> products;

    @Given("User is logged into SauceDemo using {string}")
    public void user_logs_in_using_test_case(String testCaseId) {

        loginPage =
                new LoginPage(DriverFactory.getDriver());

        inventoryPage =
                new InventoryPage(DriverFactory.getDriver());

        DriverFactory.getDriver().get(
                ConfigReader.getProperty("url"));

        Map<String, String> loginData =
                ExcelReader.getTestData(
                        ConfigReader.getProperty(
                                "loginSheet"),
                        testCaseId);

        loginPage.enterUsername(
                loginData.get("Username"));

        loginPage.enterPassword(
                loginData.get("Password"));

        loginPage.clickLogin();

    }

    @Then("Inventory page should be displayed")
    public void inventory_page_should_be_displayed() {

        assertTrue(
                inventoryPage.isInventoryPageDisplayed(),
                "Inventory Page is not displayed.");

    }

    @Then("Inventory title should be {string}")
    public void inventory_title_should_be(
            String expectedTitle) {

        assertEquals(
                inventoryPage.getInventoryTitle(),
                expectedTitle,
                "Inventory title mismatch.");

    }

    @Then("Inventory page should contain {int} products")
    public void inventory_page_should_contain_products(
            Integer expectedCount) {

        assertEquals(
                inventoryPage.getProductCount(),
                expectedCount.intValue(),
                "Incorrect number of products displayed.");

    }

    @When("User extracts all inventory products")
    public void user_extracts_all_inventory_products() {

        ExcelWriter.clearInventorySheet();

        ExcelWriter.createInventoryHeader();

        products = inventoryPage.getProducts();

    }

    @Then("Product details should be written into Inventory Excel sheet")
    public void product_details_should_be_written_into_inventory_excel_sheet() {

        ExcelWriter.writeInventoryProducts(products);

    }

    @When("User sorts products by {string}")
    public void user_sorts_products_by(String sortOption) {

        products =
                inventoryPage.getSortedProducts(
                        sortOption);

    }

    @Then("Sorted products should be written under {string}")
    public void sorted_products_should_be_written_under(
            String sortType) {

        switch (sortType.toUpperCase()) {

            case "A-Z":

                ExcelWriter.writeSortingResult(
                        products, 4);
                break;

            case "Z-A":

                ExcelWriter.writeSortingResult(
                        products, 5);
                break;

            case "LOW-HIGH":

                ExcelWriter.writeSortingResult(
                        products, 6);
                break;

            case "HIGH-LOW":

                ExcelWriter.writeSortingResult(
                        products, 7);
                break;

            default:

                throw new IllegalArgumentException(
                        "Invalid Sort Type : "
                                + sortType);

        }

    }

}
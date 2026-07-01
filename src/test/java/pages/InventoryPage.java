package pages;
 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
 
import model.Product;
 
public class InventoryPage {
 
    private WebDriver driver;
 
    // ===========================
    // Constructor
    // ===========================
 
    public InventoryPage(WebDriver driver) {
 
        this.driver = driver;
 
    }
 
    // ===========================
    // Locators
    // ===========================
 
    private By inventoryTitle =
            By.className("title");
 
    private By productNames =
            By.className("inventory_item_name");
 
    private By productPrices =
            By.className("inventory_item_price");
 
    private By productDescriptions =
            By.className("inventory_item_desc");
 
    private By productImages =
            By.cssSelector(".inventory_item_img img");
 
    private By sortDropdown =
            By.className("product_sort_container");
 
    // ===========================
    // Validation Methods
    // ===========================
 
    public boolean isInventoryPageDisplayed() {
 
        return driver.getCurrentUrl().contains("inventory.html");
 
    }
 
    public String getInventoryTitle() {
 
        return driver.findElement(inventoryTitle).getText();
 
    }
 
    public int getProductCount() {
 
        return driver.findElements(productNames).size();
 
    }
 
    // ===========================
    // Product Extraction
    // ===========================
 
    public List<Product> getProducts() {
 
        List<Product> productList = new ArrayList<>();
 
        List<WebElement> names =
                driver.findElements(productNames);
 
        List<WebElement> prices =
                driver.findElements(productPrices);
 
        List<WebElement> descriptions =
                driver.findElements(productDescriptions);
 
        List<WebElement> images =
                driver.findElements(productImages);
 
        for (int i = 0; i < names.size(); i++) {
 
            Product product = new Product();
 
            product.setName(names.get(i).getText());
 
            product.setPrice(prices.get(i).getText());
 
            product.setDescription(
                    descriptions.get(i).getText());
 
            product.setImageUrl(
                    images.get(i).getAttribute("src"));
 
            productList.add(product);
 
        }
 
        return productList;
 
    }
 
    // ===========================
    // Sorting
    // ===========================
 
    public void sortProducts(String visibleText) {
 
        Select select = new Select(
                driver.findElement(sortDropdown));
 
        select.selectByVisibleText(visibleText);
 
    }
 
    // ===========================
    // Sorting Helpers
    // ===========================
 
    public List<Product> getSortedProducts(String sortOption) {
 
        sortProducts(sortOption);
 
        return getProducts();
 
    }
 
}
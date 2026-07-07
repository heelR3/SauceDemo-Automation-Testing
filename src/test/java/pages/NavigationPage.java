package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
public class NavigationPage {
 
    private WebDriver driver;
 
    public NavigationPage(WebDriver driver) {
 
        this.driver = driver;
    }
 
    // =========================================
    // Locators
    // =========================================
 
    private By productLink =
            By.id("item_4_title_link");
 
    private By addToCartButton =
            By.id("add-to-cart");
 
    private By removeButton =
            By.id("remove");
 
    private By backToProductsButton =
            By.id("back-to-products");
 
    private By cartIcon =
            By.className("shopping_cart_link");
 
    private By backHomeButton =
            By.id("back-to-products");
 
    // =========================================
    // Product Details
    // =========================================
 
    public void openProductDetails() {
 
        driver.findElement(productLink).click();
    }
 
    public void clickAddToCart() {
 
        driver.findElement(addToCartButton).click();
    }
 
    public void clickRemove() {
 
        driver.findElement(removeButton).click();
    }
 
    public void clickBackToProducts() {
 
        driver.findElement(backToProductsButton).click();
    }
 
    // =========================================
    // Navigation
    // =========================================
 
    public void clickCartIcon() {
 
        driver.findElement(cartIcon).click();
    }
 
    public void clickBackHome() {
 
        driver.findElement(backHomeButton).click();
    }
 
    // =========================================
    // Validation
    // =========================================
 
    public boolean isRemoveButtonDisplayed() {
 
        return driver.findElements(removeButton).size() > 0;
    }
 
    public boolean isAddToCartButtonDisplayed() {
 
        return driver.findElements(addToCartButton).size() > 0;
    }
 
    public boolean isInventoryPageDisplayed() {
 
        return driver.getCurrentUrl().contains("inventory");
    }
}
 
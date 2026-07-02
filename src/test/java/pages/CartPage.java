package pages;
 
import java.util.ArrayList;
import java.util.List;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
import model.Product;
import utils.WaitUtil;
 
public class CartPage {
 
    private WebDriver driver;
 
    public CartPage(WebDriver driver) {
 
        this.driver = driver;
 
    }
 
    // ===========================
    // Locators
    // ===========================
 
    private By addToCartButtons =
            By.xpath("//button[contains(text(),'Add to cart')]");
 
    private By removeButtons =
            By.xpath("//button[contains(text(),'Remove')]");
 
    private By cartBadge =
            By.className("shopping_cart_badge");
 
    private By cartIcon =
            By.className("shopping_cart_link");
 
    private By cartTitle =
            By.className("title");
 
    private By cartItems =
            By.className("cart_item");
 
    private By itemNames =
            By.className("inventory_item_name");
 
    private By itemPrices =
            By.className("inventory_item_price");
 
    private By continueShoppingButton =
            By.id("continue-shopping");
 
    // ===========================
    // Add Products
    // ===========================
 
    public void addSingleProduct() {
 
        WaitUtil.waitForClickability(addToCartButtons)
                .click();
 
    }
 
    public void addProduct(int index) {
 
        List<WebElement> buttons =
                driver.findElements(addToCartButtons);
 
        buttons.get(index).click();
 
    }
 
    public void addMultipleProducts(int count) {
 
        List<WebElement> buttons =
                driver.findElements(addToCartButtons);
 
        for (int i = 0; i < count; i++) {
 
            buttons.get(i).click();
 
        }
 
    }
 
    // ===========================
    // Remove Product
    // ===========================
 
    public void removeFirstProduct() {
 
        WaitUtil.waitForClickability(removeButtons)
                .click();
 
    }
 
    // ===========================
    // Cart Badge
    // ===========================
 
    public String getCartBadgeCount() {
 
        List<WebElement> badge =
                driver.findElements(cartBadge);
 
        if (badge.isEmpty()) {
 
            return "0";
 
        }
 
        return badge.get(0).getText();
 
    }
 
    // ===========================
    // Open Cart
    // ===========================
 
    public void openCart() {
 
        WaitUtil.waitForClickability(cartIcon)
                .click();
 
    }
 
    public boolean isCartPageDisplayed() {
 
        return driver.getCurrentUrl().contains("cart.html");
 
    }
 
    public String getCartTitle() {
 
        return driver.findElement(cartTitle).getText();
 
    }
 
    // ===========================
    // Cart Products
    // ===========================
 
    public int getCartItemCount() {
 
        return driver.findElements(cartItems).size();
 
    }
 
    public List<Product> getCartProducts() {
 
        List<Product> products =
                new ArrayList<>();
 
        List<WebElement> names =
                driver.findElements(itemNames);
 
        List<WebElement> prices =
                driver.findElements(itemPrices);
 
        for (int i = 0; i < names.size(); i++) {
 
            Product product =
                    new Product();
 
            product.setName(
                    names.get(i).getText());
 
            product.setPrice(
                    prices.get(i).getText());
 
            products.add(product);
 
        }
 
        return products;
 
    }
 
    // ===========================
    // Continue Shopping
    // ===========================
 
    public void clickContinueShopping() {
 
        WaitUtil.waitForClickability(
                continueShoppingButton)
                .click();
 
    }
 
}

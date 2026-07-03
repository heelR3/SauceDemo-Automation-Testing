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
    // ============================
    // Locators
    // ============================
    private By addToCartButtons =
            By.xpath("//button[text()='Add to cart']");
    private By removeButtons =
            By.xpath("//button[text()='Remove']");
    private By cartIcon =
            By.className("shopping_cart_link");
    private By cartBadge =
            By.className("shopping_cart_badge");
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
    // ============================
    // Add Products
    // ============================
    public Product addSingleProduct() {
        List<WebElement> names =
                driver.findElements(itemNames);
        List<WebElement> prices =
                driver.findElements(itemPrices);
        Product product = new Product();
        product.setName(names.get(0).getText());
        product.setPrice(prices.get(0).getText());
        WaitUtil.waitForClickability(addToCartButtons).click();
        return product;
    }
    public List<Product> addMultipleProducts(int count) {
        List<Product> addedProducts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<WebElement> buttons =
                    driver.findElements(addToCartButtons);
            List<WebElement> names =
                    driver.findElements(itemNames);
            List<WebElement> prices =
                    driver.findElements(itemPrices);
            if (buttons.isEmpty()) {
                break;
            }
            Product product = new Product();
            product.setName(names.get(0).getText());
            product.setPrice(prices.get(0).getText());
            addedProducts.add(product);
            buttons.get(0).click();
        }
        return addedProducts;
    }
    // ============================
    // Cart
    // ============================
    public void openCart() {
        WaitUtil.waitForClickability(cartIcon).click();
    }
    public boolean isCartPageDisplayed() {
        return driver.getCurrentUrl().contains("cart.html");
    }
    public String getCartTitle() {
        return driver.findElement(cartTitle).getText();
    }
    public int getCartBadgeCount() {
        List<WebElement> badge =
                driver.findElements(cartBadge);
        if (badge.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badge.get(0).getText());
    }
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
    // ============================
    // Remove Product
    // ============================
    public Product removeFirstProduct() {
        List<WebElement> names =
                driver.findElements(itemNames);
        List<WebElement> prices =
                driver.findElements(itemPrices);
        Product product = new Product();
        product.setName(names.get(0).getText());
        product.setPrice(prices.get(0).getText());
        WaitUtil.waitForClickability(removeButtons).click();
        return product;
    }
    // ============================
    // Continue Shopping
    // ============================
    public void clickContinueShopping() {
        WaitUtil.waitForClickability(
                continueShoppingButton).click();
    }
} 
package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
public class UIValidationPage {
 
    private WebDriver driver;
 
    public UIValidationPage(WebDriver driver) {
        this.driver = driver;
    }
 
    // =========================
    // Login Page Locators
    // =========================
 
    private By appLogo = By.className("app_logo");
    private By loginButton = By.id("login-button");
 
    // =========================
    // Inventory Page Locators
    // =========================
 
    private By title = By.className("title");
    private By inventoryItems = By.className("inventory_item");
    private By cartIcon = By.className("shopping_cart_link");
    private By menuButton = By.id("react-burger-menu-btn");
 
    // =========================
    // Footer Links
    // =========================
 
    private By twitter = By.cssSelector("a[data-test='social-twitter']");
    private By facebook = By.cssSelector("a[data-test='social-facebook']");
    private By linkedin = By.cssSelector("a[data-test='social-linkedin']");
 
    // =========================
    // SD_TC_043
    // =========================
 
    public boolean isLogoDisplayed() {
        return driver.findElement(appLogo).isDisplayed();
    }
 
    // =========================
    // SD_TC_044
    // =========================
 
    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }
 
    public Point getLoginButtonLocation() {
        return driver.findElement(loginButton).getLocation();
    }
 
    // =========================
    // SD_TC_045
    // =========================
 
    public boolean isInventoryTitleDisplayed() {
        return driver.findElement(title).isDisplayed();
    }
 
    public boolean areProductsDisplayed() {
        return driver.findElements(inventoryItems).size() > 0;
    }
 
    public boolean isShoppingCartDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }
 
    public boolean isMenuButtonDisplayed() {
        return driver.findElement(menuButton).isDisplayed();
    }
 
    // =========================
    // SD_TC_046
    // =========================
 
    public boolean isCartIconVisible() {
        return driver.findElement(cartIcon).isDisplayed();
    }
 
    // =========================
    // SD_TC_047
    // =========================
 
    public boolean isTwitterLinkDisplayed() {
        return driver.findElement(twitter).isDisplayed();
    }
 
    public boolean isFacebookLinkDisplayed() {
        return driver.findElement(facebook).isDisplayed();
    }
 
    public boolean isLinkedInLinkDisplayed() {
        return driver.findElement(linkedin).isDisplayed();
    }
}
 
package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
import utils.WaitUtil;
 
public class LogoutPage {
 
    private WebDriver driver;
 
    public LogoutPage(WebDriver driver) {
 
        this.driver = driver;
 
    }
 
    // ==========================================
    // Locators
    // ==========================================
 
    private By menuButton = By.id("react-burger-menu-btn");
 
    private By logoutButton = By.id("logout_sidebar_link");
 
    private By loginButton = By.id("login-button");
 
    // ==========================================
    // Actions
    // ==========================================
 
    public void clickMenu() {
 
        WaitUtil.waitForClickability(menuButton).click();
 
    }
 
    public void clickLogout() {
 
        WaitUtil.waitForClickability(logoutButton).click();
 
    }
 
    // ==========================================
    // Validation
    // ==========================================
 
    public boolean isLoginPageDisplayed() {
 
        return WaitUtil.waitForVisibility(loginButton).isDisplayed();
 
    }
 
}
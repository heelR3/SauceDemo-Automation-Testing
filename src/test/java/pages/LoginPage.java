package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
public class LoginPage {
 
    private WebDriver driver;
 
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
 
    private By txtUsername = By.id("user-name");
    private By txtPassword = By.id("password");
    private By btnLogin = By.id("login-button");
    private By lblError = By.xpath("//h3[@data-test='error']");
 
    public void enterUsername(String username) {
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);
    }
 
    public void enterPassword(String password) {
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }
 
    public void clickLogin() {
        driver.findElement(btnLogin).click();
    }
 
    public String getErrorMessage() {
        return driver.findElement(lblError).getText();
    }
 
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
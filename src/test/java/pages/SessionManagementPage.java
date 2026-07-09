package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionManagementPage {

    private WebDriver driver;

    public SessionManagementPage(WebDriver driver) {
        this.driver = driver;
    }

    // Inventory page title
    private By inventoryTitle =
            By.xpath("//span[text()='Products']");

    // Cart icon
    private By cartIcon =
            By.className("shopping_cart_link");

    public boolean isInventoryPageDisplayed() {
        return driver.findElement(inventoryTitle).isDisplayed();
    }

    public void refreshBrowser() {
        driver.navigate().refresh();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void openInventoryUrl() {
        driver.get(
            "https://www.saucedemo.com/inventory.html");
    }
}
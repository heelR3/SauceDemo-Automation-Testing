package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccessibilityPage {

    WebDriver driver;

    public AccessibilityPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkLoginElements() {

        boolean usernameDisplayed =
                driver.findElement(By.id("user-name"))
                        .isDisplayed();

        boolean passwordDisplayed =
                driver.findElement(By.id("password"))
                        .isDisplayed();

        boolean loginButtonDisplayed =
                driver.findElement(By.id("login-button"))
                        .isDisplayed();

        System.out.println("Username Field : "
                + usernameDisplayed);

        System.out.println("Password Field : "
                + passwordDisplayed);

        System.out.println("Login Button : "
                + loginButtonDisplayed);
    }
}
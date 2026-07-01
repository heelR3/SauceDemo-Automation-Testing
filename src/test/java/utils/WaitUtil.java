package utils;
 
import java.time.Duration;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
public class WaitUtil {
 
    private static final int DEFAULT_TIMEOUT = 10;
 
    private WaitUtil() {
        // Prevent object creation
    }
 
    private static WebDriverWait getWait() {
 
        return new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(DEFAULT_TIMEOUT));
 
    }
 
    /**
     * Wait until element is visible.
     */
    public static WebElement waitForVisibility(By locator) {
 
        return getWait().until(
                ExpectedConditions.visibilityOfElementLocated(locator));
 
    }
 
    /**
     * Wait until element is clickable.
     */
    public static WebElement waitForClickability(By locator) {
 
        return getWait().until(
                ExpectedConditions.elementToBeClickable(locator));
 
    }
 
    /**
     * Wait until element disappears.
     */
    public static boolean waitForInvisibility(By locator) {
 
        return getWait().until(
                ExpectedConditions.invisibilityOfElementLocated(locator));
 
    }
 
    /**
     * Wait until page title contains expected text.
     */
    public static boolean waitForTitleContains(String title) {
 
        return getWait().until(
                ExpectedConditions.titleContains(title));
 
    }
 
    /**
     * Wait until URL contains expected text.
     */
    public static boolean waitForUrlContains(String url) {
 
        return getWait().until(
                ExpectedConditions.urlContains(url));
 
    }
 
    /**
     * Wait until specific text is present in element.
     */
    public static boolean waitForText(By locator, String text) {
 
        return getWait().until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text));
 
    }
 
    /**
     * Wait until alert is present.
     */
    public static void waitForAlert() {
 
        getWait().until(
                ExpectedConditions.alertIsPresent());
 
    }
 
}
package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PerformancePage {

    WebDriver driver;

    public PerformancePage(WebDriver driver) {
        this.driver = driver;
    }

    public long getPageLoadTime() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        long start = System.currentTimeMillis();

        js.executeScript("window.location.reload();");

        long end = System.currentTimeMillis();

        return end - start;
    }
}
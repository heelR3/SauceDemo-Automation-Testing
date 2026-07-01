package utils;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
 
public class ScreenshotUtil {
 
    private ScreenshotUtil() {
    }
 
    /**
     * Capture screenshot and save inside the given feature folder.
     *
     * Example:
     * screenshots/Inventory/
     * screenshots/Login/
     * screenshots/Cart/
     */
    public static void captureScreenshot(String featureFolder,
                                         String screenshotName) {
 
        try {
 
            WebDriver driver = DriverFactory.getDriver();
 
            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);
 
            String timestamp =
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern(
                                    "yyyyMMdd_HHmmss"));
 
            String directory =
                    "screenshots"
                            + File.separator
                            + featureFolder;
 
            File folder = new File(directory);
 
            if (!folder.exists()) {
 
                folder.mkdirs();
 
            }
 
            File destinationFile =
                    new File(directory
                            + File.separator
                            + screenshotName
                            + "_"
                            + timestamp
                            + ".png");
 
            Files.copy(
                    sourceFile.toPath(),
                    destinationFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
 
            System.out.println(
                    "Screenshot Saved : "
                            + destinationFile.getAbsolutePath());
 
        }
 
        catch (IOException e) {
 
            throw new RuntimeException(
                    "Unable to capture screenshot.",
                    e);
 
        }
 
    }
 
}
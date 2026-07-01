package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static WebDriver driver;

	public static void initDriver() {

		String browser = ConfigReader.getProperty("browser");

		switch (browser.toLowerCase()) {

		case "chrome":

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();

			break;

		default:

			throw new RuntimeException("Unsupported Browser");

		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(

				Duration.ofSeconds(

						Long.parseLong(

								ConfigReader.getProperty("implicitWait"))));

		driver.get(

				ConfigReader.getProperty("url"));

	}

	public static WebDriver getDriver() {

		return driver;

	}

	public static void quitBrowser() {

		if (driver != null) {

			driver.quit();

			driver = null;

		}

	}

}
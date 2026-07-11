package utils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void initDriver(String browser) {

		//String browser = System.getProperty("browser",ConfigReader.getProperty("browser"));
		
		switch (browser.toLowerCase()) {

		case "chrome":

			WebDriverManager.chromedriver().setup();
			 
			ChromeOptions options = new ChromeOptions();

			Map<String, Object> prefs = new HashMap<>();

			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);

			options.setExperimentalOption("prefs", prefs);

			options.addArguments("--disable-notifications");
			options.addArguments("--disable-features=PasswordLeakDetection");

			driver.set(new ChromeDriver(options));

			break;
			
		case "firefox":

		    WebDriverManager.firefoxdriver().setup();

		    FirefoxOptions firefoxOptions = new FirefoxOptions();

		    driver.set(new FirefoxDriver(firefoxOptions));

		    break;

		case "edge":

		    System.setProperty(
		        "webdriver.edge.driver",
		        "C:\\Users\\RU20695960\\.cache\\selenium\\msedgedriver\\msedgedriver.exe");

		    EdgeOptions edgeOptions = new EdgeOptions();

		    driver.set(new EdgeDriver(edgeOptions));

		    break;
			

		default:

			throw new RuntimeException("Unsupported Browser");

		}

		driver.get().manage().window().maximize();

		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));

		driver.get().get(ConfigReader.getProperty("url"));
	}

	public static WebDriver getDriver() {
	    return driver.get();
	}

	public static void quitBrowser() {

		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}

	}

}
package hooks;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.ExcelWriter;
 
public class Hooks {
	
	private static boolean checkoutSheetInitialized = false;
 
	@Before()
	public void beforeScenario() {
 
		DriverFactory.initDriver();
 
	}
 
	@Before("@Cart")
	public void beforeCartScenario() {
 
		ExcelWriter.createCartHeader();
 
		ExcelWriter.clearCartSheet();
 
	}
 
	@Before("@Checkout")
	public void beforeCheckoutScenario() {
 
		if (!checkoutSheetInitialized) {
 
			ExcelWriter.createCheckoutHeader();
			ExcelWriter.clearCheckoutSheet();
 
			checkoutSheetInitialized = true;
		}
 
	}
	
	@Before("@Logout")
	public void beforeLogoutScenario() {
	 
	    ExcelWriter.createLogoutHeader();
	 
	    ExcelWriter.clearLogoutSheet();
	 
	}
	
	private static boolean errorSheetInitialized = false;	
	@Before("@ErrorMessages")
	public void beforeErrorMessagesScenario() {
	
	    if (!errorSheetInitialized) {
	        ExcelWriter.clearErrorSheet();
	        ExcelWriter.createErrorHeader();
	
	        errorSheetInitialized = true;
	    }
	}
	 
 
	@After
	public void afterScenario() {
 
		DriverFactory.quitBrowser();
 
	}
 
}
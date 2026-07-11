package hooks;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.BrowserContext;
import utils.DriverFactory;
import utils.ExcelWriter;
 
public class Hooks {
	
	private static volatile boolean checkoutSheetInitialized = false;
 
	@Before
	public void beforeScenario(io.cucumber.java.Scenario scenario) {
		DriverFactory.initDriver(BrowserContext.getBrowser());
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
	
	private static volatile boolean errorSheetInitialized = false;	
	
	@Before("@ErrorMessages")
	public void beforeErrorMessagesScenario() {
	
	    if (!errorSheetInitialized) {
	        ExcelWriter.clearErrorSheet();
	        ExcelWriter.createErrorHeader();
	
	        errorSheetInitialized = true;
	    }
	}
	 
	private static volatile boolean uiValidationSheetInitialized = false;	
	
	@Before("@UIValidation")
	public void beforeuiValidationScenario() {
		
		if (!uiValidationSheetInitialized) {
			ExcelWriter.clearUIValidationSheet();
			ExcelWriter.createUIValidationHeader();
			
			uiValidationSheetInitialized = true;
		}
	}
	
	private static volatile boolean sessionManagementSheetInitialized = false;	
	
	@Before("@UIValidation")
	public void beforeSessionManagementScenario() {
		
		if (!sessionManagementSheetInitialized) {
			ExcelWriter.clearSessionSheet();
			ExcelWriter.createSessionHeader();
			
			sessionManagementSheetInitialized = true;
		}
	}
	
 
	@After
	public void afterScenario() {
 
		DriverFactory.quitBrowser();
 
	}
 
}
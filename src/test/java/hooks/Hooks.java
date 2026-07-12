package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.BrowserContext;
import utils.DriverFactory;
import utils.ExcelWriter;
import utils.ExecutionTimer;
import utils.PerformanceManager;

public class Hooks {

	private static volatile boolean checkoutSheetInitialized = false;

	@Before
	public void beforeScenario(io.cucumber.java.Scenario scenario) {
		ExecutionTimer.startTimer();
		DriverFactory.initDriver(BrowserContext.getBrowser());
	}

	private static volatile boolean cartSheetInitialized = false;
	@Before("@Cart")
	public void beforeCartScenario() {
	    if (!cartSheetInitialized) {
	        ExcelWriter.clearCartSheet();
	        ExcelWriter.createCartHeader();
	        cartSheetInitialized = true;
	    }
	}

	@Before("@Checkout")
	public void beforeCheckoutScenario() {

		if (!checkoutSheetInitialized) {
			ExcelWriter.clearCheckoutSheet();
			ExcelWriter.createCheckoutHeader();
			checkoutSheetInitialized = true;
		}

	}
	
	private static volatile boolean logoutSheetInitialized = false;
	@Before("@Logout")
	public void beforeLogoutScenario() {
	    if (!logoutSheetInitialized) {
	        ExcelWriter.clearLogoutSheet();
	        ExcelWriter.createLogoutHeader();
	        logoutSheetInitialized = true;
	    }
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
	@Before("@SessionManagement")
	public void beforeSessionManagementScenario() {
		if (!sessionManagementSheetInitialized) {
			ExcelWriter.clearSessionSheet();
			ExcelWriter.createSessionHeader();
			sessionManagementSheetInitialized = true;
		}
	}
	
	private static volatile boolean inventorySheetInitialized = false;
	@Before
	public void beforeInventorySheet() {
	    synchronized (Hooks.class) {
	        if (!inventorySheetInitialized) {
	            ExcelWriter.clearInventorySheet();
	            ExcelWriter.createInventoryHeader();
	            inventorySheetInitialized = true;
	        }
	    }
	}

	private static volatile boolean performanceSheetInitialized = false;
	@Before
	public void beforePerformanceScenario() {
	    synchronized (Hooks.class) {
	        if (!performanceSheetInitialized) {
	            ExcelWriter.clearPerformanceSheet();
	            ExcelWriter.createPerformanceHeader();
	            performanceSheetInitialized = true;
	        }
	    }
	}

	@After
	public void tearDown(Scenario scenario) {

		long duration = ExecutionTimer.stopTimer();

		String browser = BrowserContext.getBrowser();

		String testCaseId =ExcelWriter.getTestCaseIdByScenarioName(scenario.getName());

		PerformanceManager.recordExecution(testCaseId, browser, duration);

		DriverFactory.quitBrowser();
	}
}
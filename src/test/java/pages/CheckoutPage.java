package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
 
import utils.WaitUtil;
 
public class CheckoutPage {
 
	private WebDriver driver;
 
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
 
	// =====================================================
	// Locators
	// =====================================================
 
	private By checkoutButton = By.id("checkout");
 
	private By firstName = By.id("first-name");
 
	private By lastName = By.id("last-name");
 
	private By postalCode = By.id("postal-code");
 
	private By continueButton = By.id("continue");
 
	private By cancelButton = By.id("cancel");
 
	private By finishButton = By.id("finish");
 
	private By pageTitle = By.className("title");
 
	private By errorMessage = By.cssSelector("h3[data-test='error']");
 
	private By completeHeader = By.className("complete-header");
 
	private By completeText = By.className("complete-text");
 
	// =====================================================
	// Checkout Information
	// =====================================================
 
	public void clickCheckout() {
 
		WaitUtil.waitForClickability(checkoutButton).click();
 
	}
 
	public void enterFirstName(String value) {
 
		WaitUtil.waitForVisibility(firstName).clear();
		if (value != null) {
			WaitUtil.waitForVisibility(firstName).sendKeys(value);
		}
 
	}
 
	public void enterLastName(String value) {
 
		WaitUtil.waitForVisibility(lastName).clear();
		if (value != null) {
			WaitUtil.waitForVisibility(lastName).sendKeys(value);
		}
 
	}
 
	public void enterPostalCode(String value) {
 
		WaitUtil.waitForVisibility(postalCode).clear();
		if (value != null) {
			WaitUtil.waitForVisibility(postalCode).sendKeys(value);
		}
 
	}
 
	public void clickContinue() {
 
		WaitUtil.waitForClickability(continueButton).click();
 
	}
 
	public void clickCancel() {
 
		WaitUtil.waitForClickability(cancelButton).click();
 
	}
 
	// =====================================================
	// Overview
	// =====================================================
 
	public String getPageTitle() {
 
		return WaitUtil.waitForVisibility(pageTitle).getText();
 
	}
 
	// =====================================================
	// Finish
	// =====================================================
 
	public void clickFinish() {
 
		WaitUtil.waitForClickability(finishButton).click();
 
	}
 
	public String getCompleteHeader() {
 
		return WaitUtil.waitForVisibility(completeHeader).getText();
 
	}
 
	public String getCompleteText() {
 
		return WaitUtil.waitForVisibility(completeText).getText();
 
	}
 
	// =====================================================
	// Validation
	// =====================================================
 
	public String getErrorMessage() {
 
		return WaitUtil.waitForVisibility(errorMessage).getText();
 
	}
 
}
package stepdefinitions;
 
import io.cucumber.java.en.And;
import pages.InventoryPage;
import utils.DriverFactory;
 
public class ResetAppSteps {
 
    InventoryPage inventoryPage =
            new InventoryPage(DriverFactory.getDriver());
 
    @And("User resets app state")
    public void user_resets_app_state() throws InterruptedException {
 
        inventoryPage.resetAppState();
    }
}
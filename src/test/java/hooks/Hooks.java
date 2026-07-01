package hooks;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.ExcelWriter;
 
public class Hooks {
 
    @Before(order = 0)
    public void beforeScenario() {
 
        DriverFactory.initDriver();
 
    }
 
    @Before(order = 1)
    public void prepareInventorySheet() {
 
        try {
 
            ExcelWriter.createInventoryHeader();
 
        } catch (Exception e) {
 
            System.out.println("Inventory sheet already exists.");
 
        }
 
    }
 
    @After
    public void afterScenario() {
 
        DriverFactory.quitBrowser();
 
    }
 
}
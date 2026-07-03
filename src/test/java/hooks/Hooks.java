package hooks;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
import utils.ExcelWriter;
 
public class Hooks {
 
    @Before()
    public void beforeScenario() {
 
        DriverFactory.initDriver();
 
    }
    @Before("@Cart")
    public void beforeCartScenario() {
     
        ExcelWriter.createCartHeader();
     
        ExcelWriter.clearCartSheet();
     
    }
 
    @After
    public void afterScenario() {
 
        DriverFactory.quitBrowser();
 
    }
    
 
}
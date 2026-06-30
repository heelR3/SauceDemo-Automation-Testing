package hooks;
 
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.DriverFactory;
 
public class Hooks {
 
    @Before
    public void setup() {
 
        DriverFactory.initDriver();
 
    }
 
    @After
    public void tearDown() {
 
        DriverFactory.quitBrowser();
 
    }
 
}
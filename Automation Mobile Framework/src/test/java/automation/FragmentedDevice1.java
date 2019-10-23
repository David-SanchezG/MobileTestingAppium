package automation;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import automation.helpers.LocalDriver;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
        },
        features = {
                "src/test/resources/automation/Features"
        }
)
public class FragmentedDevice1 extends AbstractTestNGCucumberTests {

    @BeforeMethod
    @Parameters({"version", "platformName"})
    public void getDevice(String version, String platformName) {
        LocalDriver.setVersion(version);
        LocalDriver.setPlatform(platformName);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
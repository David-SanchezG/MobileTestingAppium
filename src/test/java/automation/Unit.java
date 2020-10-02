package automation;

import automation.helpers.LocalDriver;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;


@Test
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json",
        },
        features = {
                "src/test/resources/automation/features"
        }
)
public class Unit extends AbstractTestNGCucumberTests {

    @BeforeMethod
    @Parameters({"version", "platformName"})
    public void getDevice(String version, String platformName) {
        LocalDriver.setVersion(version);
        LocalDriver.setPlatform(platformName);
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}
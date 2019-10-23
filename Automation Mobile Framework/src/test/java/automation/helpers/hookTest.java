package automation.helpers;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class hookTest {


    private World world;
    private DesiredCapabilities caps;

    public hookTest(World world) {
        this.world = world;
    }

    //    before scenario
    @Before
    public void setup(Scenario scenario) throws Exception {
        String runOn = System.getProperty("runOn");
        String gridURL;
        String appVersion = System.getProperty("appVersion");
        if (runOn.equals("local")) {
            gridURL = "http://127.0.0.1:4444/wd/hub";
        } else {
            gridURL = "http://192.168.1.42:4444/wd/hub";
        }

        scenario.write(LocalDriver.getPlatform() + " - " + LocalDriver.getVersion());
        caps = new DesiredCapabilities();

        caps.setCapability("newCommandTimeout", 20);
        caps.setCapability("deviceReadyTimeout", 60);
        caps.setCapability("androidDeviceReadyTimeout", 60);
        caps.setCapability("androidInstallTimeout", 60000);

        String currentDir = Paths.get("").toAbsolutePath().toString() + "/";

        if (LocalDriver.getVersion() != null) {
            caps.setCapability(MobileCapabilityType.BROWSER_VERSION, LocalDriver.getVersion());
        }
        if (LocalDriver.getPlatform().equals("Android")) {
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.APP, currentDir + "app-x86-" + appVersion + ".apk");
            world.driver = new AndroidDriver<MobileElement>(new URL(gridURL), caps);
        } else {
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            caps.setCapability(MobileCapabilityType.APP, currentDir + "myApp.app");
            world.driver = new IOSDriver(new URL(gridURL), caps);
        }
        world.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        world.wait = new FluentWait<>(world.driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        System.out.println(scenario.getName());
    }

    @Before("@SomeTag")
    public void beforeSomething(Scenario scenario) {

    }

    //    after scenario
    @After
    public void tearDown(Scenario scenario) {
        System.out.println(scenario.getName() + " --> " + scenario.getStatus());
        if (scenario.isFailed()) {
            scenario.embed(((TakesScreenshot) this.world.driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        world.driver.closeApp();
        try {
            world.driver.quit();
        } catch (Exception e) {
            System.out.println("driver.quit error");
        }
    }

}
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
import org.testng.annotations.Parameters;

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

    @Before
    public void setup(Scenario scenario) throws Exception {

        String appiumAddress = "http://127.0.0.1:4723/wd/hub";

        scenario.write(LocalDriver.getPlatform() + " - " + LocalDriver.getVersion());
        caps = new DesiredCapabilities();

        caps.setCapability("newCommandTimeout", 20);
        caps.setCapability("deviceReadyTimeout", 60);

        String currentDir = Paths.get("").toAbsolutePath().toString() + "/";

        if (LocalDriver.getPlatform().equals("Android")) {
            caps.setCapability("androidDeviceReadyTimeout", 60);
            caps.setCapability("androidInstallTimeout", 60000);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.APP, currentDir + "apidemos.apk");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel7.0");
            this.world.driver = new AndroidDriver<MobileElement>(new URL(appiumAddress), caps);
        } else {
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, LocalDriver.getVersion());
            caps.setCapability(MobileCapabilityType.APP, currentDir + "UIKitCatalog.app");
            this.world.driver = new IOSDriver(new URL(appiumAddress), caps);
        }
        this.world.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.world.wait = new FluentWait<>(this.world.driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        System.out.println(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println(scenario.getName() + " --> " + scenario.getStatus());
        if (scenario.isFailed()) {
            scenario.embed(((TakesScreenshot) this.world.driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        this.world.driver.closeApp();
        try {
            this.world.driver.quit();
        } catch (Exception e) {
            System.out.println("driver.quit error");
        }
    }

}
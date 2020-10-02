package automation.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class World {

    public AppiumDriver driver;
    public FluentWait wait;

    public World() {

    }

    public void waitForVisibility(MobileElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isToastVisible(String toastMessage) throws InterruptedException {
        String xml1 = this.driver.getPageSource();
        return xml1.contains(toastMessage);
    }

    public static boolean isPresent(MobileElement element) {
        boolean isPresent = true;
        try {
            element.isDisplayed();
        } catch (Exception e) {
            isPresent = false;
        }
        return isPresent;
    }

    public void waitAndClick(MobileElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void clickAndSet(MobileElement element, String keys) {
        element.click();
        element.sendKeys(keys);
    }

    public void scroll(MobileElement element, String direction) {
        Dimension size = this.driver.manage().window().getSize();
        Point elementPoint = element.getLocation();
        int endPointY = 0;
        if (direction.equals("up")) {
            endPointY = size.height - 5;
        }
        new TouchAction(this.driver)
                .press(point(element.getCenter().x, elementPoint.y))
                .waitAction(waitOptions(ofMillis(500)))
                .moveTo(point(element.getCenter().x, endPointY))
                .waitAction(waitOptions(ofMillis(500)))
                .release().perform();
    }

}

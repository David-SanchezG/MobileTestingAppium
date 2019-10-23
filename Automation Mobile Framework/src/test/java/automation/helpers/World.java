package automation.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class World {

    public AppiumDriver driver;
    public FluentWait wait;
    public String username;
    public String password;
    public Service service;

    public World() {
        service = new Service();
    }

    public void waitForVisibility(MobileElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibility(MobileElement element, int timeout) {
        FluentWait oneTimeWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        oneTimeWait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isToastVisible(String toastMessage) throws InterruptedException {
        String xml1 = this.driver.getPageSource();
        return xml1.contains(toastMessage);
    }

    public void waitForNotVisible(MobileElement element) {
        this.wait.until(waitForInvisible(element));
    }

    public static ExpectedCondition<Boolean> waitForInvisible(final MobileElement element) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return !isPresent(element);
            }

            public String toString() {
                return "invisibility of " + element;
            }
        };
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

    public void verifyNotPresent(MobileElement element) {
        Assert.assertFalse(isPresent(element));
    }

    public void waitAndClickIfPresent(MobileElement element, int timeout) {
        FluentWait oneTimeWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try{
            oneTimeWait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch(Exception error){
            System.out.println("Permissions granted");
        }
    }

    public void waitAndClick(MobileElement element) {
        this.wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void waitAndClick(MobileElement element, int timeout) {
        FluentWait oneTimeWait = new FluentWait<>(this.driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        oneTimeWait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    public void clickAndSet(MobileElement element, String keys) {
        element.click();
        element.sendKeys(keys);
    }

    public void swipe(MobileElement element, String direction) {
        Dimension size = this.driver.manage().window().getSize();
        int startPointX = size.width - 5;
        int endPointX = 0;
        if (direction.equals("right")) {
            startPointX = 0;
            endPointX = size.width - 5;
        }
        new TouchAction(this.driver)
                .press(point(startPointX, element.getCenter().y - 5))
                .waitAction(waitOptions(ofMillis(500)))
                .moveTo(point(endPointX, element.getCenter().y - 5))
                .release().perform();
    }

}

package automation.step_definitions;

import automation.helpers.World;
import automation.pages.AndroidPages;
import automation.pages.iOSPages;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.util.Date;

public class AndroidSteps {

    private World world;
    private AndroidPages androidPages;

    public AndroidSteps(World world) {
        this.world = world;
        androidPages = new AndroidPages(this.world);
    }

    @When("I navigate to Preference dependencies")
    public void I_navigate_to_Preference_dependencies() {
        this.world.waitAndClick(androidPages.preference);
        this.world.waitAndClick(androidPages.PreferenceDependencies);

    }

    @When("Turn ON WiFi option")
    public void Turn_ON_WiFi_option() {
        this.world.waitAndClick(androidPages.checkBoxWifi);

    }

    @When("Select WiFi Settings and write {word} and click OK to close the dialog")
    public void Select_WiFi_Settings_and_write_text_and_click_OK_to_close_the_dialog(String expectedText) {
        this.world.waitAndClick(androidPages.wifiSettings);
        this.world.waitForVisibility(androidPages.textFieldWifiSettings);
        this.world.clickAndSet(androidPages.textFieldWifiSettings, expectedText);
        androidPages.buttonOk.click();
    }

    @Then("Wifi should be enable")
    public void Wifi_should_be_enable() {
        this.world.waitForVisibility(androidPages.checkBoxWifi);
        Assert.assertEquals(androidPages.checkBoxWifi.getAttribute("checked"), "true");
    }

    @When("Wifi settings should has the text {word}")
    public void Wifi_settings_should_has_the_text(String expectedText) {
        this.world.waitAndClick(androidPages.wifiSettings);
        this.world.waitForVisibility(androidPages.textFieldWifiSettings);
        Assert.assertEquals(androidPages.textFieldWifiSettings.getText(), expectedText);
    }

    @Given("I navigate to Views -> Expandable Lists -> Custom Adapter")
    public void i_navigate_to_Views_Expandable_Lists_Custom_Adapter() {
        this.world.waitAndClick(androidPages.views);
        this.world.waitAndClick(androidPages.expandableLists);
        this.world.waitAndClick(androidPages.customAdapter);
    }

    @When("I Perform a long tap on People Names row")
    public void i_Perform_a_long_tap_on_People_Names_row() {
        Point coordinates = androidPages.peopleNames.getLocation();
        new TouchAction(this.world.driver)
                .longPress(PointOption.point(coordinates.x, coordinates.y))
                .perform();
    }

    @When("Select Sample action in the Sample menu")
    public void select_Sample_action_in_the_Sample_menu() {
        this.world.waitAndClick(androidPages.sampleAction);
    }

    @Then("A Toast appears with some text")
    public void a_Toast_appears_with_some_text() throws InterruptedException {
        Assert.assertTrue(this.world.isToastVisible("People Names: Group 0 clicked"));
    }

    @Given("I navigate through Views -> Drag and Drop")
    public void i_navigate_through_Views_Drag_and_Drop() {
        this.world.waitAndClick(androidPages.views);
        this.world.waitAndClick(androidPages.dragAndDrop);
    }

    @When("I Drag the top left circle to the top right circle")
    public void i_Drag_the_top_left_circle_to_the_top_right_circle() {
        Point firstDotCoordinates = androidPages.firstDot.getLocation();
        Point secondDotCoordinates = androidPages.secondDot.getLocation();
        new TouchAction(this.world.driver)
                .press(PointOption.point(firstDotCoordinates.x, firstDotCoordinates.y))
                .moveTo(PointOption.point(secondDotCoordinates.x, secondDotCoordinates.y))
                .release()
                .perform();
    }

    @Then("I should see a text appears at the bottom saying {string}")
    public void i_should_see_a_text_appears_at_the_bottom_saying(String expectedText) {
        this.world.waitForVisibility(androidPages.dragresult);
        Assert.assertEquals(androidPages.dragresult.getText(), expectedText);
    }

    @Given("I navigate through Views -> Search View -> Filter")
    public void i_navigate_through_Views_Search_View_Filter() {
        this.world.waitAndClick(androidPages.views);
        while (!this.world.isPresent(androidPages.searchView)) {
            int listSize = androidPages.textViewElements.size();
            this.world.scroll(androidPages.textViewElements.get(listSize - 1), "down");
        }
        this.world.waitAndClick(androidPages.searchView);
        this.world.waitAndClick(androidPages.filter);
    }

    @When("I write {string} as text filter")
    public void i_write_as_text_filter(String searchText) {
        this.world.waitForVisibility(androidPages.textFieldSearch);
        this.world.clickAndSet(androidPages.textFieldSearch, searchText);
    }

    @Then("The first result should be {word}")
    public void the_first_result_should_be_Colby(String expectedText) {
        this.world.waitForVisibility(androidPages.searchResult);
        Assert.assertEquals(androidPages.searchResult.getText(), expectedText);
    }

    @Given("navigate through Views -> WebView")
    public void navigate_through_Views_WebView() {
        this.world.waitAndClick(androidPages.views);
        while (!this.world.isPresent(androidPages.webView)) {
            int listSize = androidPages.textViewElements.size();
            this.world.scroll(androidPages.textViewElements.get(listSize - 1), "down");
        }
        this.world.waitAndClick(androidPages.webView);
    }

    @When("I tap on the 'I am a link' link")
    public void i_tap_on_the_link() {
        this.world.waitAndClick(androidPages.webViewLink);
    }

    @Then("I should see a web with the text {string} appears")
    public void i_should_see_a_web_with_the_text_appears(String expectedText) {
        this.world.waitForVisibility(androidPages.webViewText);
    }

}

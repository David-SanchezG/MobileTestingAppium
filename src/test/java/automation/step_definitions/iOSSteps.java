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

public class iOSSteps {

    private World world;
    private iOSPages iOSPages;

    public iOSSteps(World world) {
        this.world = world;
        iOSPages = new iOSPages(this.world);
    }

    @Given("I navigate to Picker View")
    public void i_navigate_to_Picker_View() {
        this.world.waitAndClick(iOSPages.pickerView);
    }

    @When("I set the colors to {int}, {int}, {int}")
    public void i_set_the_colors_to(Integer int1, Integer int2, Integer int3) {
        this.world.waitForVisibility(iOSPages.redColor);
        iOSPages.redColor.sendKeys(int1.toString());
        iOSPages.greenColor.sendKeys(int2.toString());
        iOSPages.blueColor.sendKeys(int3.toString());
    }

    @Then("I should see the color numbers {int}, {int}, {int}")
    public void i_should_see_the_color_numbers_I_wanted(Integer int1, Integer int2, Integer int3) {
        Assert.assertEquals(iOSPages.redColor.getText(), int1.toString());
        Assert.assertEquals(iOSPages.greenColor.getText(), int2.toString());
        Assert.assertEquals(iOSPages.blueColor.getText(), int3.toString());
    }

    @Given("I navigate to Stack Views")
    public void i_navigate_to_Stack_Views() {
        this.world.waitAndClick(iOSPages.stackViews);
    }

    @When("I add further details")
    public void i_add_further_details() {
        this.world.waitForVisibility(iOSPages.plusDetail);
        iOSPages.plusDetail.click();
    }

    @When("I add a view")
    public void i_add_a_view() {
        iOSPages.plusView.click();
    }

    @Then("I should see further detail")
    public void i_should_see_further_detail() {
        this.world.waitForVisibility(iOSPages.furtherDetailText);
    }

    @Then("I should see a new view")
    public void i_should_see_a_new_view() {
        Assert.assertTrue(iOSPages.listAreas.size() >= 3);
    }

    @Given("I navigate to Date Picker")
    public void i_navigate_to_Date_Picker() {
        this.world.waitAndClick(iOSPages.datePicker);
    }

    @When("I set the time to tomorrow at {int} hours and {int} minutes")
    public void i_set_the_time_to_tomorrow_at_hours_and_minutes(Integer hours, Integer minutes) {
        this.world.waitForVisibility(iOSPages.day);
        Date tomorrow = new Date((new Date()).getTime() + (1000 * 60 * 60 * 24));
        String[] dateParts = tomorrow.toString().split(" ");
        iOSPages.day.sendKeys(dateParts[1] + " " + Integer.parseInt(dateParts[2]));
        if (hours > 12) {
            iOSPages.dayPart.sendKeys("PM");
        } else {
            iOSPages.dayPart.sendKeys("AM");
        }
        int analogTime = hours % 13 + 1;
        iOSPages.hour.sendKeys(analogTime + "");
        iOSPages.minutes.sendKeys(minutes.toString());
    }

    @Then("I should see a text with tomorrow at {int} hours and {int} minutes")
    public void i_should_see_a_text_with_tomorrow_at_hours_and_minutes(Integer hours, Integer minutes) {
        Date tomorrow = new Date((new Date()).getTime() + (1000 * 60 * 60 * 24));
        String[] dateParts = tomorrow.toString().split(" ");
        Assert.assertTrue(iOSPages.date.getText().contains(dateParts[1]));
        Assert.assertTrue(iOSPages.date.getText().contains(Integer.parseInt(dateParts[2]) + ""));
        Assert.assertTrue(iOSPages.date.getText().contains(hours % 13 + 1 + ":" + minutes));
    }

}

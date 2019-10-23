package automation.step_definitions;

import automation.helpers.World;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Navigation {

    private World world;
    private Page1 navigationBar;
    private Page2 idNowPage;

    public Navigation(World world) {
        this.world = world;
        page1 = new Page1(this.world);
        page2 = new Page2(this.world);
    }

    @When("I navigate to privacy")
    public void I_navigate_to_privacy() {

    }

    @Then("I navigate to data protection")
    public void I_navigate_to_data_protection() {

    }

}

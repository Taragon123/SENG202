package seng202.teamsix.data;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MenuOperationsStepDefs {
    @Given("The current price of a burger is ${int}")
    public void theCurrentPriceOfABurgerIs$(int arg0) {
    }

    @When("The user selects change price")
    public void theUserSelectsChangePrice() {
    }

    @And("Enters new price value \\(${int})")
    public void entersNewPriceValue$(int arg0) {
    }

    @And("Selects save changes")
    public void selectsSaveChanges() {
    }

    @Then("The current price of a burger is now the new price \\(${int})")
    public void theCurrentPriceOfABurgerIsNowTheNewPrice$(int arg0) {
    }

}

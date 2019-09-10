package seng202.teamsix.data;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AcceptanceTestStepDefs {
    CashRegister cashRegister;
    @Given("${int} current total cash")
    public void $CurrentTotalCash(int arg0) {
        cashRegister = new CashRegister();
        //cashRegister.setDollars(arg0);
    }

    @When("${int} is add to the current total cash")
    public void $IsAddToTheCurrentTotalCash(int arg0) {
    }

    @Then("The current total cash is now ${int}")
    public void theCurrentTotalCashIsNow$(int arg0) {
    }
}

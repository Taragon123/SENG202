package seng202.teamsix.data;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashRegisterOperationsStepDefs {
    CashRegister cashRegister;

    @Given("${int} current total cash")
    public void $CurrentTotalCash(int arg0) {
        cashRegister = new CashRegister();

        cashRegister.setRegisterAmount(10000);
    }

    @When("${int} is add to the current total cash")
    public void $IsAddToTheCurrentTotalCash(int arg0) {
        cashRegister.setRegisterAmount(5000);
    }

    @Then("The current total cash is now ${int}")
    public void theCurrentTotalCashIsNow$(int arg0) {
        assertEquals(150, cashRegister.getRegisterAmount());
    }

    @When("${int} is removed from the current total cash")
    public void $IsRemovedFromTheCurrentTotalCash(int arg0) {
        cashRegister.setRegisterAmount(-5000);
    }

    @Then("Current total cash is now ${int}")
    public void currentTotalCashIsNow$(int arg0) {
        assertEquals(100, cashRegister.getRegisterAmount());
    }
}

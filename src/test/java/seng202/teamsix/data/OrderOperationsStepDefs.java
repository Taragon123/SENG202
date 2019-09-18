package seng202.teamsix.data;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import seng202.teamsix.managers.OrderManager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderOperationsStepDefs {
    OrderManager order = new OrderManager();
    Currency base = new Currency();
    Currency markup = new Currency();

    @Given("Current order contains only one burger")
    public void currentOrderContainsOnlyOneBurger() {

        ArrayList<ItemTag_Ref> tags = new ArrayList();
        ItemTag_Ref tag_ref = new ItemTag_Ref();
        tags.add(tag_ref);

        base.setCents(10000);
        markup.setCents(1500);

        Item burger = new Item("Burger", "Simple burger", base, markup,
                tags, UnitType.L);

        order.addToCart(burger, 1);
    }

    @When("Chips are added to the current order")
    public void chipsAreAddedToTheCurrentOrder() {
        base.setCents(5000);
        markup.setCents(1500);

        ArrayList<ItemTag_Ref> tags = new ArrayList();
        ItemTag_Ref tag_ref = new ItemTag_Ref();
        tags.add(tag_ref);

        Item chips = new Item("Chips", "Hot chips", base, markup,
                tags, UnitType.ML);

        order.addToCart(chips, 1);
    }

    @Then("The current order consists of one burger and one chips")
    public void theCurrentOrderConsistsOfOneBurgerAndOneChips() {

       // assertEquals("Burger", item.getName());
    }
}

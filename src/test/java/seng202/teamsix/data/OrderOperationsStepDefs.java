package seng202.teamsix.data;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.junit.jupiter.api.extension.ExtensionContext;
import seng202.teamsix.managers.OrderManager;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderOperationsStepDefs {
    Currency price = new Currency();
    OrderItem cart = new OrderItem();
    MenuItem menuBurger = new MenuItem();
    Item burger;

    @Given("Current order contains only one burger")
    public void currentOrderContainsOnlyOneBurger() {
        price.setCents(1000);
        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID("79e1c5bf-ecca-4d8b-a3a5-1c0166c9f994");
        burger = StorageAccess.instance().getItem(burger_ref);

        menuBurger.setItem(burger_ref);
        cart.addToOrder(burger_ref, 1, price, 0);
    }

    @When("Chips are added to the current order")
    public void chipsAreAddedToTheCurrentOrder() {
    }

    @Then("The current order consists of one burger and one chips")
    public void theCurrentOrderConsistsOfOneBurgerAndOneChips() {
    }
}

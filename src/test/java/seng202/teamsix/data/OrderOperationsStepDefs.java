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
    OrderManager cart = new OrderManager();
    Currency base = new Currency();
    Currency markup = new Currency();

    @Given("Current order contains only one burger")
    public void currentOrderContainsOnlyOneBurger() {
        base.setCents(10000);
        markup.setCents(1500);

        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        Item burger = StorageAccess.instance().getItem(burger_ref);
        StorageAccess.instance().updateItem(burger);
        MenuItem menuBurger = new MenuItem();
        menuBurger.setItem(burger);
        cart.addToCart(menuBurger, 1);
    }

    @When("Chips are added to the current order")
    public void chipsAreAddedToTheCurrentOrder() {
        base.setCents(5000);
        markup.setCents(1500);

        Item_Ref my_ref = new Item_Ref();
        my_ref.setUUID("79e1c5bf-ecca-4d8b-a3a5-1c0166c9f994");
        Item chips = StorageAccess.instance().getItem(my_ref);
        StorageAccess.instance().updateItem(chips);
        MenuItem menuBurger = new MenuItem();
        menuBurger.setItem(chips);
        cart.addToCart(menuBurger, 1);
    }

    @Then("The current order consists of one burger and one chips")
    public void theCurrentOrderConsistsOfOneBurgerAndOneChips() {

        System.out.println(cart.getCart().getOrderTree().toString());
    }
}

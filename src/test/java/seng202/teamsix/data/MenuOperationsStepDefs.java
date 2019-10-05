package seng202.teamsix.data;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuOperationsStepDefs {
    Currency price = new Currency();
    MenuItem menu_burger = new MenuItem();
    Menu testMenu = new Menu();
    Recipe testRecipe;
    Item burger = new Item();
    Item burger2 = new Item();
    Item burger3 = new Item();

    @Given("The current price of a burger is ${int}")
    public void theCurrentPriceOfABurgerIs$(int arg0) {
        price.setValue(10,0);

        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        burger = StorageAccess.instance().getItem(burger_ref);

        menu_burger.setItem(burger_ref);

        menu_burger.setPrice(price);
    }


    @When("The user selects change price and enters ${int}")
    public void theUserSelectsChangePriceAndEnters$(int arg0) {
        price.setValue(12, 0);
        menu_burger.setPrice(price);
    }

    @Then("The current price of a burger is now the new price ${int}")
    public void theCurrentPriceOfABurgerIsNowTheNewPrice$(int arg0) {
        Currency actual = new Currency();
        actual.setValue(12,0);
        assertEquals("$12.00", menu_burger.getPrice().toString());
    }

    @Given("Menu is open and the user needs to check the recipe for a burger")
    public void menuIsOpenAndTheUserNeedsToCheckTheRecipeForABurger() {
        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        burger2 = StorageAccess.instance().getItem(burger_ref);

        menu_burger.setItem(burger_ref);
    }

    @When("A burger is selected")
    public void aBurgerIsSelected() {
        menu_burger.getItem();
        burger2 = StorageAccess.instance().getItem(menu_burger.getItem());
    }

    @Then("The recipe for a burger is displayed")
    public void theRecipeForABurgerIsDisplayed() {
        assertEquals("Put it all in the bag", burger2.getRecipe().getMethod());
    }

    @Given("A new burger is created")
    public void aNewBurgerIsCreated() {
        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        StorageAccess.instance().getItem(burger_ref).setName("Burger");
    }

    @When("Edit menu is selected")
    public void editMenuIsSelected() {
        testMenu.addToMenu(menu_burger);
        StorageAccess.instance().updateMenu(testMenu);
    }


    @Then("New burger now in menu")
    public void newBurgerNowInMenu() {
        burger2 = StorageAccess.instance().getItem(menu_burger.getItem());
        System.out.println(burger2.getName());
        assertEquals("Burger", testMenu.getMenuItems().get(0).getName());
    }

    @Given("A new recipe for an item is created")
    public void aNewRecipeForAnItemIsCreated() {
        Item_Ref burger_ref = new Item_Ref();
        burger_ref.setUUID(8782518176451284363l, -6654882082024982124l);
        burger = StorageAccess.instance().getItem(burger_ref);

        menu_burger.setItem(burger_ref);
        testRecipe = new Recipe("Test that setting recipes works");
    }

    @When("Add recipe is selected")
    public void addRecipeIsSelected() {
        burger3 = StorageAccess.instance().getItem(menu_burger.getItem());
        burger3.setRecipe(testRecipe);
    }

    @Then("Recipe is now in the menu")
    public void recipeIsNowInTheMenu() {
        assertEquals("Test that setting recipes works", burger3.getRecipe().getMethod());
    }
}

package seng202.teamsix.data;

/**
 * Class Recipe, holds the recipe as an object with a string attribute called method which is essentially the instructions
 * to making a certain item, such as a burger.
 */
public class Recipe {
    private String method;

    /**
     * Constructor class for Recipe.
     * @param method String containing the instructions for making a certain item.
     */
    public Recipe(String method) {
        this.method = method;
    }
}

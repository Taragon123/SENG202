package seng202.teamsix.data;

/**
 * Class Recipe, holds the recipe as an object with a string attribute called method which is essentially the instructions
 * to making a certain item, such as a burger. This is class because we may add more methods and attributes in future.
 */
public class Recipe {
    private String method;

    /**
     * A getter for the method attribute.
     * @return The recipe
     */
    public String getMethod() {
        return method;
    }

    /**
     * Constructor class for Recipe.
     * @param method String containing the instructions for making a certain item.
     */
    public Recipe(String method) {
        this.method = method;
    }
}

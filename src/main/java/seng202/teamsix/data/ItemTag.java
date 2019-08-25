package seng202.teamsix.data;

public class ItemTag extends UUID_Entity{
    /**
     * Class for tags
     */
    //Members
    private String name;
    private Boolean is_dominant;

    //Constructors
    public ItemTag(String name, Boolean is_dominant) {
        super();
        this.name = name;
        this.is_dominant = is_dominant;
    }

    //Functions
    /**
     * Gets the value of is_dominant.
     * @return Boolean true if tag is dominant
     */
    public Boolean getIsDominant() {
        return is_dominant;
    }

    /**
     * Gets the value of name.
     * @return String name of tag
     */
    public String getName() {
        return name;
    }
}

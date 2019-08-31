package seng202.teamsix.data;

import javax.xml.bind.annotation.*;

/**
 * Class for tags
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ItemTag extends ItemTag_Ref{
    //Members
    @XmlElement
    private String name;
    @XmlElement
    private Boolean is_dominant;

    //Constructors
    public ItemTag() {}
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

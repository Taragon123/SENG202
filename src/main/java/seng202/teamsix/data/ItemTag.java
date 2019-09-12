/**
 * Name: ItemTag.java
 * Authors: George Stephenson
 * Date: 19/08/2019
 */

package seng202.teamsix.data;

import javax.xml.bind.annotation.*;

/**
 * Class for tags
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ItemTag extends ItemTag_Ref{
    // Members
    @XmlElement
    private String name;
    @XmlElement
    private Boolean is_dominant;

    // Constructors
    public ItemTag() {}
    public ItemTag(String name, Boolean is_dominant) {
        super();
        this.name = name;
        this.is_dominant = is_dominant;
    }

    // Methods
    /**
     * Gets the value of is_dominant.
     * Dominance determines if parent item also has tag.
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

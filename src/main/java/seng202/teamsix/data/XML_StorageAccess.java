package seng202.teamsix.data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class XML_StorageAccess extends StorageAccess{
    // Filename constants
    private static final String item_filename = "item_list.xml";
    private static final String item_tag_filename = "tag_list.xml";
    private static final String menu_filename = "menu_list.xml";

    // Members
    private String xml_dir;


    /**
     * @param source_dir source directory where xml files should be saved and loaded
     * @throws IOException raised if cannot create xml directory if does not exist
     */
    public XML_StorageAccess(String source_dir) throws IOException {
        xml_dir = source_dir;

        // Create folder if does not exist
        File dir = new File(xml_dir);
        if (!dir.exists()) {
            // If cannot create folder create exception
            if(!dir.mkdir()) {
                throw new IOException(String.format("Cannot create xml directory '%s'", xml_dir));
            }
        }
    }

    // Methods

    @Override
    public Item getItem(UUID_Entity ref) {
        return null;
    }

    @Override
    public ItemTag getItemTag(UUID_Entity ref) {
        return null;
    }

    @Override
    public Menu getMenu(UUID_Entity ref) {
        return null;
    }

    @Override
    public void updateItem(Item item) {

    }

    @Override
    public void updateItemTag(ItemTag tag) {

    }

    @Override
    public void updateMenu(Menu menu) {

    }
}

package seng202.teamsix.data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;


@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD)
class XML_Cache {
    public HashMap<UUID, Item> item_map = new HashMap<UUID, Item>();
    public HashMap<UUID, ItemTag> item_tag_map = new HashMap<UUID, ItemTag>();
    public HashMap<UUID, Menu> menu_map = new HashMap<UUID, Menu>();
    public HashMap<UUID, Order> order_map = new HashMap<UUID, Order>();
    public HashMap<UUID, StockInstance> stock_instance_map = new HashMap<UUID, StockInstance>();
}

/**
 * Implementation of StorageAccess that uses XML to save and load data
 */
public class XML_StorageAccess extends StorageAccess{
    // Filename constants
    private static final String data_filename = "data.xml";

    // Members
    private String xml_dir;

    private HashSet<UUID> modified_entity_set;
    private XML_Cache cache = new XML_Cache();

    private JAXBContext  contextCache;


    /**
     * @param source_dir source directory where xml files should be saved and loaded
     * @throws IOException raised if cannot create xml directory if does not exist
     */
    public XML_StorageAccess(String source_dir) throws IOException, JAXBException {
        xml_dir = source_dir;

        // Create folder if does not exist
        File dir = new File(xml_dir);
        if (!dir.exists()) {
            // If cannot create folder create exception
            if(!dir.mkdir()) {
                throw new IOException(String.format("Cannot create xml directory '%s'", xml_dir));
            }
        }

        // Create object contexts for JAXB
        contextCache = JAXBContext.newInstance(XML_Cache.class);

        // Load data
        loadData();
    }

    // Methods
    @Override
    public Item getItem(Item_Ref ref) {
        return cache.item_map.getOrDefault(ref.getUUID(), null);
    }

    @Override
    public ItemTag getItemTag(ItemTag_Ref ref) {
        return cache.item_tag_map.getOrDefault(ref.getUUID(), null);
    }

    @Override
    public Menu getMenu(Menu_Ref ref) {
        return cache.menu_map.getOrDefault(ref.getUUID(), null);
    }

    @Override
    public Order getOrder(Order_Ref ref) {
        return cache.order_map.getOrDefault(ref.getUUID(), null);
    };

    @Override
    public StockInstance getStockInstance(StockInstance_Ref ref) {
        return cache.stock_instance_map.getOrDefault(ref.getUUID(), null);
    }

    @Override
    public void updateItem(Item item) {
        cache.item_map.put(item.getUUID(), item);
    }

    @Override
    public void updateItemTag(ItemTag tag) {
        cache.item_tag_map.put(tag.getUUID(), tag);
    }

    @Override
    public void updateMenu(Menu menu) {
        cache.menu_map.put(menu.getUUID(), menu);
    }

    @Override
    public void updateOrder(Order order) {
        cache.order_map.put(order.getUUID(), order);
    }

    @Override
    public void updateStockInstance(StockInstance stock) {
        cache.stock_instance_map.put(stock.getUUID(), stock);
    }

    /**
     * Loads xml data from file and into cache
     */
    public void loadData() {
        // Load File
        String filename = xml_dir + "/" + data_filename;
        File file = new File(filename);

        // Load Cache to File
        try {
            Unmarshaller u = contextCache.createUnmarshaller();
            cache = (XML_Cache)u.unmarshal(file);
        } catch (JAXBException e) {
            // TODO(Connor): Exit program safely
            System.err.println("Could not load xml cache");
        }
    }

    /**
     * Saves cache to xml data
     */
    public void saveData() {
        // Create File
        String filename = xml_dir + "/" + data_filename;
        File file = new File(filename);

        // Write Cache to File
        try {
            Marshaller m = contextCache.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(cache, file);
        } catch (JAXBException e) {
            System.err.println("Could not save xml cache");
        }
    }

}

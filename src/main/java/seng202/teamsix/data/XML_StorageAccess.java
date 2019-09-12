package seng202.teamsix.data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;


@XmlRootElement(name="data")
@XmlAccessorType(XmlAccessType.FIELD)
class XML_Cache {
    HashMap<Item_Ref, Item> item_map = new HashMap<>();
    HashMap<ItemTag_Ref, ItemTag> item_tag_map = new HashMap<>();
    HashMap<Menu_Ref, Menu> menu_map = new HashMap<>();
    HashMap<Order_Ref, Order> order_map = new HashMap<>();
    HashMap<StockInstance_Ref, StockInstance> stock_instance_map = new HashMap<>();
}

/**
 * Implementation of StorageAccess that uses XML to save and load data
 */
public class XML_StorageAccess extends StorageAccess{
    // Filename constants
    private static final String data_filename = "data.xml";

    // Members
    private String xml_dir;

    private Boolean cache_modified = false;
    private XML_Cache cache = new XML_Cache();

    private JAXBContext  contextCache;


    /**
     * @param source_dir source directory where xml files should be saved and loaded
     * @throws IOException raised if cannot create xml directory if does not exist
     */
    public XML_StorageAccess(String source_dir) throws IOException, JAXBException {

        // Create object contexts for JAXB
        contextCache = JAXBContext.newInstance(XML_Cache.class);

        initFileStructure(source_dir);

        // Load data
        loadData();
    }

    // Methods
    @Override
    public Item getItem(Item_Ref ref) {
        return cache.item_map.getOrDefault(ref, null);
    }

    @Override
    public ItemTag getItemTag(ItemTag_Ref ref) {
        return cache.item_tag_map.getOrDefault(ref, null);
    }

    @Override
    public Menu getMenu(Menu_Ref ref) {
        return cache.menu_map.getOrDefault(ref, null);
    }

    @Override
    public Order getOrder(Order_Ref ref) {
        return cache.order_map.getOrDefault(ref, null);
    };

    @Override
    public StockInstance getStockInstance(StockInstance_Ref ref) {
        return cache.stock_instance_map.getOrDefault(ref, null);
    }

    @Override
    public void updateItem(Item item) {
        cache.item_map.put(item.copyRef(), item);
        cache_modified = true;
    }

    @Override
    public void updateItemTag(ItemTag tag) {
        cache.item_tag_map.put(tag.copyRef(), tag);
        cache_modified = true;
    }

    @Override
    public void updateMenu(Menu menu) {
        cache.menu_map.put(menu.copyRef(), menu);
        cache_modified = true;
    }

    @Override
    public void updateOrder(Order order) {
        cache.order_map.put(order.copyRef(), order);
        cache_modified = true;
    }

    @Override
    public void updateStockInstance(StockInstance stock) {
        cache.stock_instance_map.put(stock.copyRef(), stock);
        cache_modified = true;
    }


    @Override
    public Set<Item_Ref> getAllItems() {
        return cache.item_map.keySet();
    }

    @Override
    public Set<ItemTag_Ref> getAllItemTags() {
        return cache.item_tag_map.keySet();
    }

    @Override
    public Set<Menu_Ref> getAllMenus() {
        return cache.menu_map.keySet();
    }

    @Override
    public Set<Order_Ref> getAllOrders() {
        return cache.order_map.keySet();
    }

    @Override
    public Set<StockInstance_Ref> getAllStockInstances() {
        return cache.stock_instance_map.keySet();
    }

    // Generates file if it does not exist
    private void initFileStructure(String source_dir) throws IOException {
        xml_dir = source_dir;

        // Create folder if does not exist
        File dir = new File(xml_dir);
        if (!dir.exists()) {
            // If cannot create folder create exception
            if(!dir.mkdir()) {
                throw new IOException(String.format("Cannot create xml directory '%s'", xml_dir));
            }
        }

        // Create file if does not exist
        String filename = xml_dir + "/" + data_filename;
        File file = new File(filename);

        if(!file.exists()) {
            cache_modified = true;
            saveData();
        }
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
        if(cache_modified) {
            cache_modified = false;

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

}

package seng202.teamsix.managers;

import java.util.ArrayList;
import seng202.teamsix.data.StockInstance;

/**
 * Class StockManager, used to manage the current stock inventory
 * Author: Andrew Clifford
 * Date Created: 31/08/19
 * Last Modified: 31/08/19
 */
public class StockManager {

    private ArrayList<StockInstance> stock_list;

    /* Constructors below, unsure of how they are going to be instantiated atm */

    /**
     * Constructor for the StockManager class, used if there is no current list of stock items
     */
    public StockManager() {
        stock_list = new ArrayList<>();
    }

    /**
     * Constructor for the StockManager class, used if there is already a current list of stock items
     * @param stock_list an ArrayList of the current StockInstances
     */
    public StockManager(ArrayList<StockInstance> stock_list) {
        this.stock_list = stock_list;
    }

    /**
     * Adds the given StockInstance to the ArrayList of StockInstances
     * @param instance StockInstance to be added to the list of StockInstances
     */
    public void addStockInstance(StockInstance instance) {
        if (!stock_list.contains(instance)) {
            stock_list.add(instance);
        } else {
            System.err.println("Can't add same StockInstance to the list of stock_items!");
        }
    }

    public void removeStockInstance(StockInstance instance) {
        if (stock_list.contains(instance)) {
            stock_list.remove(instance);
        } else {
            System.err.println("The specified StockInstance is not in the list of stock_items!");
        }
    }
}

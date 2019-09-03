package seng202.teamsix.managers;

import java.util.ArrayList;
import seng202.teamsix.data.StockInstance;

/**
 * Class StockManager, used to manage the current stock inventory
 * Author: Andrew Clifford
 * Date Created: 31/08/19
 * Last Modified: 01/09/19
 */
public class StockManager {

    private ArrayList<StockInstance> stock_list;

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
     * Adds the given StockInstance to the ArrayList stock_items
     * @param instance StockInstance to be added to stock_items
     * @return boolean, true if the StockInstance was successfully added to stock_items, false otherwise
     */
    public boolean addStock(StockInstance instance) {
        boolean bool;
        if (!stock_list.contains(instance)) {
            stock_list.add(instance);
            return true;
        } else {
            System.err.println("Can't add same StockInstance to the list of stock_items!");
            return false;
        }
    }

    /**
     * Removes the given StockInstance from the ArrayList stock_items
     * @param instance StockInstance to be removed from stock_items
     * @return boolean, true if the StockInstance was successfully removed from stock_items, false otherwise
     */
    public boolean removeStock(StockInstance instance) {
        if (stock_list.contains(instance)) {
            stock_list.remove(instance);
            return true;
        } else {
            System.err.println("The specified StockInstance is not in the list of stock_items!");
            return false;
        }
    }

    public ArrayList<StockInstance> getStockList() { return stock_list; }
}

/**
 * Name: OrderItem.java
 * Authors: Connor Macdonald
 * Date: 30/08/2019
 */

package seng202.teamsix.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Name: OrderItem.java
 * Date: August - September, 2019
 *
 * Defines an ordered item for an order, an order item can contain many dependent order items that are required for the item
 *
 * Authors: Connor Macdonald, Hamesh Ravji
 */


@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class OrderItem {
    @XmlElement
    private Item_Ref item = null;
    @XmlElement
    private ArrayList<OrderItem> dependants = new ArrayList<OrderItem>();
    @XmlElement
    private int quantity = 0;

    private Currency price = new Currency();

    public Currency getPrice() {
        return price;
    }

    public void setPrice(Currency newPrice) {
        price = newPrice;
    }

    public Item_Ref getItem() {
        return this.item;
    }

    public void setItem(Item_Ref item) {
        // TODO(Connor): Add check that item exists
        this.item = item;
        // By default, this sets the price of the OrderItem to the markup price stated as part of the Item object.
        //this.price = ((Item)item).getMarkupPrice();
    }

    public ArrayList<OrderItem> getDependants() {
        return this.dependants;
    }

    public void addDependant(OrderItem order_item) {
        this.dependants.add(order_item);

    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    /**
     * Adds an Item to the Order, given an Item_Ref item_ref, int qty, and ItemTag_Ref default tag.
     *
     * @param item_ref Refers to the Item of which we want to add to the order.
     * @param qty      The number of items we want to add too the order.
     */
    public void addToOrder(Item_Ref item_ref, int qty, Currency new_item_price, int recurse_depth) {
        boolean is_added = false;
        Item item = StorageAccess.instance().getItem(item_ref);
        for (OrderItem orderItem : dependants) {
            if (orderItem.getItem() == item_ref) {
                orderItem.quantity += qty;
                is_added = true;
            }
        }
        if (!is_added) {
            OrderItem parent = new OrderItem();
            parent.setItem(item_ref);
            parent.setQuantity(qty);
            this.dependants.add(parent);
            if (new_item_price != null) {
                Currency temp_price = parent.getPrice();
                temp_price.addCash(new_item_price);
                parent.setPrice(temp_price);
            }

            if (item instanceof CompositeItem) {
                for (Item_Ref child_ref : ((CompositeItem) item).getItems()) {
                    parent.addToOrder(child_ref, 1, new_item_price, recurse_depth + 1);
                }
            } else if (item instanceof VariantItem) {
                parent.addToOrder(((VariantItem) item).getVariants().get(0), 1, null, recurse_depth + 1);
            }
        }
        if (recurse_depth == 0) {
            for (int i = 0; i < qty; i++) {
                if (new_item_price != null) {
                    price.addCash(new_item_price);
                } else {
                    price.addCash(item.getMarkupPrice());
                }
            }
        }
    }


    /**
     * Removes an Item from the Order, given an Item_Ref item_ref, and an int qty.
     *
     * @param item_ref Refers to the Item which we want to remove from the Order.
     * @param qty      The number of items we want to remove from the order.
     * @return True if items are removed (even if qty > already in cart), false if they didn't exist in the first place.
     */
    public boolean removeFromOrder(Item_Ref item_ref, int qty, Currency price_of_item_to_remove) {
        boolean is_removed = false;
        // check the list of dependants to see if the item is in the list (at the top level)
        int index = 0;
        while (index < dependants.size() && !is_removed) {
            OrderItem order_item = dependants.get(index);

            if (order_item.getItem().equals(item_ref)) {
                // match found, check if the quantity in the list of dependants is larger than the qty passed through.
                if (order_item.getQuantity() > qty) {
                    order_item.setQuantity(order_item.getQuantity() - qty);
                    is_removed = true;
                } else if (order_item.getQuantity() == qty) {
                    dependants.remove(order_item);
                    is_removed = true;

                }
            }
            index++;
        }
        if (is_removed) {
            for (int i = 0; i < qty; i++) {
                if (price_of_item_to_remove != null) {
                    price.subCash(price_of_item_to_remove);
                } else {
                    Item item = StorageAccess.instance().getItem(item_ref);
                    price.subCash(item.getMarkupPrice());
                }
            }
            return true;
        } else {
            System.out.println("Something is definitely wrong here");
            return false;
        }
    }


    /*
    public void setTagWithoutCheck(ItemTag_Ref tagRef) {
        Item item = StockInstance.getItem(this.getItem());
        if (item instanceof VariantItem) {
            Item_Ref variant_with_tag = ((VariantItem) item).getVariantWithTag(tagRef);
        }
    }
     */

    public String getOrderTreeRepr(int current_depth) {
        Item item = StorageAccess.instance().getItem(getItem());
        String order_name = "(Empty)";
        if (item != null) {
            order_name = item.getName();
        }

        String spacer = String.join("", Collections.nCopies(current_depth, "|--"));
        String line = spacer + "+ " + order_name + "\n";

        StringBuilder output = new StringBuilder();
        output.append(line);
        for (OrderItem dependant : getDependants()) {
            output.append(dependant.getOrderTreeRepr(current_depth + 1));
        }

        if (current_depth > 0 && getDependants().size() > 0) {
            output.append("|" + String.join("", Collections.nCopies(Math.max(current_depth - 1, 0), "--|")) + "\n");
        }

        return output.toString();
    }

    @Override
    public String toString() {
        Item item = StorageAccess.instance().getItem(getItem());
        if (item != null) {
            return item.getName() + "x" + quantity;
        }
        return "(NULL Item)";
    }

    /**
     * This method returns a string representation of the everything below the OrderItem in the hierarchy tree, where dashed lines represent the items inside the order.
     *
     * @param current_depth Pass through 0 initially, used to determine how much we need to indent the line.
     * @return A string containing all the dependants and what makes up those dependants in a hierarchical form.
     */
    public String getCleanOrderRepresentation(int current_depth, boolean wants_price) {
        Item item = StorageAccess.instance().getItem(getItem());
        String order_name;
        String line = "";
        if (item != null) {
            order_name = item.getName();
            String spacer = String.join("", Collections.nCopies(current_depth - 1, "  "));
            if (current_depth == 1) {
                line = spacer + this.getQuantity() + " x " + order_name;
                if (wants_price) {
                    line += " @ "+this.getPrice()+" each";
                }
                line += "\n";
            } else {
                line = spacer + "  - " + order_name;
                if ((item instanceof VariantItem) || (item instanceof CompositeItem)) {
                    line += "\n";
                }
            }

        }
        StringBuilder output = new StringBuilder();
        output.append(line);
        for (OrderItem dependant : getDependants()) {
            output.append(dependant.getCleanOrderRepresentation(current_depth + 1, wants_price));
        }
        if (!(item instanceof VariantItem) && !(item instanceof CompositeItem)) {
            output.append("\n");
        }

        return output.toString();
    }
}

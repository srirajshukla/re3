package orderbook;

import java.util.HashMap;
import java.util.UUID;

public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    PriceNode<Integer, BuyOrder> bestBuy;
    PriceNode<Integer, SellOrder> bestSell;

    HashMap<UUID, Order> orderIdToOrder;

    public OrderBook() {    // Default constructor
        buyTree = new OrderTree<>();
        sellTree = new OrderTree<>();
        bestBuy = buyTree.treeMaximum(buyTree.root);
        bestSell = sellTree.treeMinimum(sellTree.root);
        orderIdToOrder = new HashMap<>();
    }

    public void addOrder(BuyOrder order){
        int key = (int)(order.price * 100);

        PriceNode<Integer, BuyOrder> node = buyTree.search(key);
        if (node==null)
                node = buyTree.insert(key);

        node.orders.add(order);

        if(bestBuy.key == null || key > bestBuy.key){
            bestBuy = node;
        }
    }

    public void addOrder(SellOrder order){
        int key = (int)(order.price * 100);

        PriceNode<Integer, SellOrder> node = sellTree.search(key);
        if (node==null)
            node = sellTree.insert(key);

        node.orders.add(order);

        if(bestSell.key == null || key < bestSell.key){
            bestSell = node;
        }
    }

    public void deleteOrder(UUID id){
        Order order = orderIdToOrder.get(id);
        int key = (int)(order.price * 100);

        if (order.type == 0){
            PriceNode<Integer , BuyOrder> node = buyTree.search(key);
            node.orders.remove(id);
        }
        else{
            PriceNode<Integer , SellOrder> node = sellTree.search(key);
            node.orders.remove(id);
        }
    }
    
    public void updateOrderVolume(UUID id, int new_vol){
        Order order = orderIdToOrder.get(id);

        order.volume = new_vol;    // Currently not being sent to tail of the linked list.Need to verify with mentors
    }

    public void updateOrderPrice(UUID id, double new_price){
        Order order = orderIdToOrder.get(id);

        deleteOrder(id);
        order.price = new_price;

        if (order.type == 0){
            if (order instanceof BuyOrder) {
                BuyOrder buyorder = (BuyOrder) order;
                addOrder(buyorder);
            }
        }
        else{
            if (order instanceof SellOrder) {
                SellOrder sellorder = (SellOrder) order;
                addOrder(sellorder);
            }
        }
    }

    public BuyOrder[] getBuyOrders(){
        
        return null;
    }

    public SellOrder[] getSellOrders(){
        return null;
    }

    @Override
    public String toString() {
        return "OrderBook {" +
                "\n\tbuyTree=" + buyTree.toString() +
                "\n\tsellTree=" + sellTree.toString() +
                "\n\tbestBuy=" + bestBuy.toString() +
                "\n\tbestSell=" + bestSell.toString() +
                '}';
    }
}

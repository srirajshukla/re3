package orderbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;

public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    PriceNode<Integer, BuyOrder> bestBuy;
    PriceNode<Integer, SellOrder> bestSell;

    HashMap<UUID, ListNode> orderIdToNode;

    public OrderBook() {    // Default constructor
        buyTree = new OrderTree<>();
        sellTree = new OrderTree<>();
        bestBuy = buyTree.treeMaximum(buyTree.root);
        bestSell = sellTree.treeMinimum(sellTree.root);
        orderIdToNode = new HashMap<>();
    }

    public void addOrder(BuyOrder order){
        int key = (int)(order.price * 100);

        PriceNode<Integer, BuyOrder> tree_node = buyTree.search(key);
        if (tree_node == null)
                tree_node = buyTree.insert(key);

        ListNode list_node = tree_node.orders.add(order);
        orderIdToNode.put(order.id, list_node);

        if(bestBuy.key == null || key > bestBuy.key){
            bestBuy = tree_node;
        }

        Order ord = (Order) order;
        orderIdToOrder.put(order.id,ord);
    }

    public void addOrder(SellOrder order){
        int key = (int)(order.price * 100);

        PriceNode<Integer, SellOrder> tree_node = sellTree.search(key);
        if (tree_node == null)
                tree_node = sellTree.insert(key);

        ListNode list_node = tree_node.orders.add(order);
        orderIdToNode.put(order.id, list_node);

        if(bestSell.key == null || key > bestSell.key){
            bestSell = tree_node;
        }

        Order ord = (Order) order;
        orderIdToOrder.put(order.id,ord);
    }

    public void deleteOrder(UUID id){
        ListNode node = orderIdToNode.get(id);
        if(node.prev == null){
            // not sure how to do this in O(1) time
        }
        else{
            node.prev.next = node.next;
        }

        orderIdToOrder.remove(id);
    }
    
    public void updateOrderVolume(UUID id, int new_vol){
        Order order = orderIdToNode.get(id).order;

        order.volume = new_vol;    // Currently not being sent to tail of the linked list.Need to verify with mentors
    }

    public void updateOrderPrice(UUID id, double new_price){
        Order order = orderIdToNode.get(id).order;

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

    public List<BuyOrder> getBuyOrders(){
        List<BuyOrder> lst = new ArrayList<>();

        for(Order order : orderIdToOrder.values()){
            if (order.type == 0){
                if (order instanceof BuyOrder) {
                    BuyOrder buyorder = (BuyOrder) order;
                    lst.add(buyorder);
                }
            }
        }

        return lst;
    }

    public List<SellOrder> getSellOrders(){
        List<SellOrder> lst = new ArrayList<>();

        for(Order order : orderIdToOrder.values()){
            if (order.type == 1){
                if (order instanceof SellOrder) {
                    SellOrder sellorder = (SellOrder) order;
                    lst.add(sellorder);
                }
            }
        }

        return lst;
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

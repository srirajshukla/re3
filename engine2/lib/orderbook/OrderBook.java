package orderbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;

public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    public PriceNode<Integer, BuyOrder> bestBuy;
    public PriceNode<Integer, SellOrder> bestSell;

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
    }

    public void addOrder(SellOrder order){
        int key = (int)(order.price * 100);

        PriceNode<Integer, SellOrder> tree_node = sellTree.search(key);
        if (tree_node == null)
                tree_node = sellTree.insert(key);

        ListNode list_node = tree_node.orders.add(order);
        orderIdToNode.put(order.id, list_node);

        if(bestSell.key == null || key < bestSell.key){
            bestSell = tree_node;
        }
    }

    public void deleteOrder(UUID id){
        ListNode node = orderIdToNode.get(id);

        Order order = node.order;
        int price = (int)(order.price * 100);

        if(order.type == 0){
            if(node.prev != null && node.next != null){
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            // if it is the only remaining node at a price level then delete the priceNode from the tree
            else if(node.prev == null && node.next == null){
                buyTree.remove(buyTree.search(price));
                // updating the bestBuy pointer
                if(price == bestBuy.key)
                    bestBuy = buyTree.treeMaximum(buyTree.root);
            }
            // if it is either the head or the tail of the list
            else{
                PriceNode<Integer, BuyOrder> tree_node = buyTree.search(price);
                if(node.prev == null && node.next != null){
                    tree_node.orders.head = node.next;
                    node.next.prev = null;
                }
                else if(node.prev != null && node.next == null){
                    tree_node.orders.tail = node.prev;
                    node.prev.next = null;
                }
            }
        }
        else{
            if(node.prev != null && node.next!= null){
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            // if it is the only remaining node at a price level then delete the priceNode from the tree
            else if(node.prev == null && node.next == null){
                sellTree.remove(sellTree.search(price));
                // updating the bestSell pointer
                if(price == bestSell.key)
                    bestSell = sellTree.treeMinimum(sellTree.root);
            }
            // if it is either the head or the tail of the list
            else{
                PriceNode<Integer, SellOrder> tree_node = sellTree.search(price);
                if(node.prev == null && node.next != null){
                    tree_node.orders.head = node.next;
                    node.next.prev = null;
                }
                else if(node.prev != null && node.next == null){
                    tree_node.orders.tail = node.prev;
                    node.prev.next = null;
                }
            }
        }

        orderIdToNode.remove(id);
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

        for(ListNode node : orderIdToNode.values()){
            Order order = node.order;
            if(order.type == 0){
                if(order instanceof BuyOrder){
                    BuyOrder buyorder = (BuyOrder) order;
                    lst.add(buyorder);
                }
            }
        }

        return lst;
    }

    public List<SellOrder> getSellOrders(){
        List<SellOrder> lst = new ArrayList<>();

        for(ListNode node : orderIdToNode.values()){
            Order order = node.order;
            if (order.type == 1){
                if (order instanceof SellOrder) {
                    SellOrder sellorder = (SellOrder) order;
                    lst.add(sellorder);
                }
            }
        }

        return lst;
    }

    public BuyOrder getBestBuy(){
        if (bestBuy.orders.head.order instanceof BuyOrder) {
            BuyOrder buyorder = (BuyOrder) bestBuy.orders.head.order;
        return buyorder;
        }
        return null;
    }

    public SellOrder getBestSell(){
        if (bestSell.orders.head.order instanceof SellOrder) {
            SellOrder sellorder = (SellOrder) bestSell.orders.head.order;
        return sellorder;
        }
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

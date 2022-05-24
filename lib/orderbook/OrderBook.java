package orderbook;

import java.util.HashMap;

public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    PriceNode<Integer, BuyOrder> bestBuy;
    PriceNode<Integer, SellOrder> bestSell;

    HashMap<Integer, DoubleLinkedList> buyPricetoList;
    HashMap<Integer, DoubleLinkedList> sellPricetoList;

    public OrderBook() {    // Default constructor
        buyTree = new OrderTree<>();
        sellTree = new OrderTree<>();
        bestBuy = buyTree.treeMaximum(buyTree.root);
        bestSell = sellTree.treeMinimum(sellTree.root);
        buyPricetoList = new HashMap<>();
        sellPricetoList = new HashMap<>();
    }

    public void addOrder(BuyOrder order){
        int key = (int)(order.price * 100);

        if(buyPricetoList.containsKey(key)){
            DoubleLinkedList list = buyPricetoList.get(key);
            list.add(order);
        }
        else{
            PriceNode<Integer, BuyOrder> node = buyTree.insert(key);
            node.orders.add(order);
            buyPricetoList.put(key, node.orders);
        }

        // PriceNode<Integer, BuyOrder> node = buyTree.search(key);
        // if (node==null)
        //         node = buyTree.insert(key);

        // node.orders.add(order);

        bestBuy = buyTree.treeMaximum(buyTree.root);
    }

    public void addOrder(SellOrder order){
        int key = (int)(order.price * 100);

        if(sellPricetoList.containsKey(key)){
            DoubleLinkedList list = sellPricetoList.get(key);
            list.add(order);
        }
        else{
            PriceNode<Integer, SellOrder> node = sellTree.insert(key);
            node.orders.add(order);
            sellPricetoList.put(key, node.orders);
        }

        // PriceNode<Integer, SellOrder> node = sellTree.search(key);
        // if (node==null)
        //     node = sellTree.insert(key);

        // node.orders.add(order);

        bestSell = sellTree.treeMinimum(sellTree.root);
    }

    public void deleteOrder(){

    }
    
    public void updateOrderVolume(){

    }

    public void updateOrderPrice(){

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

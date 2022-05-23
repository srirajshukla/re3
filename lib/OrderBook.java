public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    PriceNode<Integer, BuyOrder> bestBuy;
    PriceNode<Integer, SellOrder> bestSell;

    public OrderBook() {    // Default constructor
        buyTree = new OrderTree<>();
        sellTree = new OrderTree<>();
        bestBuy = buyTree.treeMaximum(buyTree.root);
        bestSell = sellTree.treeMinimum(sellTree.root);
    }

    public void addOrder(BuyOrder order){
        int key = (int)(order.price * 100);
        PriceNode<Integer, BuyOrder> node = buyTree.insert(key);
        node.orders.add(order);

        bestBuy = buyTree.treeMaximum(buyTree.root);
    }

    public void addOrder(SellOrder order){
        int key = (int)(order.price * 100);
        PriceNode<Integer, SellOrder> node = sellTree.insert(key);
        node.orders.add(order);

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
}

public class OrderBook {
    RBTree<BuyOrder> buyTree;
    RBTree<SellOrder> sellTree;

    public OrderBook() {    // Default constructor
        buyTree = new RBTree<BuyOrder>();
        sellTree = new RBTree<SellOrder>();
    }

    public void addOrder(BuyOrder order){

    }

    public void addOrder(SellOrder order){

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

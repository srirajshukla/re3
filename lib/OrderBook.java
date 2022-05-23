public class OrderBook {
    OrderTree<BuyOrder> buyTree;
    OrderTree<SellOrder> sellTree;

    public OrderBook() {    // Default constructor
        buyTree = new OrderTree<>();
        sellTree = new OrderTree<>();
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

import Utils.DataGenerator;
import orderbook.BuyOrder;
import orderbook.OrderBook;
import orderbook.SellOrder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class App {
    public static void main(String[] args) throws Exception {
       OrderBook ob = new OrderBook();

        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);

        ob.addOrder(new BuyOrder(UUID.randomUUID(), 1, 10, 23.02, "GOOG", new Date()));
        ob.addOrder(new BuyOrder(UUID.randomUUID(), 2,12, 22.02, "GOOG", new Date()));
        ob.addOrder(new SellOrder(UUID.randomUUID(), 3,10, 23.02, "GOOG", new Date()));
        ob.addOrder(new BuyOrder(id2, 2, 1, 27.02, "GOOG", new Date()));
        ob.addOrder(new BuyOrder(id1, 17, 4, 23.02, "GOOG", new Date()));
        ob.addOrder(new SellOrder(id3, 11, 2, 22.02, "GOOG", new Date()));

        System.out.println(ob);

        ob.deleteOrder(id1);
        ob.updateOrderVolume(id2, 5);
        ob.updateOrderPrice(id3, 23.02);

        System.out.println(ob);

       DataGenerator dg = new DataGenerator();
       dg.writeOrdersToFile("GOOG", 1000, 20, 0.25);

        List<BuyOrder> orders = dg.generateBuyOrder("GOOG", 100, 20, 0.10);
        for (BuyOrder order : orders){
            ob.addOrder(order);
        }

        System.out.println(ob);

        List<SellOrder> lst = ob.getSellOrders();
        System.out.println(lst);
    }
}

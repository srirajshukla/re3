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

       ob.addOrder(new BuyOrder(UUID.randomUUID(), 10, 23.02, "GOOG", new Date()));
       ob.addOrder(new BuyOrder(UUID.randomUUID(), 12, 22.02, "GOOG", new Date()));
       ob.addOrder(new SellOrder(UUID.randomUUID(), 10, 23.02, "GOOG", new Date()));
       ob.addOrder(new BuyOrder(UUID.randomUUID(), 2, 27.02, "GOOG", new Date()));
       ob.addOrder(new BuyOrder(UUID.randomUUID(), 17, 23.02, "GOOG", new Date()));
       ob.addOrder(new SellOrder(UUID.randomUUID(), 11, 22.02, "GOOG", new Date()));


       DataGenerator dg = new DataGenerator();
       dg.writeOrdersToFile("GOOG", 1000, 20, 0.25);

        List<BuyOrder> orders = dg.generateBuyOrder("GOOG", 100, 20, 0.10);
        for (BuyOrder order : orders){
            ob.addOrder(order);
        }

        System.out.println(ob);
    }
}

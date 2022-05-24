import orderbook.BuyOrder;
import orderbook.OrderBook;
import orderbook.SellOrder;

import java.util.Date;
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


        System.out.println(ob);
    }
}

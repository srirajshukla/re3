package Utils;

import orderbook.BuyOrder;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DataGenerator {
    // todo: choose a better rng distribution function
    public List<BuyOrder> generateBuyOrder(String symbol, int orderQty, double meanPrice, double variance){
        List<BuyOrder> orders = new ArrayList<>();

        double minBuyPrice = meanPrice - meanPrice*variance;
        double maxBuyPrice = meanPrice + meanPrice*variance;

        int minOrderSize = 10;
        int maxOrderSize = 30;
        Random r1 = new Random();

        for (int i=0; i<orderQty; i++){
            int volume = r1.nextInt(maxOrderSize-minOrderSize) + minOrderSize;
            double price = r1.nextDouble() * (maxBuyPrice - minBuyPrice) + minBuyPrice;
            price = price * 100;
            price = Math.ceil(price);
            // round about 70% of the time, price is a multiple of 5
            if (r1.nextDouble() > 0.3){
                price += (5 - (price % 5))%5;
            }

            int userId = r1.nextInt(10) + 1;

            price = price / 100;
            BuyOrder order = new BuyOrder(UUID.randomUUID(), userId, volume, price, symbol, new Date());
            orders.add(order);
        }

        return orders;
    }

    public void writeOrdersToFile(String symbol, int orderQty, double meanPrice, double variance) throws IOException {
        List<BuyOrder> orders = generateBuyOrder(symbol, orderQty, meanPrice, variance);

        String filename = symbol+ Math.random() + ".orders";
        PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);

        for (BuyOrder order : orders){
            writer.println(order.toString());
        }

        writer.close();
    }
}

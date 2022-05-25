package matchingengine;

import orderbook.*;

import java.util.ArrayList;

public class MatchingEngine {

    /**
     * Match an incoming buy order with the best sell order in the order book. 
     * If the order is not matched, directly add it to the orderbook, otherwise
     * if there is a full/partial match, create a trade and update the orderbook.
     * 
     * @param order - incoming buy order
     * @param obook - The current orderbook
     * 
     * @return ArrayList<Trade> containing all the trades generated by the incoming order
     */
    public ArrayList<Trade> matchBuyOrder(BuyOrder order,OrderBook obook){
        SellOrder best_sell_order =  obook.getBestSell();
        ArrayList<Trade> tradesGenerated = new ArrayList<>();

        // While volume is greater than 0, then search for match
        while(order.volume > 0){
            // If the best sell price is more than the buy order price, then add order to buytree (no match found)
            if (order.price < best_sell_order.price){
                obook.addOrder(order);
                break;  // Break out of while
            }
            //Else Match found
            else{
                // If buy order volume is less than best sell order volume, then order completed
                if (order.volume < best_sell_order.volume){
                    // Create trade
                    Trade trade = new Trade(order.userId, best_sell_order.userId, order.symbol, order.volume, order.price);
                    tradesGenerated.add(trade);
                    
                    // Update order volume of best sell order as we've consumed our order volume
                    best_sell_order.volume -= order.volume;
                    order.volume = 0;
                }
                // Else Order is partially complete and best sell order is deleted
                else{ 
                    // Create trade
                    Trade trade = new Trade(order.userId, best_sell_order.userId, order.symbol, best_sell_order.volume, order.price);
                    tradesGenerated.add(trade);

                    // Update order volume of buy order, as we've consumed our current best sell order
                    order.volume -= best_sell_order.volume;
                    obook.deleteOrder(best_sell_order.id);
                    // reassign the best sell order after deletion the last best node
                    best_sell_order =  obook.getBestSell();
                }
            }
        }

        return tradesGenerated;
    }


    /**
     * Match an incoming sell order with the best buy order in the order book. 
     * If the order is not matched, directly add it to the orderbook, otherwise
     * if there is a full/partial match, create a trade and update the orderbook.
     * 
     * @param order - incoming sell order
     * @param obook - The current orderbook
     * 
     * @return ArrayList<Trade> containing all the trades generated by the incoming order
     */
    public void matchsellOrder(SellOrder order,OrderBook obook){
        BuyOrder best_buy_order = obook.getBestBuy();
        ArrayList<Trade> tradesGenerated = new ArrayList<>();
        
        // While volume is greater than 0, then search for match
        while(order.volume > 0){
            // If the best buy price is less than the sell order price, then add order to selltree (no match found)
            if (order.price > best_buy_order.price){
                obook.addOrder(order);
                break;
            }
            //Else Match found
            else{
                // If sell order volume is less than best buy order volume, then order completed
                if (order.volume < best_buy_order.volume){
                    // Create trade
                    Trade trade = new Trade(order.userId, best_buy_order.userId, order.symbol, order.volume, order.price);
                    tradesGenerated.add(trade);

                    // Update order volume of best buy order as we've consumed our order volume
                    best_buy_order.volume -= order.volume;
                    order.volume = 0;
                }
                // Else Order is partially complete and best buy order is deleted
                else{
                    // Create trade
                    Trade trade = new Trade(order.userId, best_buy_order.userId, order.symbol, best_buy_order.volume, order.price);
                    tradesGenerated.add(trade);

                    // Update order volume of sell order, as we've consumed our current best buy order
                    order.volume -= best_buy_order.volume;
                    obook.deleteOrder(best_buy_order.id);

                    // reassign the best sell order after deletion the last best node
                    best_buy_order = obook.getBestBuy();

                }
            }
        }
    }
}
package matchingengine;

import orderbook.*;

public class MatchingEngine {

    public void matchbuyOrder(BuyOrder order,OrderBook obook){
        SellOrder best_sell_order =  obook.getBestSell();

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
                    best_sell_order.volume -= order.volume;
                    order.volume = 0;
                }
                // Else Order is partially complete and best sell order is deleted
                else{ 
                    order.volume -= best_sell_order.volume;
                    obook.deleteOrder(best_sell_order.id);
                }
            }
        }
    }


    public void matchsellOrder(SellOrder order,OrderBook obook){
        BuyOrder best_buy_order = obook.getBestBuy();

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
                    best_buy_order.volume -= order.volume;
                    order.volume = 0;
                }
                // Else Order is partially complete and best buy order is deleted
                else{
                    order.volume -= best_buy_order.volume;
                    obook.deleteOrder(best_buy_order.id);
                }
            }
        }
    }
}

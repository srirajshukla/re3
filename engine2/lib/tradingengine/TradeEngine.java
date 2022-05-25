package tradingengine;

import matchingengine.MatchingEngine;
import orderbook.OrderBook;

public class TradeEngine {
    OrderBook obook;
    MatchingEngine me;

    public TradeEngine(OrderBook obook, MatchingEngine me) {
        this.obook = obook;
        this.me = me;
    }
}

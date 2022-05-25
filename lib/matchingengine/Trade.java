package matchingengine;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Trade {
    UUID tradeId;
    int buyerId;
    int sellerId;
    String symbol;
    int volume;
    double price;
    Date timestamp;

    public Trade(){
        this.tradeId = UUID.randomUUID();
        this.timestamp = new Date();
    }
    public Trade(int buyerId, int sellerId, String symbol, int volume, double price) {
        this.tradeId = UUID.randomUUID();
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.symbol = symbol;
        this.volume = volume;
        this.price = price;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", symbol='" + symbol + '\'' +
                ", volume=" + volume +
                ", price=" + price +
                ", timestamp=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(timestamp) +
                '}';
    }
}

import java.util.Date;
import java.util.UUID;

public class SellOrder extends Order implements Comparable<SellOrder> {

    public SellOrder(UUID id, int volume, double price, String symbol, Date timestamp) {
        super();
        this.id = id;
        this.volume = volume;
        this.price = price;
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.type = SELL;
    }

    @Override
    public int compareTo(SellOrder o) {
        if (this.price == o.price){
            return this.timestamp.compareTo(o.timestamp);
        } else if (this.price < o.price){
            return -1;
        } else{
            return 1;
        }
    }
}

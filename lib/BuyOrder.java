import java.util.Date;
import java.util.UUID;

public class BuyOrder extends Order implements Comparable<BuyOrder> {

    public BuyOrder(UUID id, int volume, double price, String symbol, Date timestamp) {
        super();
        this.id = id;
        this.volume = volume;
        this.price = price;
        this.symbol = symbol;
        this.timestamp = timestamp;
        this.type = BUY;
    }

    @Override
    public int compareTo(BuyOrder o) {
        if (this.price == o.price){
            return this.timestamp.compareTo(o.timestamp);
        } else if (this.price < o.price){
            return -1;
        } else{
            return 1;
        }
    }

    
}

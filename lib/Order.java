import java.util.Date;
import java.util.UUID;

public abstract class Order{
    public UUID id;
    public int volume;
    public double price;
    public String symbol;
    public Date timestamp;
    public int type;

    public static final int BUY = 0;
    public static final int SELL = 1;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (type == BUY){
            sb.append("BUY");
        } else{
            sb.append("SELL");
        }
        sb.append(" Order ");
        sb.append(symbol);
        sb.append(" [id=");
        sb.append(id);
        sb.append(", timestamp=");
        sb.append(timestamp);
        sb.append(", volume=");
        sb.append(volume);
        sb.append(", price=");
        sb.append(price);
        sb.append("]");
        return sb.toString();
    }
}

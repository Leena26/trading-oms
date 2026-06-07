package order;
import order.Order;

public class MarketOrder extends Order{
    public MarketOrder(String orderId, String traderId, String symbol, int quantity){
        super(orderId, traderId, symbol, quantity);
    }

    @Override
    public boolean validate(){
        return this.getQuantity() > 0;
    }
}
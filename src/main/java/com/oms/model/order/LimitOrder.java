package order;

public class LimitOrder extends Order{
    private double limitPrice;

    public LimitOrder(String orderId, String traderId, String symbol, int quantity, double limitPrice){
        super(orderId, traderId, symbol, quantity);
        this.limitPrice = limitPrice;
    }

    public double getLimitPrice(){
        return this.limitPrice;
    }

    @Override
    public boolean validate(){
        return this.getQuantity() > 0 && this.limitPrice > 0;
    }
}
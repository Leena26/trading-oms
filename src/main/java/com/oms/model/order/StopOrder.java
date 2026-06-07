package order;

public class StopOrder extends Order{
    private double stopPrice;

    public StopOrder(String orderId, String traderId, String symbol, int quantity, double stopPrice){
        super(orderId, traderId, symbol, quantity);
        this.stopPrice = stopPrice;
    }

    public double getStopPrice(){
        return this.stopPrice;
    }

    public boolean isTriggered(double currentMarketPrice){
        return currentMarketPrice <= this.stopPrice;
    }

    @Override
    public boolean validate(){
        return this.getQuantity() > 0 && this.stopPrice > 0;
    }
}
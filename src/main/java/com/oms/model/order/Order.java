package order;

public abstract class Order{
    private String orderId;
    private String traderId;
    private String symbol;
    private int quantity;
    private OrderStatus status;

    public Order(String orderId, String traderId, String symbol, int quantity){
        this.orderId = orderId;
        this.traderId = traderId;
        this.symbol = symbol;
        this.quantity = quantity;
        this.status= OrderStatus.PENDING;
    }

    public String getOrderId(){
        return this.orderId;
    }

    public String getTraderId(){
        return this.traderId;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public OrderStatus getStatus(){
        return this.status;
    }

    public void setStatus(OrderStatus s){
        this.status = s;
    }

    public void setQuantity(int q){
        this.quantity = q;
    }
    public abstract boolean validate();
}
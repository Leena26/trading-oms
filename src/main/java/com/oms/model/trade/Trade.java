package model.trade;
import java.time.LocalDateTime;

public class Trade{
    private String tradeId;
    private String buyOrderId;
    private String sellOrderId;
    private String symbol;
    private double executedPrice;
    private int quantity;
    private LocalDateTime timestamp;

    public Trade( String tradeId, String buyOrderId, String sellOrderId, String symbol, double executedPrice, int quantity){
        this.tradeId = tradeId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.symbol = symbol;
        this.executedPrice = executedPrice;
        this.quantity = quantity;
        this.timestamp = LocalDateTime.now();
    }

    public String getTradeId(){
        return this.tradeId;
    }
    
    public String getBuyOrderId(){
        return this.buyOrderId;
    }

    public String getSellOrderId(){
        return this.sellOrderId;
    }

    public String getSymbol(){
        return this.symbol;
    }

    public double getExecutedPrice(){
        return this.executedPrice;
    }

    public int getQuantity(){
        return this.quantity;
    }

    
}
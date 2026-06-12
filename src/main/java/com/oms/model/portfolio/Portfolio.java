package model.portfolio;
import model.trade.Trade;

import java.util.Map;
import java.util.HashMap;

public class Portfolio{
    private String traderId;
    private Map<String, Integer> holdings;
    private double cashBalance;
    private double initialCash;

    public Portfolio(String traderId, double initialCash){
        this.traderId = traderId;
        this.cashBalance = initialCash;
        this.initialCash = initialCash;
        this.holdings = new HashMap<String, Integer>();
    }

    public void updateOnTrade(Trade trade, boolean side){
        double cashAmount = trade.getExecutedPrice() * trade.getQuantity();
        if (side){
            this.cashBalance -= cashAmount;
            this.holdings.put(trade.getSymbol(), this.holdings.getOrDefault(trade.getSymbol(), 0) + trade.getQuantity());
        } else{
            this.cashBalance += cashAmount;
            this.holdings.put(trade.getSymbol(), this.holdings.getOrDefault(trade.getSymbol(), 0) - trade.getQuantity());
        }
        if (this.holdings.get(trade.getSymbol()) == 0){
            this.holdings.remove(trade.getSymbol());
        }
    }

    public double getCashBalance(){
        return this.cashBalance;
    }

    public Map<String, Integer> getHoldings(){
        return this.holdings;
    }

    public double getPosition(String symbol){
        return this.holdings.getOrDefault(symbol, 0);
    }

    public double calculatePnL(Map<String, Double> currentPrices){
        double sum = 0.0;
        for (String s : currentPrices.keySet()){
            sum = sum + this.holdings.getOrDefault(s, 0) * currentPrices.get(s);
        }    
        sum = sum + this.cashBalance - this.initialCash;

        return sum;
    }
}
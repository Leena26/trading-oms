package engine;

import model.order.Order;
import model.order.OrderStatus;
import model.trade.Trade;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class MatchingEngine{
    private OrderBook orderBook;

    public MatchingEngine(OrderBook orderBook){
        this.orderBook = orderBook;
    }

    public List<Trade> submitOrder(Order order, boolean side){
        if (order.validate()){
            this.orderBook.addOrder(order, side);
            return matchOrders();
        }
        return new ArrayList<>();
    }

    public List<Trade> matchOrders(){
        List<Trade> res = new ArrayList<>();
        while (!this.orderBook.getBuyOrders().isEmpty() && !this.orderBook.getSellOrders().isEmpty() && this.orderBook.getEffectivePrice(this.orderBook.peekBestBuy()) >= this.orderBook.getEffectivePrice(this.orderBook.peekBestSell())){
            bestBuy = this.orderBook.pollBestBuy();
            bestSell = this.orderBook.pollBestSell();
            double executedPrice = this.orderBook.getEffectivePrice(bestSell);
            int filledQuantity = Math.min(bestBuy.getQuantity(), bestSell.getQuantity());
            Trade t = new Trade(UUID.randomUUID().toString(), bestBuy.getOrderId(), bestSell.getOrderId(), bestBuy.getSymbol(), executedPrice, filledQuantity);
            res.add(t);
        return t;
    }

    public void cancelOrder(String orderId){
        this.orderBook.cancelOrder(orderId);
    }
}
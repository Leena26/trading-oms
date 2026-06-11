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
            Order bestBuy = this.orderBook.pollBestBuy();
            Order bestSell = this.orderBook.pollBestSell();
            double executedPrice = this.orderBook.getEffectivePrice(bestSell);
            int filledQuantity = Math.min(bestBuy.getQuantity(), bestSell.getQuantity());
            Trade t = new Trade(UUID.randomUUID().toString(), bestBuy.getOrderId(), bestSell.getOrderId(), bestBuy.getSymbol(), executedPrice, filledQuantity);
            res.add(t);

            if (bestBuy.getQuantity() > bestSell.getQuantity()){
                bestBuy.setQuantity(bestBuy.getQuantity()-filledQuantity);
                bestBuy.setStatus(OrderStatus.PARTIALLY_FILLED);
                this.orderBook.addOrder(bestBuy, true);
                bestSell.setStatus(OrderStatus.FILLED);
            } else if (bestBuy.getQuantity() < bestSell.getQuantity()){
                bestSell.setQuantity(bestSell.getQuantity()-filledQuantity);
                bestSell.setStatus(OrderStatus.PARTIALLY_FILLED);
                this.orderBook.addOrder(bestSell, false);
                bestBuy.setStatus(OrderStatus.FILLED);
            } else {
                bestBuy.setStatus(OrderStatus.FILLED);
                bestSell.setStatus(OrderStatus.FILLED);
            }
        }

        return res;
    }

    public void cancelOrder(String orderId){
        this.orderBook.cancelOrder(orderId);
    }
}
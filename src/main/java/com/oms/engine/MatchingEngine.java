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
    }

    public void cancelOrder(String orderId){
        this.orderBook.cancelOrder(orderId);
    }
}
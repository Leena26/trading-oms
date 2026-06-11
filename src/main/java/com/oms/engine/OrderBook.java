package engine;
import model.order.Order;
import model.order.OrderStatus;
import model.order.LimitOrder;
import model.order.MarketOrder;

import java.util.PriorityQueue;

public class OrderBook{
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;

    public OrderBook(){
        this.buyOrders = new PriorityQueue<>((a, b) -> Double.compare(getEffectivePrice(b), getEffectivePrice(a)));
        this.sellOrders = new PriorityQueue<>((a, b) -> Double.compare(getEffectivePrice(a), getEffectivePrice(b)));
    }

    private double getEffectivePrice(Order o){
        if (o instanceof LimitOrder){
            return ((LimitOrder) o).getLimitPrice();
        } else if (o instanceof MarketOrder){
            return Double.MAX_VALUE;
        }
        return 0.0;
    }

    public void addOrder(Order order, boolean side){
        if (!order.validate()){
            throw new IllegalArgumentException("Invalid Order");
        }

        if (side){
            this.buyOrders.add(order);
        } else{
            this.sellOrders.add(order);
        }
    }

    public Order cancelOrder(String orderId){
        for (Order o : this.buyOrders){
            if (o.getOrderId().equals(orderId)){
                this.buyOrders.remove(o);
                o.setStatus(OrderStatus.CANCELLED);
                return o;
            }
        }

        for (Order o : this.sellOrders){
            if (o.getOrderId().equals(orderId)){
                this.sellOrders.remove(o);
                o.setStatus(OrderStatus.CANCELLED);
                return o;
            }
        }

        return null;
    }

    public Order peekBestBuy(){
        return this.buyOrders.peek();
    }

    public Order peekBestSell(){
        return this.sellOrders.peek();
    }

    public Order pollBestBuy(){
        return this.buyOrders.poll();
    }

    public Order pollBestSell(){
        return this.sellOrders.poll();
    }

    public PriorityQueue<Order> getBuyOrders(){
        return this.buyOrders;
    }

    public PriorityQueue<Order> getSellOrders(){
        return this.sellOrders;
    }
}
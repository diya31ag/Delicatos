package com.example.delicatos.Services;

import com.example.delicatos.Models.CartItem;
import com.example.delicatos.Models.Order;
import com.example.delicatos.Models.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.delicatos.Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
@Service
public class OrderService {
    OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }
    public void addOrder(List<CartItem> itemList, double total){
        Order order=new Order();
        order.setCustomer(itemList.get(0).getCustomer());
        order.setRestaurant(itemList.get(0).getRestaurant());
        DateFormat date_format_obj = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date_obj = new Date();
        order.setDateTime(date_format_obj.format(date_obj).toString());
        order.setStatus("placed");
        order.setTotal(total);
        List<OrderItem> items=new ArrayList<>();
        for(int i=0;i<itemList.size();i++){
            OrderItem item=new OrderItem(0,itemList.get(i).getItem(),itemList.get(i).getQuantity());
            items.add(item);
        }
        order.setItemList(items);
        orderRepository.addOrder(order);
    }
    public List<Order> getOrderByCustomer(String customer){
        return orderRepository.getOrderByCustomer(customer);
    }
    public List<Order> getOrderByRestaurant(String restaurant){
        return orderRepository.getOrderByRestaurant(restaurant);
    }
}

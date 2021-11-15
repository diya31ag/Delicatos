package com.example.delicatos.Controller;

import com.example.delicatos.Models.Order;
import com.example.delicatos.Models.Restaurant;
import com.example.delicatos.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.delicatos.Models.CartItem;
import com.example.delicatos.Services.CartServiceImpl;
import java.util.List;
import com.example.delicatos.Services.RestaurantServiceImplementation;
@Controller
public class OrderController {
    OrderService orderService;
    CartServiceImpl cartService;
    RestaurantServiceImplementation restaurantServiceImplementation;
    @Autowired
    public OrderController(OrderService orderService, CartServiceImpl cartService, RestaurantServiceImplementation restaurantServiceImplementation){
        this.orderService=orderService;
        this.cartService=cartService;
        this.restaurantServiceImplementation=restaurantServiceImplementation;
    }
    @GetMapping("/addOrder")
    public String addOrders(SecurityContextHolderAwareRequestWrapper request, @RequestParam double total){
        List<CartItem> cartItemList=cartService.findByCustomer(request.getRemoteUser());
        System.out.println("entered orders");
        orderService.addOrder(cartItemList, total);
        System.out.println("exit orders");
        return "redirect:/customer/orders";
    }
    @GetMapping("/customer/orders")
    public String ordersForCustomer(Model model, SecurityContextHolderAwareRequestWrapper request){
        List<Order> orderList=orderService.getOrderByCustomer(request.getRemoteUser());
        model.addAttribute("orderList", orderList);
//        Restaurant restaurant = restaurantServiceImplementation.findByUsername(orderList.get(0).getRestaurant());
//        model.addAttribute("restaurant", restaurant);
        return "orders";
    }
    @GetMapping("/restaurant/orders")
    public String ordersForRestaurant(Model model, SecurityContextHolderAwareRequestWrapper request){
        List<Order> orderList=orderService.getOrderByRestaurant(request.getRemoteUser());
        model.addAttribute("orderList", orderList);
        return "restaurantOrders";
    }
}

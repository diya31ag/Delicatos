package com.example.delicatos.Controller;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {
    @GetMapping("/addOrder")
    public String addOrders(@RequestParam List<>){
        return "orders";
    }
}

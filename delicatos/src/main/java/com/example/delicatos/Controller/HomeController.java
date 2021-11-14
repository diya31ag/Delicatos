package com.example.delicatos.Controller;

import com.example.delicatos.Models.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.List;
import com.example.delicatos.Models.Restaurant;
import com.example.delicatos.Services.RestaurantServiceImplementation;
import com.example.delicatos.Models.Customer;
import com.example.delicatos.Services.CustomerServiceImplementation;
@Controller
public class HomeController {
    RestaurantServiceImplementation restaurantServiceImplementation;
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    public HomeController(RestaurantServiceImplementation restaurantServiceImplementation, CustomerServiceImplementation customerServiceImplementation){
        this.restaurantServiceImplementation=restaurantServiceImplementation;
        this.customerServiceImplementation=customerServiceImplementation;
    }
    @GetMapping("")
    public String home(SecurityContextHolderAwareRequestWrapper request, Authentication authentication, Model model){
        List<Restaurant> restaurants = restaurantServiceImplementation.getAllRestaurants();
        model.addAttribute("restaurants",restaurants);
        String error="";
        if (model.asMap().get("error") != null) {
            error=model.asMap().get("error").toString();
        }
        model.addAttribute("error", error);
        if(request.getRemoteUser()==null)
        return "homepage";
        else if(authentication.getAuthorities().toArray()[0].toString().equals("customer")){
            Customer customer=customerServiceImplementation.findByUsername(request.getRemoteUser());

            model.addAttribute("customer", customer);
            return "homepage";}
        else
        return "redirect:/restaurant";
    }
}

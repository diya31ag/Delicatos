package com.example.delicatos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import com.example.delicatos.Services.CustomerServiceImplementation;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.delicatos.Models.Customer;

//import java.security.Principal;
@Controller
public class CustomerController {
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    public CustomerController(CustomerServiceImplementation customerServiceImplementation){
        this.customerServiceImplementation = customerServiceImplementation;
    }
    @GetMapping("/customerProfileEdit")
    public String Customer(Model model){
        System.out.println("wetryhgf");
        Customer customer = new Customer();
            customer.setEmail(model.asMap().get("email").toString());
            model.addAttribute("customer", customer);
            return "customerProfileEdit";
    }

    @PostMapping("/customerProfileEdit")
    public String customer(@ModelAttribute("customer") Customer customer, Model model, SecurityContextHolderAwareRequestWrapper request, @RequestParam String email){
        customer.setEmail(email);
        customerServiceImplementation.save(customer);
        return "redirect:/login";
    }

    @RequestMapping("/customer")
    public String customer(Model model){
        return "customer";
    }
}

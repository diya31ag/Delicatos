package com.example.delicatos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import com.example.delicatos.Services.CustomerServiceImplementation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.delicatos.Models.Customer;
import org.springframework.web.bind.annotation.RequestMapping;

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
            model.addAttribute("customer", new Customer());
            return "customerProfileEdit";
    }

    @PostMapping("/customerProfileEdit")
    public String customer(@ModelAttribute("customer") Customer customer, Model model, SecurityContextHolderAwareRequestWrapper request){
        customer.setEmail(request.getRemoteUser());
        customerServiceImplementation.save(customer);
        return "redirect:/login";
    }

    @RequestMapping("/customer")
    public String customer(Model model){
        return "customer";
    }
}

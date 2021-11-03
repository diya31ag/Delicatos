package com.example.delicatos.Controller;

import com.example.delicatos.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import com.example.delicatos.Services.CustomerServiceImplementation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.delicatos.Models.Customer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

//import java.security.Principal;
@Controller
public class CustomerController {
    CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    public CustomerController(CustomerServiceImplementation customerServiceImplementation){
        this.customerServiceImplementation = customerServiceImplementation;
    }
    @GetMapping("/customer")
    public String Customer(Model model){
        System.out.println("wetryhgf");
//        if(request.getMethod().equals("GET")) {
            model.addAttribute("customer", new Customer());
            return "customer";
//        }
//        else{
//            System.out.println("qwer");
//            return "homepage";
//        }
    }

    @PostMapping("/customer")
    public String customer(@ModelAttribute("customer") Customer customer, Model model, SecurityContextHolderAwareRequestWrapper request){
        customer.setEmail(request.getRemoteUser());
        customerServiceImplementation.save(customer);
        return "homepage";
    }
}

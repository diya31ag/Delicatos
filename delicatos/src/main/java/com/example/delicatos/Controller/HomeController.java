package com.example.delicatos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
@Controller
public class HomeController {
    @GetMapping("")
    public String home(SecurityContextHolderAwareRequestWrapper request, Authentication authentication){
        Object c="customer";
        System.out.println(authentication.getAuthorities().toArray()[0].toString().equals("customer"));
        if(request.getRemoteUser()==null)
        return "homepage";
//        System.out.println("");
        else if(authentication.getAuthorities().toArray()[0].toString().equals("customer")) return "redirect:/customer";
        else
        return "redirect:/restaurant";
    }
}

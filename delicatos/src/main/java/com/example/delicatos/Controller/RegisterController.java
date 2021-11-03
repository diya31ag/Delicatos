package com.example.delicatos.Controller;

import com.example.delicatos.Models.User;
import com.example.delicatos.Services.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class RegisterController {
    private DefaultUserService userService;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public RegisterController(DefaultUserService userService, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    // path variable to access parts of url
    @PostMapping("/register")//Request parameter for accessing the post request
    public String register(@ModelAttribute("user") User user, BindingResult result, WebRequest request, Model model, RedirectAttributes attributes, @RequestParam String re_password){
        if (user.getPassword().equals(re_password)) {
            try {
                userService.save(user);
                model.addAttribute("response", "Registration Successful");

//        System.out.println(user.getRole());
                return "register_success";

            } catch (Exception e) {
                model.addAttribute("response", "Username already exist");
            }

        } else {
            model.addAttribute("response", "Re enter Password Correctly");
        }
        return "register";
    }
}

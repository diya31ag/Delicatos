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
import com.example.delicatos.Exception.UserAlreadyExistException;
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
        if(result.hasErrors()){
            model.addAttribute("registrationForm", user);
            System.out.println("error1");
            return "register";
        }
        //TODO: Add restrictions to password
        if (user.getPassword().equals(re_password)) {
            try {
                System.out.println("errorgreat");
                userService.register(user);
                System.out.println("errorgreat1");
                if(user.getRole().equals("customer")) return "redirect:/customerProfileEdit";
                else return "redirect:/restaurantProfileEdit";
            }catch (UserAlreadyExistException e){
                result.rejectValue("email", "user.email","An account already exists for this email.");
                model.addAttribute("registrationForm", user);
                System.out.println("error2");
                return "register";
            }

        } else {
            result.rejectValue("password","user.password","Re enter Password Correctly");
            model.addAttribute("response", "Re enter Password Correctly");
        }
        System.out.println("error3");
        return "register";
    }
}

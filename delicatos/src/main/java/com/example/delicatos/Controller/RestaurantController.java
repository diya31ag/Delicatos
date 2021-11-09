package com.example.delicatos.Controller;

import com.example.delicatos.Utilities.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import com.example.delicatos.Services.RestaurantServiceImplementation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.delicatos.Models.Restaurant;
import com.example.delicatos.Models.MenuItem;
import com.example.delicatos.Services.FoodMenuService;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

//import java.security.Principal;
@Controller
public class RestaurantController {
    RestaurantServiceImplementation restaurantServiceImplementation;
    FoodMenuService foodMenuService;
    @Autowired
    public RestaurantController(RestaurantServiceImplementation restaurantServiceImplementation, FoodMenuService foodMenuService){
        this.restaurantServiceImplementation = restaurantServiceImplementation;
        this.foodMenuService = foodMenuService;
    }
    @GetMapping("/restaurantProfileEdit")
    public String Restaurant(Model model){
        System.out.println("wetryhgf");
        model.addAttribute("restaurant", new Restaurant());
        return "restaurantProfileEdit";
    }

    @PostMapping("/restaurantProfileEdit")
    public String restaurant(@ModelAttribute("restaurant") Restaurant restaurant, Model model, SecurityContextHolderAwareRequestWrapper request){
        System.out.println("errorrestaurant0");
        restaurant.setEmail(request.getRemoteUser());
        System.out.println("errorrestaurant1");
        restaurantServiceImplementation.save(restaurant);
        System.out.println("errorrestaurant2");
        return "redirect:/login";
    }

    @GetMapping("/restaurant")
    public String restaurant(Model model){
//        String[] cuisines = new String[]{"italian", "indian", "chinese", "japanese", "south indian"};
        String[] Categories = new String[]{"dessert", "snacks", "drinks", "starter", "lunch", "dinner", "breakfast", "vegetables", "sweets","italian", "indian", "chinese", "japanese", "south indian"};
        model.addAttribute("Categories", Categories);
        model.addAttribute("menuItem", new MenuItem());
        return "restaurant";
    }

    @PostMapping("/restaurant")
    public String restaurant(Model model, @ModelAttribute("menuItem") MenuItem menuItem, SecurityContextHolderAwareRequestWrapper request, @RequestParam("img") MultipartFile multipartFile){
        System.out.println(request.getRemoteUser());
        System.out.println("sdfg");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        menuItem.setImage(fileName);
        menuItem.setRestaurant(request.getRemoteUser());
        MenuItem savedItem=foodMenuService.addMenuItem(menuItem);
        String uploadDir = "food-item-photos/" + savedItem.getId();
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        catch(IOException e){
            System.out.println(e);
        }

        return "restaurant";
    }
}

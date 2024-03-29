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

import java.util.Collection;
import java.util.List;
import com.example.delicatos.Services.RestaurantServiceImplementation;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

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
        Restaurant restaurant = new Restaurant();
        System.out.println(model.asMap().get("email").toString());
        restaurant.setEmail(model.asMap().get("email").toString());
        model.addAttribute("restaurant", restaurant);
        return "restaurantProfileEdit";
    }

    @PostMapping("/restaurantProfileEdit")
    public String restaurant(@ModelAttribute("restaurant") Restaurant restaurant, Model model, SecurityContextHolderAwareRequestWrapper request, @RequestParam String email, @RequestParam("img") MultipartFile multipartFile){
        System.out.println(email);
        restaurant.setEmail(email);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        restaurant.setImage(fileName);
        Restaurant savedRestaurant=restaurantServiceImplementation.save(restaurant);
        String uploadDir = "src/main/resources/static/img/restaurant/" + savedRestaurant.getId();
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return "redirect:/login";
    }

    @GetMapping("/restaurant")
    public String restaurant(Model model, SecurityContextHolderAwareRequestWrapper request){
        String[] Categories = new String[]{"dessert", "drinks", "starter", "vegetables","italian", "indian"};
        model.addAttribute("Categories", Categories);
        model.addAttribute("menuItem", new MenuItem());
        List<MenuItem> menuItemList = foodMenuService.getMenuItemByRestaurant(request.getRemoteUser());
        model.addAttribute("menuItemList", menuItemList);
        return "restaurant";
    }

    @PostMapping("/restaurant")
    public String restaurant(Model model, @ModelAttribute("menuItem") MenuItem menuItem, SecurityContextHolderAwareRequestWrapper request, @RequestParam("img") MultipartFile multipartFile){
        System.out.println(request.getRemoteUser());
        System.out.println("sdfg");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        fileName=fileName.replaceAll("\\s", "");
        menuItem.setImage(fileName);
        menuItem.setRestaurant(request.getRemoteUser());
        MenuItem savedItem=foodMenuService.addMenuItem(menuItem);
        String uploadDir = "src/main/resources/static/img/food-item-photos/" + savedItem.getId();
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        catch(IOException e){
            System.out.println(e);
        }

        return "redirect:/restaurant/menu";
    }
    @GetMapping("/restaurant/menu")
    public String items(Model model, SecurityContextHolderAwareRequestWrapper request){
        Restaurant restaurant=restaurantServiceImplementation.findByUsername(request.getRemoteUser());
        model.addAttribute("restaurant", restaurant);
        String[] Categories = new String[]{"dessert", "drinks", "starter", "vegetables","italian", "indian"};
        model.addAttribute("Categories", Categories);
//        List<MenuItem> menuItemList=foodMenuService.getMenuItemByRestaurant(restaurant.getEmail());
//        model.addAttribute("menuItemList", menuItemList);
        Map<String, Collection<MenuItem>> menuItemMap = foodMenuService.getItemFromRestaurantAndCategoryList(restaurant.getEmail(),Categories);
        model.addAttribute("menuItemMap", menuItemMap);
        System.out.println(menuItemMap.get("dessert").size());
        return "restaurantMenu";
    }
    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam int itemId){
        foodMenuService.deleteItemByItemId(itemId);
        return "redirect:/restaurant";
    }
    @GetMapping("/restaurant/profile")
    public String restaurantProfile(Model model,SecurityContextHolderAwareRequestWrapper request){
        Restaurant restaurant=restaurantServiceImplementation.findByUsername(request.getRemoteUser());
        model.addAttribute("restaurant",restaurant);
        return "restaurantProfile";
    }
}

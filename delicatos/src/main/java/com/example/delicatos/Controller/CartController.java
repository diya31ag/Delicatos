package com.example.delicatos.Controller;

import com.example.delicatos.Models.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.delicatos.Models.CartItem;
import com.example.delicatos.Services.CartServiceImpl;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.delicatos.Services.FoodMenuService;

import java.util.ArrayList;
import com.example.delicatos.Models.Restaurant;
import com.example.delicatos.Services.RestaurantServiceImplementation;
import java.util.List;

@Controller
public class CartController {
    CartServiceImpl cartService;
    FoodMenuService foodMenuService;
    RestaurantServiceImplementation restaurantServiceImplementation;
    @Autowired
    public CartController(CartServiceImpl cartService, FoodMenuService foodMenuService, RestaurantServiceImplementation restaurantServiceImplementation){
        this.cartService=cartService;
        this.foodMenuService=foodMenuService;
        this.restaurantServiceImplementation=restaurantServiceImplementation;
    }
    @GetMapping("/cart/addItem")
    public String addItemToCart(@RequestParam int itemId, SecurityContextHolderAwareRequestWrapper request, @RequestParam String restaurant, Model model, RedirectAttributes attributes){
        List<CartItem> cartItemList=cartService.findByCustomer(request.getRemoteUser());
        System.out.println(itemId+"wertf");
        if(!cartItemList.isEmpty()&&!cartItemList.get(0).getRestaurant().equals(restaurant)){
            System.out.println(cartItemList.get(0).getRestaurant().equals(restaurant));
            System.out.println(cartItemList.get(0).getRestaurant().toString()==restaurant.toString());
            System.out.println(restaurant);
            model.addAttribute("restaurant",restaurant);
            attributes.addFlashAttribute("error","You can place items of only one restaurant in the cart. Remove the existing items from cart in order to place items from some other restaurant");
            return "redirect:/";
        }
        System.out.println("qdtynfd");
        MenuItem menuItem=foodMenuService.getMenuItemById(itemId);

        CartItem cartItem=new CartItem(1,request.getRemoteUser(),restaurant,menuItem,1);

        cartService.addItemToCart(cartItem);
        return "redirect:/cart";
    }
    @GetMapping("/cart")
    public String cart(SecurityContextHolderAwareRequestWrapper request, Model model){
        List<CartItem> cartItemList=cartService.findByCustomer(request.getRemoteUser());
        Restaurant restaurant = restaurantServiceImplementation.findByUsername(cartItemList.get(0).getRestaurant());
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("restaurant", restaurant);
        double total=0;
        for(int i=0;i<cartItemList.size();i++)
            total+=cartItemList.get(i).getItem().getPrice()*cartItemList.get(i).getQuantity();
        model.addAttribute("total", total);
        return "cart";
    }
    @GetMapping("/cart/updateQuantity")
    public String addQuantity(@RequestParam int id, @RequestParam int quantity){
        if(quantity<0) quantity=0;
        cartService.updateQuantity(quantity,id);
        return "redirect:/cart";
    }
    @GetMapping("/cart/delete")
    public String delete(@RequestParam int id){
        cartService.deleteItem(id);
        return "redirect:/cart";
    }
}

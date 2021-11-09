package com.example.delicatos.Services;
import com.example.delicatos.Models.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.delicatos.Repository.FoodMenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
@Service
public class FoodMenuService {
    private FoodMenuRepository foodMenuRepository;
    @Autowired
    public FoodMenuService(FoodMenuRepository foodMenuRepository){
        this.foodMenuRepository=foodMenuRepository;
    }
    public MenuItem addMenuItem(MenuItem menuItem){
        return foodMenuRepository.addMenuItem(menuItem);
    }
    public List<MenuItem> getMenuItemByRestaurantId(String restaurantId){
        return foodMenuRepository.getMenuItemByRestaurantId(restaurantId);
    }
//    public List<MenuItem> getMenuItemByRestaurantIdAndCuisineType(String cuisineType, int restaurantId){
//        return foodMenuRepository.getMenuItemByRestaurantIdAndCuisineType(cuisineType,restaurantId);
//    }
//    public MenuItem getMenuItemById(int itemId){
//        return foodMenuRepository.getMenuItemById(itemId);
//    }
}

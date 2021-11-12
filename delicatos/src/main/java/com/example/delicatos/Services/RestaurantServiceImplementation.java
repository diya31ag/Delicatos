package com.example.delicatos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.delicatos.Repository.RestaurantProfileRepository;
import com.example.delicatos.Models.Restaurant;

import java.util.List;
import java.util.ArrayList;
import java.util.Locale;

@Service
public class RestaurantServiceImplementation {
    RestaurantProfileRepository restaurantProfileRepository;
    @Autowired
    public RestaurantServiceImplementation(RestaurantProfileRepository restaurantProfileRepository) {
        this.restaurantProfileRepository = restaurantProfileRepository;
    }
    public Restaurant findByUsername(String email){
        return restaurantProfileRepository.findByEmail(email);
    }
    public Restaurant findById(int id){ return restaurantProfileRepository.findById(id); }
    public Restaurant save(Restaurant restaurant){
        System.out.println(restaurant.getImageURL());
        restaurant.setCity(restaurant.getCity().toLowerCase());
        restaurantProfileRepository.save(restaurant);
        return restaurantProfileRepository.findByEmail(restaurant.getEmail());
    }
    public List<Restaurant> getAllRestaurants(){
        return restaurantProfileRepository.getAllRestaurants();
    }

//    public List<Restaurant> getRestaurantByName(final String restaurantName){}
    //TODO

//    public List<Restaurant> getRestaurantsByCity(final String city){}
    //TODO
}

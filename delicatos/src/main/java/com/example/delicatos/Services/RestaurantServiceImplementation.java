package com.example.delicatos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.delicatos.Repository.RestaurantProfileRepository;
import com.example.delicatos.Models.Restaurant;

import java.util.List;
import java.util.ArrayList;
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
    public void save(Restaurant restaurant){
        restaurantProfileRepository.save(restaurant);
    }
//    public List<Restaurant> getAllRestaurants(){}
        //TODO

//    public List<Restaurant> getRestaurantByName(final String restaurantName){}
    //TODO

//    public List<Restaurant> getRestaurantsByCity(final String city){}
    //TODO
}

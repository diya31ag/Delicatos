package com.example.delicatos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.delicatos.Repository.CartRepository;
import com.example.delicatos.Models.CartItem;

import java.util.List;

@Service
public class CartServiceImpl {
    CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    public void addItemToCart(CartItem cartItem){
        cartRepository.addItemToCart(cartItem);
    }
    public List<CartItem> findByCustomer(String customer){
        return cartRepository.findByCustomer(customer);
    }
    public CartItem findById(int id){
        return cartRepository.findById(id);
    }
    public void updateQuantity(int quantity, int id){
        cartRepository.updateQuantity(quantity, id);
    }
    public void deleteItem(int id){
        cartRepository.deleteItem(id);
    }
}

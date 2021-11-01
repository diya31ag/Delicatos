package com.example.delicatos.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.delicatos.Repository.CustomerProfileRepository;
import com.example.delicatos.Models.Customer;
@Service
public class CustomerServiceImplementation {
    CustomerProfileRepository customerProfileRepository;
    @Autowired
    public CustomerServiceImplementation(CustomerProfileRepository customerProfileRepository) {
        this.customerProfileRepository = customerProfileRepository;
    }
    public Customer findByUsername(String email){
        return customerProfileRepository.findByEmail(email);
    }
    public void save(Customer customer){
        customerProfileRepository.save(customer);
    }
}

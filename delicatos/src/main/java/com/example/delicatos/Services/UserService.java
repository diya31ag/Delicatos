package com.example.delicatos.Services;
import com.example.delicatos.Models.User;
public interface UserService {
    public void save(User user);

    User findByUsername(String email);
}

package com.example.delicatos.Repository;
import com.example.delicatos.Models.User;
public interface UserRepository {
    public User findByEmail(String email);
    public void save(User user);
}

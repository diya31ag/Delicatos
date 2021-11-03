package com.example.delicatos.Services;

import com.example.delicatos.Repository.UserRepositoryImplementation;
import org.springframework.stereotype.Service;
import com.example.delicatos.Models.User;
import com.example.delicatos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.delicatos.Exception.UserAlreadyExistException;
@Service
public class DefaultUserService{
    private UserRepositoryImplementation userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DefaultUserService(UserRepositoryImplementation userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public void register(User user) throws UserAlreadyExistException {

        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new UserAlreadyExistException("User already exists for this email");
        }
        save(user);
    }



    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }
}

package com.example.delicatos.Services;

//import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.delicatos.Repository.UserRepositoryImplementation;
import com.example.delicatos.Models.User;
import org.springframework.security.core.userdetails.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
    UserRepositoryImplementation userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepositoryImplementation userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println(email);
        User user = userRepository.findByEmail(email);
        List<GrantedAuthority> grantList = new ArrayList<>();
        String[] roles = user.getRole().split(" ");
        for(int i=0;i<roles.length;i++){
            GrantedAuthority authority = new SimpleGrantedAuthority(roles[i]);
            System.out.println(roles[i]);
            grantList.add(authority);
        }
//        System.out.println(user.getEmail());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),grantList
        );
    }
}

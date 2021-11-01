package com.example.delicatos;

import com.example.delicatos.Repository.UserRepository;
import com.example.delicatos.Services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DelicatosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelicatosApplication.class, args);
    }

}

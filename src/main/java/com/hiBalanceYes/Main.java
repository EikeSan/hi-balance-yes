package com.hiBalanceYes;

import com.hiBalanceYes.model.Role;
import com.hiBalanceYes.model.User;
import com.hiBalanceYes.repository.UserRepository;
import com.hiBalanceYes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Arrays;

@SpringBootApplication
public class Main {

//    @Bean
//    public CommandLineRunner setupDefaultUser(UserService service){
//        return  args -> {
//            service.save(new User("user", "user",
//                    Arrays.asList(new Role("USER"), new Role("ACTUATOR")), true));
//        };
//    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


}

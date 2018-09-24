package com.hiBalanceYes;

import com.hiBalanceYes.model.Role;
import com.hiBalanceYes.model.User;
import com.hiBalanceYes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


import java.util.Arrays;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
		if (userRepository.count() == 0 ) {
            userRepository.save(new User("usuario", "123456", Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        }


	    builder.userDetailsService(s -> new CustomUserDetails(userRepository.findByUsername(s)));
	}
}

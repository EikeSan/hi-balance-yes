package com.hiBalanceYes.service;

import com.hiBalanceYes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.isActive(),
                        user.isActive(),
                        user.isActive(),
                        user.isActive(),
                        AuthorityUtils.createAuthorityList(
                                user.getRoles().stream().map(role -> "ROLE_" + role.getName().toLowerCase())
                                .collect(Collectors.toList())
                                .toArray(new String[]{}))
                        ))
                .orElseThrow(() -> new UsernameNotFoundException("No user with the name " + username +
                        "was found in databse" ));
    }
}

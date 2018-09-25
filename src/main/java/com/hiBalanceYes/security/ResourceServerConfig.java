package com.hiBalanceYes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import static com.hiBalanceYes.security.SecurityConstants.NO_AUTH_URL;
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    JsonToUrlEncodeAuthenticationFilter jsonFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jsonFilter, ChannelProcessingFilter.class)
                .csrf().and().httpBasic().disable()
                .authorizeRequests()
                .antMatchers(NO_AUTH_URL).permitAll()
                .antMatchers("/private/**").authenticated();
    }
}

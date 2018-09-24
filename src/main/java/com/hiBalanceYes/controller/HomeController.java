package com.hiBalanceYes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String home(){
        return "Hello";
    }

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private";
    }
}

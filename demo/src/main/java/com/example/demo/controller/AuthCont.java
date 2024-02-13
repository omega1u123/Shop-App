package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/auth")
public class AuthCont {
    @GetMapping("/loginPage")
    public String auth(){
        return "login";
    }
}

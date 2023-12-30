package com.example.demo.controller;

import org.springframework.stereotype.Service;

@Service
public class LoggingController {
    String username = "-1";
    String password = "-1";
 
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void login(String username, String password){
        this.username = username;
        this.password = password;
    }
}

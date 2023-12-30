package com.example.demo.Singleton;

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
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public void login(String username, String password){
        this.username = username;
        this.password = password;
    }
    public void logout(){
        this.username = "-1";
        this.password = "-1";
    }
    public boolean isLoggedIn(){
        return !username.equals("-1") && !password.equals("-1");
    }
}

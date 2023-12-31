package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Singleton.LoggingController;
import com.example.demo.SystemService.NotificationsService;
import com.example.demo.models.NotificationTemplate;
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationsService notificationsService;
    @Autowired
    private LoggingController loggingController;

    @GetMapping("/getNotifications")
    public ResponseEntity<Object> getNotifications() {
        return ResponseEntity.ok(notificationsService.getAllNotification());
    }
    @GetMapping("/getMyNotification")
    public ResponseEntity<Object> getNotification() {
        if (!loggingController.isLoggedIn()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please sign in first!");
        }
        return ResponseEntity.ok(notificationsService.getUserNotification(loggingController.getUsername()));
    }
    @GetMapping("/getMostNotifiedEmail")
    public ResponseEntity<Object> getMostNotfifiedEmail(){
        return ResponseEntity.ok().body(notificationsService.getMostNotifiedEmail());
    }
    @GetMapping("/getMostNotifiedPhoneNumber")
    public ResponseEntity<Object> getMostNotfifiedPhoneNumber(){
        return ResponseEntity.ok().body(notificationsService.getMostNotifiedPhoneNumber());
    }
    @GetMapping("/getMostNotifiedTemplate")
    public ResponseEntity<Object> getMostNotfifiedTemplate(){
        return ResponseEntity.ok().body(notificationsService.getMostNotifiedTemplate());
    }
}

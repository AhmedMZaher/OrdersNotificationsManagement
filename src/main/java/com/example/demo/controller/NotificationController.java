package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SystemService.NotificationsService;
@RestController
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private NotificationsService notificationsService;

    @GetMapping("/getNotifications")
    public ResponseEntity<Object> getNotifications() {
        return ResponseEntity.ok(notificationsService.notificationsArray);
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

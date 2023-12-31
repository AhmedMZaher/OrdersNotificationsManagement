package com.example.demo.SystemService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.NotificationQueueService;
import com.example.demo.models.NotificationTemplate;

@Service
@EnableScheduling
@Component
public class NotificationsService {
  @Autowired
  NotificationQueueService notificationQueueService;
  public ArrayList<NotificationTemplate> notificationsArray = new ArrayList<>();

  @Scheduled(fixedRate = 5000)
  public void removeMessagesFromQueue() {
    NotificationTemplate notificationTemplate = notificationQueueService.dequeue();
    if (notificationTemplate == null)
      return;
    notificationTemplate.getCustomer().getNotifier().send(notificationTemplate);
    notificationsArray.add(notificationTemplate);
  }
  public String getMostNotifiedEmail(){
    return notificationQueueService.getMostNotifiedEmail();
  }
  public String getMostNotifiedPhoneNumber(){
    return notificationQueueService.getMostNotifiedPhoneNumber();
  }
  public String getMostNotifiedTemplate(){
    return notificationQueueService.getMostNotifiedTemplate();
  }
  public ArrayList<String> getUserNotification(String username) {
    ArrayList<String> userNotifications = new ArrayList<>();
    for(NotificationTemplate notification : notificationsArray){
      if(notification.getCustomer().getLoginData().getUsername().equals(username)){
        userNotifications.add(notification.getContent());
      }
    }
    return userNotifications;
  }
  public ArrayList<String> getAllNotification(){
    ArrayList<String> userNotifications = new ArrayList<>();
    for(NotificationTemplate notification : notificationsArray){
        userNotifications.add(notification.getContent());
    }
    return userNotifications;
  }
}

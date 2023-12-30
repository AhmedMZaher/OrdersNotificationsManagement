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
    public ArrayList<String> notificationsArray = new ArrayList<>();
    @Scheduled(fixedRate = 5000)
    public void removeMessagesFromQueue() {
        NotificationTemplate notificationTemplate = notificationQueueService.dequeue();
        if(notificationTemplate == null)
          return;
        notificationTemplate.getCustomer().getNotifier().send(notificationTemplate);
        notificationsArray.add(notificationTemplate.getContent());
    }
}

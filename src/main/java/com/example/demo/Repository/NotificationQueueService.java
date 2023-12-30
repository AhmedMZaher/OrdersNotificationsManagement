package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.demo.models.NotificationTemplate;

@Repository
public class NotificationQueueService {
    private Queue<NotificationTemplate> notificationQueue = new LinkedList<>();

    public void enqueueNotification(NotificationTemplate notificationTemplate) {
        notificationQueue.add(notificationTemplate);
    }

    public List<NotificationTemplate> getQueueContent() {
        return new ArrayList<NotificationTemplate>(notificationQueue);
    }
    public NotificationTemplate dequeue(){
        return notificationQueue.poll();
    }
}

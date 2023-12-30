package com.example.demo.SystemService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;
import com.example.demo.models.NotificationTemplate;

@Service
public class NotificationQueueService {
    private Queue<NotificationTemplate> notificationQueue = new LinkedList<>();

    public void enqueueNotification(NotificationTemplate notificationTemplate) {
        notificationQueue.add(notificationTemplate);
    }

    public List<NotificationTemplate> getQueueContent() {
        return new ArrayList<NotificationTemplate>(notificationQueue);
    }
}

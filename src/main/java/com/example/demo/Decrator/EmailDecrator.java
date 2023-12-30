package com.example.demo.Decrator;

import com.example.demo.models.NotificationTemplate;

public class EmailDecrator extends BaseNotification{
    public EmailDecrator(INotifier iNotifier) {
        super(iNotifier);
    }

    @Override
    public void send(NotificationTemplate notificationTemplate) {
        super.send(notificationTemplate);
        // Dummy impl
    }
}

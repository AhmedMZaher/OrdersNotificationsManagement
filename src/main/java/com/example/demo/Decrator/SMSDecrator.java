package com.example.demo.Decrator;

import com.example.demo.models.NotificationTemplate;

public class SMSDecrator extends BaseNotification{
    public SMSDecrator(INotifier iNotifier) {
        super(iNotifier);
    }

    @Override
    public void send(NotificationTemplate notificationTemplate) {
        super.send(notificationTemplate);
        // Dummy impl
    }
}

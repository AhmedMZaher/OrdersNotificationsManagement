package com.example.demo.Decrator;

import com.example.demo.models.NotificationTemplate;

public class BaseNotification implements INotifier{

    public BaseNotification(INotifier iNotifier){
        this.iNotifier = iNotifier;
    }
    private INotifier iNotifier;
    @Override
    public void send(NotificationTemplate notificationTemplate) {
        iNotifier.send(notificationTemplate);
    }
    
}

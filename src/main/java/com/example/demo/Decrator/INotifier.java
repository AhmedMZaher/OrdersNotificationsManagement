package com.example.demo.Decrator;

import com.example.demo.models.NotificationTemplate;

public interface INotifier {
    public abstract void send(NotificationTemplate notificationTemplate);
}
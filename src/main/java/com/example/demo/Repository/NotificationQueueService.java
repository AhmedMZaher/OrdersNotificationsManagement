package com.example.demo.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Optional;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.example.demo.models.NotificationTemplate;

@Repository
public class NotificationQueueService {
    private Queue<NotificationTemplate> notificationQueue = new LinkedList<>();
    private HashMap<String, Integer> mostNotifiedEmail = new HashMap<>();
    private HashMap<String, Integer> mostNotifiedPhoneNumber = new HashMap<>();
    private HashMap<String, Integer> mostNotifiedTemplate = new HashMap<>();

    public void enqueueNotification(NotificationTemplate notificationTemplate) {
        notificationQueue.add(notificationTemplate);
    }

    public List<NotificationTemplate> getQueueContent() {
        return new ArrayList<NotificationTemplate>(notificationQueue);
    }
    public NotificationTemplate dequeue(){
        updateStatistics(notificationQueue.peek());
        return notificationQueue.poll();
    }
    private void updateStatistics(NotificationTemplate notificationTemplate) {
        String phoneNumber = notificationTemplate.getCustomer().getCustomerData().getPhoneNumber();
        String email = notificationTemplate.getCustomer().getCustomerData().getEmail();
        String template = notificationTemplate.getContent();

        mostNotifiedEmail.put(email, mostNotifiedEmail.getOrDefault(email, 0) + 1);
        mostNotifiedPhoneNumber.put(phoneNumber, mostNotifiedPhoneNumber.getOrDefault(phoneNumber, 0) + 1);
        mostNotifiedTemplate.put(template, mostNotifiedTemplate.getOrDefault(template, 0) + 1);
    }
    public String getMostNotifiedEmail(){
        String maxNotifiedEmail = getMax(mostNotifiedEmail);
        if (mostNotifiedTemplate != null) {
            return maxNotifiedEmail;
        } else {
            return "No most notified template found.";
        }
    }
    public String getMostNotifiedPhoneNumber(){
        String maxNotifiedPhoneNumber = getMax(mostNotifiedPhoneNumber);
        if (mostNotifiedTemplate != null) {
            return maxNotifiedPhoneNumber;
        } else {
            return "No most notified template found.";
        }
    }
    public String getMostNotifiedTemplate(){
        String maxNotifiedTemplate = getMax(mostNotifiedTemplate);
        if (mostNotifiedTemplate != null) {
            return maxNotifiedTemplate;
        } else {
            return "No most notified template found.";
        }
    }
    private String getMax(HashMap<String, Integer> mostNotifiedEmail) {
        // Using Java streams to find the entry with the maximum value
        Optional<Entry<String, Integer>> entryWithMaxValue = mostNotifiedEmail.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue());

        // If the optional is present, return the key; otherwise, return null
        return entryWithMaxValue.map(Entry::getKey).orElse(null);
    }
    
}

package com.example.demo.models;

import java.util.List;

public abstract class NotificationTemplate {
    private String name;
    private String subject;
    private String content;
    private List<String> availableLanguages;
    private Customer customer;

    public NotificationTemplate(String name, String subject, String content,
                                List<String> availableLanguages, Customer customer) {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.availableLanguages = availableLanguages;
        this.customer = customer;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAvailableLanguages() {
        return availableLanguages;
    }

    public void setAvailableLanguages(List<String> availableLanguages) {
        this.availableLanguages = availableLanguages;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomers(Customer customer) {
        this.customer = customer;
    }
}

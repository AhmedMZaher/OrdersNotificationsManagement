package com.example.demo.models;

import java.util.List;

public class NotificationTemplate {
    private String name;
    private String subject;
    private String content;
    private List<String> availableLanguages;
    private List<String> availableChannels;
    private List<String> placeholders;

    // Constructors
    public NotificationTemplate() {
        // Default constructor
    }

    public NotificationTemplate(String name, String subject, String content,
                                List<String> availableLanguages, List<String> availableChannels, List<String> placeholders) {
        this.name = name;
        this.subject = subject;
        this.content = content;
        this.availableLanguages = availableLanguages;
        this.availableChannels = availableChannels;
        this.placeholders = placeholders;
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

    public List<String> getAvailableChannels() {
        return availableChannels;
    }

    public void setAvailableChannels(List<String> availableChannels) {
        this.availableChannels = availableChannels;
    }

    public List<String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<String> placeholders) {
        this.placeholders = placeholders;
    }
}

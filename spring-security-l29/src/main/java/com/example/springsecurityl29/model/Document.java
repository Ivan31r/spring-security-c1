package com.example.springsecurityl29.model;

public class Document {
    private String text;
    private String user;

    public Document(String text, String user) {
        this.text = text;
        this.user = user;
    }

    public Document() {
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

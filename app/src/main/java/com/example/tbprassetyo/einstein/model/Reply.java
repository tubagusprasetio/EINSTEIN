package com.example.tbprassetyo.einstein.model;

public class Reply {
    String body;
    String action;

    public Reply(String body, String action) {
        this.body = body;
        this.action = action;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

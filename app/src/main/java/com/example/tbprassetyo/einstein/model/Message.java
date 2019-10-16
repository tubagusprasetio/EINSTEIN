package com.example.tbprassetyo.einstein.model;

public class Message {
    private int id;
    private String body;
    private int msgType;

    public Message(String body, int msgType) {
        this.body = body;
        this.msgType = msgType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }
}

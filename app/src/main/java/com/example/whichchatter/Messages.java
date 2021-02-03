package com.example.whichchatter;

import java.util.List;

public class Messages {
    public String message, sent, received;
    public List<User> userList;

    public Messages() {
    }

    public Messages(String message, String sent, String received) {
        this.message = message;
        this.sent = sent;
        this.received = received;
    }
}

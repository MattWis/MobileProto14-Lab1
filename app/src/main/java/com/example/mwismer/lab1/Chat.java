package com.example.mwismer.lab1;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mwismer on 9/15/14.
 */
public class Chat {
    String name, message, timestamp;


    //Weird constructor and getters needed for FirebaseListAdapter
    private Chat () { }

    Chat(String name, String message, String timestamp) {
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() { return  message; }

    public String getName() { return name; }

    public String getTimestamp() { return timestamp; }

    public void sendToFirebase(Firebase ref) {
        Firebase newChat = ref.push();
        newChat.setValue(this);
    }

    public Chat(String uname, String textwall) {
        this.name = uname;
        this.message = textwall;
        this.timestamp = new SimpleDateFormat("MM/dd, HH:mm").format(new Date());
    }
}

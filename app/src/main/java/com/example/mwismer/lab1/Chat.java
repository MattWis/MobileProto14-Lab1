package com.example.mwismer.lab1;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mwismer on 9/15/14.
 */
public class Chat {
    String username, message, timestamp;

    public Chat(String uname, String textwall, String time) {
        this.username = uname;
        this.message = textwall;
        this.timestamp = time;
    }

    public Chat(String uname, String textwall) {
        this.username = uname;
        this.message = textwall;
        this.timestamp = new SimpleDateFormat("MM/dd, HH:mm").format(new Date());
    }
}

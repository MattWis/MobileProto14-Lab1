package com.example.mwismer.lab1;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.Query;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mwismer on 9/11/14.
 */
public class ChatAdapter extends FirebaseListAdapter<Chat> {

    Context context;
    public ChatAdapter(Firebase ref, Activity activity, int layout) {
        super(ref, Chat.class, layout, activity);
        this.context = activity.getApplicationContext();
    }

    public class ChatHolder {
        TextView name, message, timestamp;

        public void setChat(Chat chat) {
            this.name.setText(chat.name);
            this.message.setText(chat.message);
            this.timestamp.setText(chat.timestamp);
        }
    }

    @Override
    protected  void populateView(View view, Chat chat) {
        ChatHolder holder;
        if (view.getTag() == null) {
            holder = new ChatHolder();
            holder.name = (TextView) view.findViewById(R.id.username);
            holder.message = (TextView) view.findViewById(R.id.chat_item);
            holder.timestamp = (TextView) view.findViewById(R.id.timestamp);

            view.setTag(holder);
        } else {
            holder = (ChatHolder) view.getTag();
        }
        holder.setChat(chat);
    }

    public void removeLast() {
        if (getCount() > 0) {
            //remove(getItem(getCount() - 1));
        }
    }
}


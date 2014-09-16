package com.example.mwismer.lab1;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mwismer on 9/11/14.
 */
public class ChatAdapter extends ArrayAdapter<Chat> {

    Context context;
    public ChatAdapter(Context context, int resource, List<Chat> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ChatHolder {
        TextView name, message;

        public void setChat(Chat chat) {
            this.name.setText(chat.username);
            this.message.setText(chat.message);
        }
    }

    @Override
    public View getView(int position, View cachedView, ViewGroup parent) {
        ChatHolder holder;
        if (cachedView == null) {
            cachedView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.chat_item, parent, false);

            holder = new ChatHolder();
            holder.name = (TextView) cachedView.findViewById(R.id.username);
            holder.message = (TextView) cachedView.findViewById(R.id.chat_item);

            cachedView.setTag(holder);
        } else {
            holder = (ChatHolder) cachedView.getTag();
        }
        holder.setChat(getItem(position));

        return cachedView;
    }

    public void removeLast() {
        if (getCount() > 0) {
            remove(getItem(getCount() - 1));
        }
    }
}


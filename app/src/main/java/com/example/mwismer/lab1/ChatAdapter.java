package com.example.mwismer.lab1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by mwismer on 9/11/14.
 */
public class ChatAdapter extends ArrayAdapter<String> {

    public ChatAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public void removeLast() {
        if (getCount() > 0) {
            remove(getItem(getCount() - 1));
        }
    }

}


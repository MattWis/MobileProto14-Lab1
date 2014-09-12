package com.example.mwismer.lab1;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mwismer on 9/11/14.
 */
public class MyFragment extends Fragment {
    public MyFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lab1, container, false);

        ListView chatList = (ListView) rootView.findViewById(R.id.chat_list);

        String[] values = new String[] {"first", "example", "chats"};
        final ChatAdapter chatAdapter = new ChatAdapter(
            getActivity(),
            R.layout.chat_item,
            new ArrayList<String>(Arrays.asList(values))
        );
        chatList.setAdapter(chatAdapter);

        Button add = (Button) rootView.findViewById(R.id.add_chat);
        final EditText input = (EditText) rootView.findViewById(R.id.chatbox);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.equals("")) {
                    chatAdapter.add(text);
                    input.getText().clear();
                }
            }
        });

        final Button delete = (Button) rootView.findViewById(R.id.delete_chat);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatAdapter.removeLast();
            }
        });


        return rootView;
    }
}

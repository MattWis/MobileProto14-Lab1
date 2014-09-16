package com.example.mwismer.lab1;

import android.app.Fragment;
import android.content.Context;
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
        final View rootView = inflater.inflate(R.layout.fragment_lab1, container, false);
        final ListView chatList = (ListView) rootView.findViewById(R.id.chat_list);

        final DatabaseHandler db = new DatabaseHandler(getActivity());
        db.open();

        ArrayList<Chat> values;
        if (db.getAllChats().size() == 0) {
            values = new ArrayList<Chat>(Arrays.asList(new Chat[] {new Chat("winner", "first"), new Chat("loser", "example")}));
            db.addChats(values);
        } else {
            values = db.getAllChats();
        }

        final ChatAdapter chatAdapter = new ChatAdapter(getActivity(), R.layout.chat_item, values);
        chatList.setAdapter(chatAdapter);

        final Button add = (Button) rootView.findViewById(R.id.add_chat);
        final EditText input = (EditText) rootView.findViewById(R.id.chatbox);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    String username = getActivity().getSharedPreferences("Lab1", Context.MODE_PRIVATE).getString("username", "default");
                    Chat chat = new Chat(username, text);
                    chatAdapter.add(chat);
                    db.addChat(chat);
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

        final Button change_uname = (Button) rootView.findViewById(R.id.change_username);
        change_uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    getActivity().getSharedPreferences("Lab1", Context.MODE_PRIVATE).edit().putString("username", text).commit();
                    Log.d("FUCKKK", text);
                }
            }
        });
        return rootView;
    }
}

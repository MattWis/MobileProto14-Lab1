package com.example.mwismer.lab1;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by mwismer on 9/11/14.
 */
public class MyFragment extends Fragment {
    public MyFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_lab1, container, false);
        final ListView chatList = (ListView) rootView.findViewById(R.id.chat_list);

        final ArrayList<Chat> values;
        values = new ArrayList<Chat>(Arrays.asList(new Chat[] {}));



        final Firebase chatBase = new Firebase("https://mobprotochat.firebaseio.com/").child("chats");

        final ChatAdapter chatAdapter = new ChatAdapter(chatBase, getActivity(), R.layout.chat_item);
        chatList.setAdapter(chatAdapter);

//        chatBase.child("chats").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                chatAdapter.clear();
//                for (DataSnapshot chat_snapshot: dataSnapshot.getChildren()) {
//                    String name = (String) chat_snapshot.child("name").getValue();
//                    String message = (String) chat_snapshot.child("message").getValue();
//                    String timestamp = (String) chat_snapshot.child("timestamp").getValue();
//                    Chat chat = new Chat(name, message, timestamp);
//                    chatAdapter.add(chat);
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });

        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, int i, long l) {
                final View editChat = ((LayoutInflater) view.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.edit_chat, container, false);
                final Chat chat = (Chat) adapterView.getAdapter().getItem(i);
                final int position = i;

                ((EditText) editChat.findViewById(R.id.edit_uname)).setText(chat.name);
                ((EditText) editChat.findViewById(R.id.edit_message)).setText(chat.message);

                new AlertDialog.Builder(view.getContext()).setView(editChat).setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    chat.name = ((EditText) editChat.findViewById(R.id.edit_uname)).getText().toString();
                    chat.message = ((EditText) editChat.findViewById(R.id.edit_message)).getText().toString();
                    ((ChatAdapter.ChatHolder) view.getTag()).setChat(chat);
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                    ChatAdapter adapter = (ChatAdapter) adapterView.getAdapter();
//                    adapter.remove(adapter.getItem(position));
                    }
                }).setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                }).create().show();
            }
        });

        final Button add = (Button) rootView.findViewById(R.id.add_chat);
        final EditText input = (EditText) rootView.findViewById(R.id.chatbox);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    String username = getActivity().getSharedPreferences("Lab1", Context.MODE_PRIVATE).getString("username", "default");
//                    Chat chat = new Chat(username, text);
//                    chatBase.push().setValue(chat);
                    input.getText().clear();
                }
            }
        });

        final Button change_uname = (Button) rootView.findViewById(R.id.change_username);
        change_uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if (!text.isEmpty()) {
                    getActivity().getSharedPreferences("Lab1", Context.MODE_PRIVATE).edit().putString("username", text).commit();
                }
            }
        });
        return rootView;
    }
}

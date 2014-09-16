package com.example.mwismer.lab1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


/**
 * Created by mwismer on 9/15/14.
 */
public class DatabaseHandler {
    //Database Model
    private Database model;
    //Database
    private SQLiteDatabase database;
    //All Fields
    private String[] allColumns = {
            Database.CHAT_USERNAME,
            Database.CHAT_MESSAGE
    };
    //Public Constructor - create connection to Database
    public DatabaseHandler(Context context){
        model = new Database(context);
    }
    /**
     * Add
     */
    public void addChat(Chat chat) {
        ContentValues values = new ContentValues();
        values.put(Database.CHAT_USERNAME, chat.username);
        values.put(Database.CHAT_MESSAGE, chat.message);
        database.insertWithOnConflict(Database.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void addChats(ArrayList<Chat> chats) {
        for (Chat chat: chats) { addChat(chat); }
    }

    public void updateChat(Chat chat){
        ContentValues values = new ContentValues();
        values.put(Database.CHAT_USERNAME, chat.username);
        values.put(Database.CHAT_MESSAGE, chat.message);
        database.update(Database.TABLE_NAME, values, Database.CHAT_USERNAME + " like '%" + chat.username + "%'", null);
    }
    /**
     * Get
     */
    public ArrayList<Chat> getAllChats(){
        return sweepCursor(database.query(Database.TABLE_NAME, allColumns, null, null, null, null, null));
    }

    /**
     * Additional Helpers
     */
    //Sweep Through Cursor and return a List of Kitties
    private ArrayList<Chat> sweepCursor(Cursor cursor){
        ArrayList<Chat> chats = new ArrayList<Chat>();
        //Get to the beginning of the cursor
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            chats.add(new Chat(
                    cursor.getString(0),
                    cursor.getString(1)
            ));

            //Go on to the next Kitty
            cursor.moveToNext();
        }
        return chats;
    }
    //Get Writable Database - open the database
    public void open(){
        database = model.getWritableDatabase();
    }
}
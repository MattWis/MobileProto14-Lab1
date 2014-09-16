package com.example.mwismer.lab1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mwismer on 9/15/14.
 */
public class Database extends SQLiteOpenHelper {
    //Table Name
    public static final String TABLE_NAME = "chats";
    //Table Fields
    public static final String CHAT_USERNAME = "username";
    public static final String CHAT_MESSAGE = "message";
    //Database Info
    private static final String DATABASE_NAME = "KittyAppDatabase";
    private static final int DATABASE_VERSION = 1;
    // ModelDatabase creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "("
            + CHAT_USERNAME + " TEXT NOT NULL UNIQUE, "
            + CHAT_MESSAGE + " TEXT NOT NULL );";

    //Default Constructor
    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    //OnCreate Method - creates the ModelDatabase
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }
    @Override
    //OnUpgrade Method - upgrades ModelDatabase if applicable
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
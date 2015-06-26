package com.example.jchild.promunchkincounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Jonathan on 26/6/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "ProMunchkinDB";

    private static final String TABLE_PLAYER = "Players";

    private static final String KEY_ID = "p_id";
    private static final String KEY_NAME = "p_name";
    private static final String KEY_LEVEL = "p_level";
    private static final String KEY_EQUIPMENT = "p_equipment";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PLAYER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_LEVEL + " TEXT," + KEY_EQUIPMENT + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);

        // Create tables again
        onCreate(db);
    }
    void endGame(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
    }


    void addPlayer(player players) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, players.getID());
        values.put(KEY_NAME, players.getName());
        values.put(KEY_LEVEL, players.getlvl());
        values.put(KEY_EQUIPMENT,players.getEquip());

        // Inserting Row
        db.insert(TABLE_PLAYER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[]{KEY_ID, KEY_NAME, KEY_LEVEL, KEY_EQUIPMENT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        player players = new player(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));

        return players;
    }

    // Getting All Contacts
    public ArrayList<player> getAllContacts() {
        ArrayList<player> playerList = new ArrayList<player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                player players = new player(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));
                playerList.add(players);
            } while (cursor.moveToNext());
        }
        return playerList;
    }

    public int updateContact(player players) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, players.getName());
        values.put(KEY_LEVEL, players.getlvl());
        values.put(KEY_EQUIPMENT, players.getEquip());

        // updating row
        return db.update(TABLE_PLAYER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(players.getID())});
    }

    public void deleteContact(player players) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_ID + " = ?", new String[]{String.valueOf(players.getID())});
        db.close();
    }


    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLAYER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
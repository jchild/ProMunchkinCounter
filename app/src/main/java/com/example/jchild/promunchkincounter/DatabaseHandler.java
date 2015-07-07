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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProMunchkinDB";
    private static final String TABLE_PLAYER = "Player";
    private static final String KEY_ID = "p_id";
    private static final String KEY_NAME = "p_name";
    private static final String KEY_LEVEL = "p_level";
    private static final String KEY_EQUIPMENT = "p_equipment";
    private static final String KEY_ELF = "p_elf";
    private static final String KEY_WARRIOR = "p_warrior";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + "Player" + " ( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_LEVEL + " TEXT, " + KEY_EQUIPMENT + " TEXT, "+ KEY_ELF + " TEXT, "+ KEY_WARRIOR + " TEXT" + " )";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);

        // Create tables again
        onCreate(db);
        db.close();
    }

    public void endGame(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
        db.close();
    }

    void addPlayer(player players) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, players.getID());
        values.put(KEY_NAME, players.getName());
        values.put(KEY_LEVEL, players.getlvl());
        values.put(KEY_EQUIPMENT,players.getEquip());
        values.put(KEY_ELF,String.valueOf(players.isElf()));
        values.put(KEY_WARRIOR,String.valueOf(players.isWarrior()));

        // Inserting Row
        db.insert(TABLE_PLAYER, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLAYER, new String[]{KEY_ID, KEY_NAME, KEY_LEVEL, KEY_EQUIPMENT, KEY_ELF, KEY_WARRIOR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        player players = new player(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),Boolean.valueOf(cursor.getString(4)),Boolean.valueOf(cursor.getString(5)));
        db.close();
        return players;
    }

    // Getting All Contacts
    public ArrayList<player> getAllPlayers() {
        ArrayList<player> playerList = new ArrayList<player>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                player players = new player(Integer.parseInt(cursor.getString(0)),cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),Boolean.valueOf(cursor.getString(4)),Boolean.valueOf(cursor.getString(5)));
                playerList.add(players);
            } while (cursor.moveToNext());
        }
        db.close();
        return playerList;
    }

    public void updatePlayer(player players) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, players.getName());
        values.put(KEY_LEVEL, players.getlvl());
        values.put(KEY_EQUIPMENT, players.getEquip());
        values.put(KEY_ELF, String.valueOf(players.isElf()));
        values.put(KEY_WARRIOR, String.valueOf(players.isWarrior()));

        // updating row
        db.update(TABLE_PLAYER, values, KEY_ID + " = ?", new String[]{String.valueOf(players.getID())});
        db.close();
    }

    public void deletePlayer(player players) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_ID + " = ?", new String[]{String.valueOf(players.getID())});
        db.close();
    }


    public int getPlayersCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLAYER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int num = cursor.getCount();
        cursor.close();

        // return count
        return num;
    }
}
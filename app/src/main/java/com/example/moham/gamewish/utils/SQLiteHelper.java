package com.example.moham.gamewish.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mozeeb on 17/03/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context) {
        super(context, "wishlist.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE GAME(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DEVELOPER TEXT, GENRE TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS GAME");
        onCreate(db);
    }

    public boolean addData(String name, String dev, String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DEVELOPER", dev);
        contentValues.put("GENRE", genre);

        long result = db.insert("GAME", null, contentValues);

        //return -1 if data is not entered into ll the fields
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM GAME", null);
        return data;
    }

   /* public Cursor getItemId(String name, String dev, String genre) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT ID FROM GAME WHERE NAME = '" + name + "' AND DEVELOPER = '"
                + dev + "' AND GENRE = '" + genre + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/

    public void updateData(String name, String dev, String genre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("DEVELOPER", dev);
        contentValues.put("GENRE", genre);
        db.update("GAME", contentValues, "NAME = ?", new String [] {name});
    }

    public int deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("GAME", "NAME=?", new String[] {name});
    }
}
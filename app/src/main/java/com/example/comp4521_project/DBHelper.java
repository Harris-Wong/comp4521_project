package com.example.comp4521_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "storage.db";

    public DBHelper(Context context) {
        super(context, DBHelper.DBNAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(id INTEGER primary key, username TEXT, password TEXT, friends TEXT)");
        db.execSQL("create Table bills(id INTEGER primary key, data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists users");
        db.execSQL("drop Table if exists bills");
        onCreate(db);
    }

    public Boolean insertUser(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = sqLiteDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public String[] getFriends(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT friends FROM users WHERE username=?", new String[]{username});
        if (cursor.moveToFirst()) {
            String friendsJSON = cursor.getString(0);
            if (friendsJSON != null) {
                try {
                    JSONArray friendsArray = new JSONArray(friendsJSON);
                    String[] friends = new String[friendsArray.length()];
                    for (int i = 0; i < friendsArray.length(); i++) {
                        friends[i] = friendsArray.getString(i);
                    }
                    return friends;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return new String[0];
                }
            }
        }
        return new String[0];
    }

    public Integer countFriends(String username) {
        return getFriends(username).length;
    }

    public Boolean updateFriends(String username, String[] friends) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("friends", new JSONArray(Arrays.asList(friends)).toString());
        int rowsAffected = db.update("users", values, "username=?", new String[]{username});
        return rowsAffected > 0;
    }

    public Boolean insertBill(String data) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", data);
        long result = sqLiteDatabase.insert("users", null, contentValues);
        return result != -1;
    }

    public Boolean updateBill(int id, String newData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("data", newData);
        int rowsAffected = db.update("bills", values, "id=?", new String[]{Integer.toString(id)});
        return rowsAffected > 0;
    }

    public Boolean isUserExisted(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public Boolean isCredentialsMatched(String username, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}

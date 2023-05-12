package com.example.comp4521_project;

import android.app.Application;

public class MyApplication extends Application {
    private User user;
    private DBHelper DB;

    @Override
    public void onCreate() {
        super.onCreate();
        user = User.getInstance();
        DB = new DBHelper(this);
    }

    public User getUser() {
        return this.user;
    }

    public DBHelper getDB() {
        return this.DB;
    }
}

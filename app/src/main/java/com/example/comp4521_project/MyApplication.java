package com.example.comp4521_project;

import android.app.Application;

public class MyApplication extends Application {
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        user = User.getInstance();
    }

    public User getUser() {
        return this.user;
    }
}

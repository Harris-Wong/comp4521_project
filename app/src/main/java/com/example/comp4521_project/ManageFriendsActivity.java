package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageFriendsActivity extends AppCompatActivity {

    Button btnAddFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        btnAddFriends = (Button) findViewById(R.id.btn_add_friends);

        btnAddFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Todo: Add Friend + Update Friend List + Friend Number + 1
            }
        });

    }
}
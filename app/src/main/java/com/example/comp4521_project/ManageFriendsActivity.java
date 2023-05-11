package com.example.comp4521_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ManageFriendsActivity extends AppCompatActivity {

    EditText etFriendName;
    Button btnAddFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        btnAddFriend = (Button) findViewById(R.id.btn_add_friend);
        etFriendName = findViewById(R.id.et_friend_name);

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Todo: Check if the name exist in our database
//                  if yes -> Add Friend to Friend List + Friend Number + 1
//                  if no -> Toast.makeText(getApplicationContext(), "Non-exist User", Toast.LENGTH_SHORT).show();
                String friendName = etFriendName.getText().toString();
            }
        });
    }
}
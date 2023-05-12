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
    DBHelper DB;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        btnAddFriend = (Button) findViewById(R.id.btn_add_friend);
        etFriendName = findViewById(R.id.et_friend_name);
        DB = ((MyApplication) getApplication()).getDB();
        username = ((MyApplication) getApplication()).getUser().getUsername();

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFriend = etFriendName.getText().toString();
                if (DB.isUserExisted(newFriend)) {
                    String [] friendList = DB.getFriends(username);
                    String[] newFriendList = new String[friendList.length + 1];
                    System.arraycopy(friendList, 0, newFriendList, 0, friendList.length);
                    newFriendList[newFriendList.length - 1] = newFriend;
                    DB.updateFriends(username, newFriendList);
                } else {
                    Toast.makeText(getApplicationContext(), "Friend " + newFriend + " doesn't exist. ", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
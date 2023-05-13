package com.example.comp4521_project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp4521_project.DBHelper;
import com.example.comp4521_project.MyApplication;
import com.example.comp4521_project.friend_list_view.FriendAdapter;
import com.example.comp4521_project.friend_list_view.FriendItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManageFriendsActivity extends AppCompatActivity {

    EditText etFriendName;
    Button btnAddFriend;
    DBHelper DB;
    String username;
    ListView listView;
    FriendAdapter adapter;
    List<FriendItem> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        btnAddFriend = findViewById(R.id.btn_add_friend);
        etFriendName = findViewById(R.id.et_friend_name);
        DB = ((MyApplication) getApplication()).getDB();
        username = ((MyApplication) getApplication()).getUser().getUsername();

        friends = new ArrayList<>();

        // Todo: Get Friends and their Corresponding Debt

        friends.add(new FriendItem("Alice", "Owe me HKD 100"));
        friends.add(new FriendItem("Bob", "Owe me HKD 200"));
        friends.add(new FriendItem("Charlie", "Owe me HKD 10"));
        friends.add(new FriendItem("Dave", "Owe me HKD 10000"));

        adapter = new FriendAdapter(this, friends);
        listView = findViewById(R.id.friend_list_view);
        listView.setAdapter(adapter);

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newFriend = etFriendName.getText().toString();
                if (newFriend.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Friend's name can't be empty. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (DB.isUserExisted(newFriend)) {
                    String [] friendList = DB.getFriends(username);
                    Set<String> uniqueFriends = new HashSet<>(Arrays.asList(friendList));
                    uniqueFriends.add(newFriend);
                    DB.updateFriends(username, uniqueFriends.toArray(new String[uniqueFriends.size()]));
                    etFriendName.setText("");
                    Toast.makeText(getApplicationContext(), newFriend + " is added. ", Toast.LENGTH_SHORT).show();

                    // Add the new friend to the list and update the adapter
                    FriendItem newFriendItem = new FriendItem(newFriend, "No debts");
                    friends.add(newFriendItem);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Friend " + newFriend + " doesn't exist. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

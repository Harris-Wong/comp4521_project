package com.example.comp4521_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.comp4521_project.friend_list_view.FriendAdapter;
import com.example.comp4521_project.friend_list_view.FriendItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManageFriendsActivity extends AppCompatActivity {

    EditText etFriendName;
    TextView tvManageFriendCount;
    String currency;
    Button btnAddFriend;
    DBHelper DB;
    String username;
    ListView listView;
    FriendAdapter adapter;
    List<FriendItem> friendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_friends);

        btnAddFriend = findViewById(R.id.btn_add_friend);
        etFriendName = findViewById(R.id.et_friend_name);
        tvManageFriendCount = findViewById(R.id.tv_manage_friend_count);

        DB = ((MyApplication) getApplication()).getDB();
        username = ((MyApplication) getApplication()).getUser().getUsername();

        friendList = new ArrayList<>();

        SharedPreferences sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        currency = sharedPref.getString(getString(R.string.text_currency), "HKD");
        String[] friends = DB.getFriends(username);
        tvManageFriendCount.setText(String.valueOf(friends.length));
        Bill[] bills = DB.getBills();
        for (String friendName : friends) {
            Double netDebt = 0.0;
            for (int i = 0; i < bills.length; ++i) {
                netDebt += bills[i].getDebtBetween(username, friendName);
            }

            String debtText = "";
            if (netDebt < 0.0) {
                debtText = "Lent me ";
            } else if (netDebt > 0.0) {
                debtText = "Borrowed ";
            } else {
                debtText = "Settled ";
            }
            debtText += (currency + CurrencyConverter.hkdTo(currency, Math.abs(netDebt))).toString();
            friendList.add(new FriendItem(friendName, debtText));
        }

        adapter = new FriendAdapter(this, friendList);
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

                    // Add the new friend to the list and update the adapter
                    FriendItem newFriendItem = new FriendItem(newFriend, "Settled " + currency + "0.0");
                    ManageFriendsActivity.this.friendList.add(newFriendItem);
                    adapter.notifyDataSetChanged();

                    ((TextView) findViewById(R.id.tv_manage_friend_count)).setText(String.valueOf(DB.getFriends(username).length));
                    Toast.makeText(getApplicationContext(), newFriend + " is added. ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Friend " + newFriend + " doesn't exist. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

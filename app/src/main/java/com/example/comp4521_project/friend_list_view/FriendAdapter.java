package com.example.comp4521_project.friend_list_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.comp4521_project.R;

import java.util.List;

public class FriendAdapter extends ArrayAdapter<FriendItem> {

    Context context;

    List<FriendItem> items;

    public FriendAdapter(Context context, List<FriendItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.friend_list_item, parent, false);
        }

        FriendItem currentFriend = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.tv_friend_name);
        nameTextView.setText(currentFriend.getFriendName());

        TextView hobbyTextView = listItemView.findViewById(R.id.tv_owe_me);
        hobbyTextView.setText(currentFriend.getOweMe());

        return listItemView;
    }
}
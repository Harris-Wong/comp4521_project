package com.example.comp4521_project.friend_selection_view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.comp4521_project.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriendSelectionAdapter extends ArrayAdapter<String> {

    private List<String> friendItems;
    private HashMap<String, String> friendDebt;

    public FriendSelectionAdapter(Context context, List<String> friendItems) {
        super(context, 0, friendItems);
        this.friendItems = friendItems;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.friend_selection_item, parent, false);
        }

        friendDebt = new HashMap<>();
        TextView tvFriend = view.findViewById(R.id.tv_friend);
        EditText etDebt = view.findViewById(R.id.et_debt);

        String item = getItem(position);
        tvFriend.setText(item);

        // Set the tag of the EditText to the position so we can retrieve it later
        etDebt.setTag(position);

        //   Set an OnFocusChangeListener to the EditText to update the item in the list when the focus is lost
        etDebt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                int position = (int) etDebt.getTag();
                if (!hasFocus) {
                    String debt = etDebt.getText().toString();
                    if (debt != null && !debt.isEmpty()) {
                        debt = debt.length() == 0 ? "0.0" : debt;
                    }
                    friendDebt.put(friendItems.get(position), debt);
                }
            }
        });

        etDebt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int position = (int) etDebt.getTag();

                // Update the item in the list with the new debt value
                String debt = s.toString();
                if (debt != null && !debt.isEmpty()) {
                    debt = debt.length() == 0 ? "0.0" : debt;
                }
                friendDebt.put(friendItems.get(position), debt);
            }
        });

        return view;
    }

    public List<FriendSelectionItem> getFriendSelectionItems() {
        List<FriendSelectionItem> friendSelectionItems = new ArrayList<>();
        for (int i = 0; i < friendItems.size(); i++) {
            String friendName = friendItems.get(i);
            friendSelectionItems.add(new FriendSelectionItem(friendName, friendDebt.getOrDefault(friendName, "0.0")));
        }
        return friendSelectionItems;
    }
}
package com.example.comp4521_project.friend_selection_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.comp4521_project.R;

import java.util.List;

public class FriendSelectionAdapter extends ArrayAdapter<String> {

    private List<String> items;

    public FriendSelectionAdapter(Context context, List<String> items) {
        super(context, 0, items);
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.friend_selection_item, parent, false);
        }

        TextView tvFriend = view.findViewById(R.id.tv_friend);
        EditText etDebt = view.findViewById(R.id.et_debt);

        String item = getItem(position);
        tvFriend.setText(item);

        // Set the tag of the EditText to the position so we can retrieve it later
        etDebt.setTag(position);

        // Set an OnFocusChangeListener to the EditText to update the item in the list when the focus is lost
        etDebt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Get the position of the EditText from the tag
                    int position = (int) v.getTag();

                    // Update the item in the list with the new debt value
                    String debt = etDebt.getText().toString();
                    items.set(position, items.get(position) + " (" + debt + ")");
                }
            }
        });

        return view;
    }

    public void updateItems(List<String> newItems) {
        items.clear();
        items.addAll(newItems);
        notifyDataSetChanged();
    }
}
package com.example.comp4521_project.friend_selection_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
public class FriendSelectionItem {
    private String friendName;
    private String debt;

    public FriendSelectionItem(String friend, String debt) {
        this.friendName = friend;
        this.debt = debt;
    }

    public String getFriend() {
        return friendName;
    }

    public String getDebt() {
        return debt;
    }
}
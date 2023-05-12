package com.example.comp4521_project;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView title, people, splitMode, total, paidBy, howLongAgo;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.tv_title);
        people = itemView.findViewById(R.id.tv_people);
        splitMode = itemView.findViewById(R.id.tv_split_mode);
        total = itemView.findViewById(R.id.tv_total);
        paidBy = itemView.findViewById(R.id.tv_paid_by);
        howLongAgo = itemView.findViewById(R.id.tv_how_long_ago);
    }
}

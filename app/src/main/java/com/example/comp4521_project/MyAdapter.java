package com.example.comp4521_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.people.setText(items.get(position).getPeople());
        holder.splitMode.setText(items.get(position).getSplitMode());
        holder.total.setText(items.get(position).getTotal());
        holder.paidBy.setText(items.get(position).getPaidBy());
        holder.howLongAgo.setText(items.get(position).getHowLongAgo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

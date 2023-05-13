package com.example.comp4521_project.bill_recycler_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comp4521_project.R;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillViewHolder> {

    Context context;
    List<BillItem> items;

    public BillAdapter(Context context, List<BillItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillViewHolder(LayoutInflater.from(context).inflate(R.layout.bill_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, int position) {
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

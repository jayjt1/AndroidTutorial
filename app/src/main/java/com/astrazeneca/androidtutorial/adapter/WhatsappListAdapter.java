package com.astrazeneca.androidtutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.astrazeneca.androidtutorial.R;

import java.util.List;

public class WhatsappListAdapter extends RecyclerView.Adapter<MyListViewHolder> {

    private List<String> items;
    Context mContext;

    public WhatsappListAdapter(Context mContext, List<String> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public MyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_whatsapp_cell_list, parent, false);

        MyListViewHolder myViewHolder = new MyListViewHolder(itemView);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyListViewHolder holder, int position) {

        holder.imgIcon.setImageResource(R.drawable.user);
        holder.txtTitle.setText(items.get(position));
        holder.txtDesc.setText("Welcome to Android");
        holder.txtTime.setText("3.15 PM");
        holder.txtNotiCount.setText("10");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
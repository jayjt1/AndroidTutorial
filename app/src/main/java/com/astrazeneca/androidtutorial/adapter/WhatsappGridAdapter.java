package com.astrazeneca.androidtutorial.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.astrazeneca.androidtutorial.R;

import java.util.List;

public class WhatsappGridAdapter extends RecyclerView.Adapter<MyGridViewHolder> {

    private List<String> items;
    Context mContext;

    public WhatsappGridAdapter(Context mContext, List<String> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public MyGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_whatsapp_cell_grid, parent, false);

        MyGridViewHolder myGridHolder = new MyGridViewHolder(itemView);

        return myGridHolder;
    }

    @Override
    public void onBindViewHolder(final MyGridViewHolder holder, int position) {

        holder.imgIcon.setImageResource(R.drawable.user);
        holder.txtTitle.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
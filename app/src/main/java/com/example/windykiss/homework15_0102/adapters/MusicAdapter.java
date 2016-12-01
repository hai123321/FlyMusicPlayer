package com.example.windykiss.homework15_0102.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.managers.DBContext;
import com.example.windykiss.homework15_0102.view_holder.MusicViewHolder;

/**
 * Created by WindyKiss on 10/25/2016.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {


    @Override
    public MusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 1 Inflate View
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_musics, parent, false);

        // 2 Create View Holder
        MusicViewHolder musicViewHolder = new MusicViewHolder(itemView);

        return musicViewHolder;
    }

    @Override
    public void onBindViewHolder(MusicViewHolder holder, int position) {
        holder.bind(DBContext.getInstance().findAllMusic().get(position));
    }

    @Override
    public int getItemCount() {
        return DBContext.getInstance().findAllMusic().size();
    }
}
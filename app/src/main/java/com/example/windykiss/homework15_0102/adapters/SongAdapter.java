package com.example.windykiss.homework15_0102.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.managers.DBContext;
import com.example.windykiss.homework15_0102.models.Song;
import com.example.windykiss.homework15_0102.view_holder.MusicViewHolder;
import com.example.windykiss.homework15_0102.view_holder.SongViewHolder;

import java.util.ArrayList;

/**
 * Created by WindyKiss on 10/25/2016.
 */

public class SongAdapter extends RecyclerView.Adapter<SongViewHolder>{

    ArrayList<Song> songArrayList;

    public SongAdapter(ArrayList<Song> songArrayList) {
        this.songArrayList = songArrayList;
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_songs, parent, false);

        // 2 Create View Holder
        SongViewHolder songViewHolder = new SongViewHolder(itemView);

        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.bind(songArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }
}
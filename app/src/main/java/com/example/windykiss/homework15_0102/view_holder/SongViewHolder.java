package com.example.windykiss.homework15_0102.view_holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.models.MusicRealm;
import com.example.windykiss.homework15_0102.models.Song;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WindyKiss on 10/25/2016.
 */

public class SongViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = SongViewHolder.class.getSimpleName();
    @BindView(R.id.iv_song)
    ImageView iv;

    @BindView(R.id.tv_song_name)
    TextView tv;

    @BindView(R.id.tv_song_artist)
    TextView tv_artist;


    public SongViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Song song) {
        tv.setText(song.getName());
        tv_artist.setText(song.getArtist());
        ImageLoader imageLoader = ImageLoader.getInstance();
        Log.d(TAG, song.getImgLink());
        imageLoader.displayImage(song.getImgLink(), iv, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Log.d(TAG, "onLoadingStarted");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                Log.d(TAG, "onLoadingFailed");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                Log.d(TAG, "onLoadingComplete");
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                Log.d(TAG, "onLoadingCancelled");
            }
        });
    }

}

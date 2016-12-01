package com.example.windykiss.homework15_0102.view_holder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.models.MusicRealm;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WindyKiss on 10/25/2016.
 */

public class MusicViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_fr)
    ImageView iv;

    @BindView(R.id.tv_fr)
    TextView tv;

    private Context context;

    public MusicViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void bind(MusicRealm musicRealm) {
        try {
            InputStream stream;
            if (musicRealm.getId().equals("")) {
                stream = context.getAssets().open("images/0.png");
            } else {
                stream = context.getAssets().open("images/" + musicRealm.getId() + ".png");
            }
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText(musicRealm.getName());
    }

}

package com.example.windykiss.homework15_0102.fragments;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windykiss.homework15_0102.R;
import com.example.windykiss.homework15_0102.adapters.SongAdapter;
import com.example.windykiss.homework15_0102.constant.Constant;
import com.example.windykiss.homework15_0102.managers.DBContext;
import com.example.windykiss.homework15_0102.models.MusicRealm;
import com.example.windykiss.homework15_0102.models.Song;
import com.example.windykiss.homework15_0102.models.SongListJsonModel;
import com.example.windykiss.homework15_0102.services.MusicService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    private static final String TAG = SongFragment.class.getSimpleName();
    private String idPos;

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_share)
    ImageView iv_share;
    @BindView(R.id.iv_search)
    ImageView iv_search;
    @BindView(R.id.iv_love)
    ImageView iv_love;
    @BindView(R.id.iv_love_show)
    ImageView iv_love_show;

    @BindView(R.id.iv_img)
    ImageView iv_img;

    @BindView(R.id.iv_play)
    ImageView iv_play;

    @BindView(R.id.tv_music_name)
    TextView tv_musicName;

    @BindView(R.id.rv_songs)
    RecyclerView rv_songs;

    DBContext dbContext;

    public SongFragment() {
        // Required empty public constructor
    }

    public void setIdPos(String idPos) {
        this.idPos = idPos;
    }

    public String getIdPos() {
        return idPos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_song, container, false);
        ButterKnife.bind(this, v);
        dbContext = DBContext.getInstance();
        setupUI();
        addListeners();
        return v;
    }

    private void addListeners() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        iv_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbContext.findByID(idPos).isLove()){
                    iv_love_show.setVisibility(View.INVISIBLE);
                } else {
                    iv_love_show.setVisibility(View.VISIBLE);
                }
                dbContext.update(idPos);
            }
        });
    }

    private void setupUI() {
        if(dbContext.findByID(idPos).isLove()){
            iv_love_show.setVisibility(View.VISIBLE);
        } else {
            iv_love_show.setVisibility(View.INVISIBLE);
        }

        iv_play.setY(iv_img.getHeight() - iv_play.getHeight() / 2);
        GridLayoutManager manager = new GridLayoutManager(
                getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        rv_songs.setLayoutManager(manager);


        MusicRealm tmpMusic = dbContext.findByID(idPos);
        String id = tmpMusic.getId();
        Log.d(TAG, id);
        try {
            InputStream stream;
            if (id.equals("")) {
                stream = getActivity().getAssets().open("images/0.png");
            } else {
                stream = getActivity().getAssets().open("images/" + id + ".png");
            }
            Drawable drawable = Drawable.createFromStream(stream, null);
            iv_img.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tv_musicName.setText(tmpMusic.getName());

        Retrofit mediaRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.SONG_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MusicService musicService = mediaRetrofit.create(MusicService.class);
        musicService.getSongList(id).enqueue(new Callback<SongListJsonModel>() {
            @Override
            public void onResponse(Call<SongListJsonModel> call, Response<SongListJsonModel> response) {
                ArrayList<Song> songs = new ArrayList<>();
                SongListJsonModel songListJsonModel = response.body();
                for (SongListJsonModel.Feed.Entry entry : songListJsonModel.getFeed().getEntry()) {
                    for (SongListJsonModel.Feed.Entry.Img image : entry.getImg()) {
                        if (image.getAttribute().getHeight().equals("60")) {
                            songs.add(new Song(
                                    entry.getSongName().getName(),
                                    entry.getArtist().getName(),
                                    image.getImgLink()
                            ));
                            break;
                        }
                    }
                }
                final SongAdapter songAdapter = new SongAdapter(songs);
                rv_songs.setAdapter(songAdapter);
                Log.d(TAG, "Size: "+songAdapter.getItemCount());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        songAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(Call<SongListJsonModel> call, Throwable t) {

            }
        });
    }

}

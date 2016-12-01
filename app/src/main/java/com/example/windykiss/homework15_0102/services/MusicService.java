package com.example.windykiss.homework15_0102.services;

import com.example.windykiss.homework15_0102.models.MusicJsonModel;
import com.example.windykiss.homework15_0102.models.SongListJsonModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by WindyKiss on 11/30/2016.
 */

public interface MusicService {
    @GET("/data/media-types.json")
    Call<List<MusicJsonModel>> getMusicList();

    @GET("/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<SongListJsonModel> getSongList(@Path("id") String id);
}

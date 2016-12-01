package com.example.windykiss.homework15_0102;

import android.app.Application;
import android.util.Log;

import com.example.windykiss.homework15_0102.constant.Constant;
import com.example.windykiss.homework15_0102.managers.DBContext;
import com.example.windykiss.homework15_0102.managers.NetworkManager;
import com.example.windykiss.homework15_0102.models.MusicJsonModel;
import com.example.windykiss.homework15_0102.models.MusicRealm;
import com.example.windykiss.homework15_0102.services.MusicService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by WindyKiss on 11/30/2016.
 */

public class StartApp extends Application {
    private static final String TAG = StartApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        settingThingsUp();
    }

    private void settingThingsUp() {
        NetworkManager.init(this);
        DBContext.init(this);

        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());

        if (NetworkManager.getInstance().isConnectedToInternet()) {

            if (DBContext.getInstance().findAllMusic().isEmpty()) {
                goMediaType();
            }
        }

    }

    private void goMediaType() {
        Retrofit mediaRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.MUSIC_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MusicService musicService = mediaRetrofit.create(MusicService.class);
        musicService.getMusicList().enqueue(new Callback<List<MusicJsonModel>>() {

            @Override
            public void onResponse(Call<List<MusicJsonModel>> call, Response<List<MusicJsonModel>> response) {
                Log.d(TAG, "onResponse");
                for (MusicJsonModel musicJsonModel : response.body()) {
                    if (musicJsonModel.getId().equals("34")) {
                        for (MusicJsonModel.SubGenre subGenre : musicJsonModel.getSubgenres()) {
                            DBContext.getInstance().add(
                                    MusicRealm.create(subGenre.getId(), subGenre.getTranslation_key().toUpperCase())
                            );
                        }
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MusicJsonModel>> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}

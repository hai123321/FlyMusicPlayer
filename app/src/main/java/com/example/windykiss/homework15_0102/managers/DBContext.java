package com.example.windykiss.homework15_0102.managers;

import android.content.Context;


import com.example.windykiss.homework15_0102.models.MusicRealm;

import java.util.List;
import java.util.Random;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by WindyKiss on 11/2/2016.
 */

public class DBContext {
    private Realm realm;

    public DBContext(Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    private static DBContext instance;

    public static DBContext getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DBContext(context);
        }
    }

    public Realm getRealm() {
        return realm;
    }

    public void add(MusicRealm musicRealm) {
        beginTransaction();
        realm.copyToRealm(musicRealm);
        commitTransaction();
    }

    public MusicRealm findByID(String id) {
        //3Query
        MusicRealm musicRealm = realm
                .where(MusicRealm.class)
                .equalTo("id", id, Case.INSENSITIVE)
                .findFirst();
        //.findFirst to find 1st person.
        return musicRealm;
    }


    public void update(String id){
        beginTransaction();
        findByID(id).setLove(!findByID(id).isLove());
        commitTransaction();
    }

    public List<MusicRealm> findAllMusic() {
        RealmResults<MusicRealm> musics = realm
                .where(MusicRealm.class)
                .findAll();
        return musics;
    }

    public List<MusicRealm> findAllMusicLove() {
        RealmResults<MusicRealm> musics = realm
                .where(MusicRealm.class)
                .equalTo("love", true)
                .findAll();
        return musics;
    }

    public MusicRealm findOne(int position){
        return findAllMusic().get(position);
    }


    public void beginTransaction() {
        getRealm().beginTransaction();
    }

    public void commitTransaction() {
        getRealm().commitTransaction();
    }

    public void deleteAll(Class<? extends RealmModel> clazz) {
        beginTransaction();
        realm.delete(clazz);
        commitTransaction();
    }

    public int getNextKey()
    {
        return realm.where(MusicRealm.class).max("id").intValue() + 1;
    }
}

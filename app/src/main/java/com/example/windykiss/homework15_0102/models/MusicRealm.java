package com.example.windykiss.homework15_0102.models;

import io.realm.RealmObject;

/**
 * Created by WindyKiss on 11/29/2016.
 */

public class MusicRealm extends RealmObject{
    private String id;
    private String name;
    private boolean love;

    public MusicRealm() {
    }

    public static MusicRealm create(String id, String name) {
        MusicRealm media = new MusicRealm();
        media.id = id;
        media.name = name;
        media.love = false;
        return media;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }
}

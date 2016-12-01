package com.example.windykiss.homework15_0102.models;

/**
 * Created by WindyKiss on 12/1/2016.
 */

public class Song {
    private String name;
    private String artist;
    private String imgLink;

    public Song(String name, String artist, String imgLink) {
        this.name = name;
        this.artist = artist;
        this.imgLink = imgLink;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getImgLink() {
        return imgLink;
    }
}

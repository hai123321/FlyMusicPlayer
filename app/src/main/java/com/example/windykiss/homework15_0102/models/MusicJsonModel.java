package com.example.windykiss.homework15_0102.models;

import java.util.ArrayList;

/**
 * Created by WindyKiss on 11/30/2016.
 */

public class MusicJsonModel {
    private String id;
    private ArrayList<SubGenre> subgenres;
    public class SubGenre{
        private String id;
        private String translation_key;

        public String getId() {
            return id;
        }

        public String getTranslation_key() {
            return translation_key;
        }
    }

    public String getId() {
        return id;
    }

    public ArrayList<SubGenre> getSubgenres() {
        return subgenres;
    }
}

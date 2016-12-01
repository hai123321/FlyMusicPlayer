package com.example.windykiss.homework15_0102.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by WindyKiss on 12/1/2016.
 */

public class SongListJsonModel {
    @SerializedName("feed")
    private Feed feed;


    public class Feed {
        @SerializedName("entry")
        private ArrayList<Entry> entry;


        public class Entry {
            @SerializedName("im:name")
            private Name songName;

            public Name getSongName() {
                return songName;
            }

            @SerializedName("im:artist")
            private Artist artist;

            public class Artist {
                @SerializedName("label")
                private String name;

                public String getName() {
                    return name;
                }
            }

            public Artist getArtist() {
                return artist;
            }

            public ArrayList<Img> getImg() {
                return img;
            }

            public class Name {
                @SerializedName("label")
                private String name;

                public String getName() {
                    return name;
                }
            }

            @SerializedName("im:image")
            private ArrayList<Img> img;

            public class Img {
                @SerializedName("label")
                private String imgLink;

                @SerializedName("attributes")
                private Attribute attribute;

                public class Attribute {
                    @SerializedName("height")
                    private String height;

                    public String getHeight() {
                        return height;
                    }
                }

                public Attribute getAttribute() {
                    return attribute;
                }

                public String getImgLink() {
                    return imgLink;
                }
            }
        }

        public ArrayList<Entry> getEntry() {
            return entry;
        }
    }

    public Feed getFeed() {
        return feed;
    }
}

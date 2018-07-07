package com.example.administrator.mymapapp;

public class Map {
    public Map(double lat, double lang) {
        this.lang=lang;
        this.lat=lat;
    }

    public double getLat() {
        return lat;
    }
    public Map()
    {

    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    double lat,lang;
}
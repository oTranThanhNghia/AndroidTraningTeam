package com.framgiatranthanhnghia.androidtrainingteam.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ngh on 8/27/2015.
 */
public class CurrentWeatherData {
    public List<WeatherData> weather;
    public MainWeatherData main;
    public Wind wind;
    public SystemData sys;
}

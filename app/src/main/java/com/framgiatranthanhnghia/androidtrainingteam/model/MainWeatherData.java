package com.framgiatranthanhnghia.androidtrainingteam.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ngh on 8/27/2015.
 */
public class MainWeatherData {
    public double temp;
    public double pressure;
    public double humidity;
    @SerializedName("temp_min")
    public double tempMin;
    @SerializedName("temp_max")
    public double tempMax;
//    public double sea_level;
//    public double grnd_level;
}

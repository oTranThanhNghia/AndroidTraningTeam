package com.framgiatranthanhnghia.androidtrainingteam.api;

import com.framgiatranthanhnghia.androidtrainingteam.settings.Config;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class WeatherAPI {
    private static final String BASE_URL="http://api.openweathermap.org/data/2.5/weather";
    private static String paramUrl(String api,Object... param){
        return String.format(BASE_URL+api,param);
    }

    public static String getWeatherDataByGeo(double lat,double lon){
        return paramUrl("?lat=%f&lon=%f&APPID=%s",lat,lon, Config.APPID_WEATHER);
    }
}

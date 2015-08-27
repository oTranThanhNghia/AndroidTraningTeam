package com.framgiatranthanhnghia.androidtrainingteam.api;

import com.framgiatranthanhnghia.androidtrainingteam.settings.Config;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class WeatherAPI {
    private static final String BASE_URL="http://api.openweathermap.org";
    private static String paramUrl(String api,Object... param){
        return String.format(BASE_URL+api,param);
    }

    public static String getWeatherDataByGeo(double lat,double lon){
        return paramUrl("/data/2.5/weather?lat=%f&lon=%f&APPID=%s", lat, lon, Config.APPID_WEATHER);
    }

    //http://openweathermap.org/img/w/10d.png
    public static String getIconWeather(String nameImage){
        return paramUrl("/img/w/%s.png",nameImage);
    }

    //http://api.openweathermap.org/data/2.5/forecast/daily?lat=21.027764&lon=105.834160&cnt=16&mode=json
    /**
     * @param cnt number of days returned
     * */
    public static String getForecast(double lat,double lon,int cnt){
        return paramUrl("/data/2.5/forecast/daily?lat=%f&lon=%f&cnt=%d&mode=json&APPID=%s",lat,lon,cnt,Config.APPID_WEATHER);
    }
}

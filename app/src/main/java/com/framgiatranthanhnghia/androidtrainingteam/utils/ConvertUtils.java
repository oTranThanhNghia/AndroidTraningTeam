package com.framgiatranthanhnghia.androidtrainingteam.utils;

import java.util.Date;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 27/08/2015.
 */
public class ConvertUtils {
    public static final double KELVIN=273.15;
    public static int convertKelvinToCelsius(double kelvinTemp){
        return (int)(kelvinTemp-KELVIN+0.5);
    }

    public static Date convertUnixToDate(long timeUnix){
        return new Date(timeUnix*1000);
    }
}

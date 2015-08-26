package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;
import com.framgiatranthanhnghia.androidtrainingteam.api.LocationAPI;
import com.framgiatranthanhnghia.androidtrainingteam.api.WeatherAPI;
import com.framgiatranthanhnghia.androidtrainingteam.model.Geolocation;
import com.framgiatranthanhnghia.androidtrainingteam.request.VolleyRequestQueue;
import com.framgiatranthanhnghia.androidtrainingteam.settings.Config;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class CurrentWeatherFragment extends BaseFragment<MainActivity> {

    private final String TAG = "CurrentWeatherFragment";

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_current_weather, container, false);
        getGeolocation();
        return mRootView;
    }

    public void getGeolocation() {
        String url = LocationAPI.URL;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                try {
                    Geolocation geolocation = new Gson().fromJson(response.toString(), new TypeToken<Geolocation>() {
                    }.getType());
                    if(geolocation!=null) {
                        Log.i(TAG, "lat=" + geolocation.latitude + "--lon=" + geolocation.longitude);
                        Config.mLat=geolocation.latitude;
                        Config.mLon=geolocation.longitude;
                        getCurrentWeather();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        VolleyRequestQueue.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    public void getCurrentWeather() {
        String url = WeatherAPI.getWeatherDataByGeo(Config.mLat, Config.mLon);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG,response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.toString());
            }
        });
        VolleyRequestQueue.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

}

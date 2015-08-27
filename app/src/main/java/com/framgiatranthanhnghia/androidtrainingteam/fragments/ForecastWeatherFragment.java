package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;
import com.framgiatranthanhnghia.androidtrainingteam.adapter.ForecastAdapter;
import com.framgiatranthanhnghia.androidtrainingteam.api.LocationAPI;
import com.framgiatranthanhnghia.androidtrainingteam.api.WeatherAPI;
import com.framgiatranthanhnghia.androidtrainingteam.model.DayWeatherData;
import com.framgiatranthanhnghia.androidtrainingteam.model.Geolocation;
import com.framgiatranthanhnghia.androidtrainingteam.model.WeatherForeCastData;
import com.framgiatranthanhnghia.androidtrainingteam.request.VolleyRequestQueue;
import com.framgiatranthanhnghia.androidtrainingteam.settings.Config;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class ForecastWeatherFragment extends BaseFragment<MainActivity> {
    private final String TAG="ForecastWeatherFragment";
    private final int CNT=15;
    private View mRootView;
    private RecyclerView mForecastRecycler;
    private ForecastAdapter mAdapter;
    private List<DayWeatherData> mListForecast;

    private void init(){
        mForecastRecycler =(RecyclerView)mRootView.findViewById(R.id.weather_forecast_list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mForecastRecycler.setLayoutManager(layoutManager);
        mForecastRecycler.setHasFixedSize(true);
        mListForecast=new ArrayList<>();
        mAdapter=new ForecastAdapter(mListForecast);
        mForecastRecycler.setAdapter(mAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        init();
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
                    if (geolocation != null) {
                        Log.i(TAG, "lat=" + geolocation.latitude + "--lon=" + geolocation.longitude);
                        Config.mLat = geolocation.latitude;
                        Config.mLon = geolocation.longitude;
                        getListWeather();
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

    public void getListWeather(){
        String url= WeatherAPI.getForecast(Config.mLat,Config.mLon,CNT);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                try {
                    WeatherForeCastData weatherForeCastData=new Gson().fromJson(response.toString(),new TypeToken<WeatherForeCastData>(){}.getType());
                    if(weatherForeCastData!=null && weatherForeCastData.list!=null && weatherForeCastData.list.size()>0){
                        if(mListForecast.size()>0){
                            mListForecast.clear();
                        }
                        mListForecast.addAll(weatherForeCastData.list);
                        mAdapter.notifyDataSetChanged();
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
}

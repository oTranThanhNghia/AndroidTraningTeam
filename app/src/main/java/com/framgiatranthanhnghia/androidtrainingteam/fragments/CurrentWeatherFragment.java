package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;
import com.framgiatranthanhnghia.androidtrainingteam.api.LocationAPI;
import com.framgiatranthanhnghia.androidtrainingteam.api.WeatherAPI;
import com.framgiatranthanhnghia.androidtrainingteam.factory.StateFactory;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.ErrorStateLayout;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.LoadingStateLayout;
import com.framgiatranthanhnghia.androidtrainingteam.model.CurrentWeatherData;
import com.framgiatranthanhnghia.androidtrainingteam.model.Geolocation;
import com.framgiatranthanhnghia.androidtrainingteam.model.WeatherData;
import com.framgiatranthanhnghia.androidtrainingteam.request.VolleyRequestQueue;
import com.framgiatranthanhnghia.androidtrainingteam.settings.Config;
import com.framgiatranthanhnghia.androidtrainingteam.utils.ConvertUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.text.SimpleDateFormat;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class CurrentWeatherFragment extends BaseFragment<MainActivity> {

    private final String TAG = "CurrentWeatherFragment";

    private View mRootView;

    private TextView mTxtDateToday;
    private TextView mTxtWeather;
    private TextView mTxtTemp;
    private TextView mTxtHuminity;
    private TextView mTxtWindy;
    private TextView mTxtSunrise;
    private TextView mTxtSunset;
    private TextView mTxtHighTemp;
    private TextView mTxtLowTemp;

    private ImageView mImgWeather;

    private StateFactory mStateFactory;


    private void init() {
        mTxtDateToday = (TextView) mRootView.findViewById(R.id.txt_date_today);
        mTxtWeather = (TextView) mRootView.findViewById(R.id.txt_weather);
        mTxtTemp = (TextView) mRootView.findViewById(R.id.txt_temp);
        mTxtHuminity = (TextView) mRootView.findViewById(R.id.txt_humidity);
        mTxtWindy = (TextView) mRootView.findViewById(R.id.txt_windy);
        mTxtSunrise = (TextView) mRootView.findViewById(R.id.txt_sunrise);
        mTxtSunset = (TextView) mRootView.findViewById(R.id.txt_sunset);
        mTxtHighTemp = (TextView) mRootView.findViewById(R.id.txt_high_temp);
        mTxtLowTemp = (TextView) mRootView.findViewById(R.id.txt_low_temp);

        mImgWeather = (ImageView) mRootView.findViewById(R.id.img_weather);

        mStateFactory=new StateFactory((RelativeLayout)mRootView.findViewById(R.id.state_layout));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_current_weather, container, false);
        init();

        getGeolocation();
        return mRootView;
    }

    public void updateData(CurrentWeatherData data) {
        mTxtTemp.setText((data.main == null ? "" : ConvertUtils.convertKelvinToCelsius(data.main.temp)) + "");
        mTxtHuminity.setText((data.main == null ? "" : data.main.humidity) + getString(R.string.percent));
        mTxtHighTemp.setText((data.main == null ? "" : ConvertUtils.convertKelvinToCelsius(data.main.tempMax)) + getString(R.string.celsius));
        mTxtLowTemp.setText((data.main == null ? "" : ConvertUtils.convertKelvinToCelsius(data.main.tempMin)) + getString(R.string.celsius));
        mTxtWindy.setText((data.wind == null ? "" : data.wind.speed) + getString(R.string.miles_per_seccond));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        mTxtSunset.setText(data.sys == null ?
                "" : simpleDateFormat.format(ConvertUtils.convertUnixToDate(data.sys.sunset)));
        mTxtSunrise.setText(data.sys == null ?
                "" : simpleDateFormat.format(ConvertUtils.convertUnixToDate(data.sys.sunrise)));
        if(data.weather!=null && data.weather.size()>0 ){
            WeatherData weatherData= data.weather.get(0);
            if(weatherData.icon!=null){
                String urlIcon=WeatherAPI.getIconWeather(weatherData.icon);
                ImageLoader.getInstance().displayImage(urlIcon,mImgWeather);
            }
        }

    }



    public void getGeolocation() {
        String url = LocationAPI.URL;
        LoadingStateLayout loadingStateLayout=(LoadingStateLayout)mStateFactory.getStateLayout(StateFactory.LOADING_STATE_LAYOUT,getMainActivity());
        if(loadingStateLayout!=null){
            loadingStateLayout.showStateLayout();
        }
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
                ErrorStateLayout errorStateLayout=(ErrorStateLayout)mStateFactory.getStateLayout(StateFactory.ERROR_STATE_LAYOUT,getMainActivity());
                if(errorStateLayout!=null){
                    errorStateLayout.setReloadClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getGeolocation();
                        }
                    });
                    errorStateLayout.showStateLayout();
                }
            }
        });
        VolleyRequestQueue.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }


    public void getCurrentWeather() {
        String url = WeatherAPI.getWeatherDataByGeo(Config.mLat, Config.mLon);
        LoadingStateLayout loadingStateLayout=(LoadingStateLayout)mStateFactory.getStateLayout(StateFactory.LOADING_STATE_LAYOUT,getMainActivity());
        if(loadingStateLayout!=null){
            loadingStateLayout.showStateLayout();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, response.toString());
                try {
                    CurrentWeatherData currentWeatherData = new Gson().fromJson(response.toString(), new TypeToken<CurrentWeatherData>() {
                    }.getType());
                    if(currentWeatherData!=null){
                        updateData(currentWeatherData);

                        // remove state view
                        mStateFactory.releaseContentViews();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                ErrorStateLayout errorStateLayout=(ErrorStateLayout)mStateFactory.getStateLayout(StateFactory.ERROR_STATE_LAYOUT,getMainActivity());
                if(errorStateLayout!=null){
                    errorStateLayout.setReloadClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getCurrentWeather();
                        }
                    });
                    errorStateLayout.showStateLayout();
                }
            }
        });
        VolleyRequestQueue.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

}

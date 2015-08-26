package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class CurrentWeatherFragment extends BaseFragment<MainActivity> {
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //if (mRootView==null){
            mRootView=inflater.inflate(R.layout.fragment_current_weather,container,false);

//        }else {
//            ((ViewGroup)mRootView.getParent()).removeView(mRootView);
//        }
        return mRootView;
    }
}

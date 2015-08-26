package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 21/08/2015.
 */
public class BaseFragment<T extends Activity> extends Fragment {

    protected T mActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected T getMainActivity(){
        return mActivity;
    }

    @Override
    public void onAttach(Activity activity) {
        this.mActivity=(T)activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}

package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 21/08/2015.
 */
public class BaseFragment<T extends Activity> extends Fragment {

    protected T mActivity;




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

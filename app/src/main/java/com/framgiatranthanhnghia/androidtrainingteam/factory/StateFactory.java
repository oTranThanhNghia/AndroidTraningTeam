package com.framgiatranthanhnghia.androidtrainingteam.factory;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.BaseStateLayout;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.ErrorStateLayout;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.LoadingStateLayout;
import com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout.NoResultStateLayout;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class StateFactory {
    public static final int LOADING_STATE_LAYOUT=0;
    public static final int ERROR_STATE_LAYOUT=1;
    public static final int NO_RESULT_STATE_LAYOUT=2;

    private RelativeLayout mRootLayout;
    public StateFactory(RelativeLayout rootLayout){
        mRootLayout=rootLayout;
    }

    public BaseStateLayout getStateLayout(int typeState,Activity activity) {
        try {
            switch (typeState) {
                case ERROR_STATE_LAYOUT:
                    return new ErrorStateLayout(activity, mRootLayout);
                case LOADING_STATE_LAYOUT:
                    return new LoadingStateLayout(activity, R.layout.layout_loading, mRootLayout);
                case NO_RESULT_STATE_LAYOUT:
                    return new NoResultStateLayout(activity, R.layout.layout_no_result,mRootLayout);
                default:
                    return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
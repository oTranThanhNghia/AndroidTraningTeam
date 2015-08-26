package com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout;

import android.app.Activity;
import android.widget.RelativeLayout;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class NoResultStateLayout extends BaseStateLayout {
    public NoResultStateLayout(Activity activity,int layoutId,RelativeLayout rootView) throws NullPointerException,Exception{
        super(activity,rootView);
        setContentLayout(layoutId);
    }
    @Override
    public void onCreate() {

    }

    @Override
    public void initListener() {

    }
}
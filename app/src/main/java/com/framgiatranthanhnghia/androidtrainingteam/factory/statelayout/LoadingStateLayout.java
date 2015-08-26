package com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framgiatranthanhnghia.androidtrainingteam.R;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class LoadingStateLayout extends BaseStateLayout {

    private TextView mTxtMes;

    public LoadingStateLayout(Activity activity,int layoutId,RelativeLayout rootView) throws NullPointerException,Exception{
        super(activity,rootView);
        setContentLayout(layoutId);
    }
    @Override
    public void onCreate() {
        mTxtMes=(TextView)mRootLayout.findViewById(R.id.txt_message);
    }

    public void setMessage(String mes){
        if(mTxtMes!=null){
            mTxtMes.setText(mes);
        }
    }

    @Override
    public void initListener() {

    }


}
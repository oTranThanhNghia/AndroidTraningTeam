package com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.framgiatranthanhnghia.androidtrainingteam.R;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class ErrorStateLayout extends BaseStateLayout {
    private Button mBtnReload;
    private int mLayoutErrorID=R.layout.layout_error;
    private View.OnClickListener reloadClick;
    public ErrorStateLayout (Activity activity,RelativeLayout rootView) throws Exception{
        super(activity,rootView);
        setContentLayout(mLayoutErrorID);
    }
    @Override
    public void onCreate() {
        mBtnReload=(Button)mRootLayout.findViewById(R.id.btn_try_again);
    }

    @Override
    public void initListener() {
        mBtnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reloadClick!=null){
                    reloadClick.onClick(v);
                }
            }
        });
    }

    public void setReloadClickListener(View.OnClickListener listener){
        reloadClick=listener;
    }



}
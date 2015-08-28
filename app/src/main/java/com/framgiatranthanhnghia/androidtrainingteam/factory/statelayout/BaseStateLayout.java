package com.framgiatranthanhnghia.androidtrainingteam.factory.statelayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public abstract class BaseStateLayout {
    protected RelativeLayout mRootLayout;
    protected Activity mActivity;
    private int mLayoutID;

    public BaseStateLayout(Activity activity,RelativeLayout rootView) throws NullPointerException,Exception{
        if(rootView!=null && activity!=null) {
            mActivity = activity;
            mRootLayout = rootView;
        }else{
            throw new NullPointerException(" context or rootView is null");
        }
    }
    protected void setContentLayout(int layoutID) throws Exception{
        mLayoutID=layoutID;
        init();
    }
    private void init() throws Exception{
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mRootLayout.getChildCount() > 0) {
                    mRootLayout.removeAllViews();
                }
                LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(mLayoutID, null);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mRootLayout.getLayoutParams().width, mRootLayout.getLayoutParams().height);
                view.setLayoutParams(layoutParams);
                mRootLayout.addView(view);
                mRootLayout.setVisibility(View.GONE);
                onCreate();
                initListener();
            }
        });

    }
    public abstract void onCreate();
    public abstract void initListener();

    public void hiddenStateLayout(){
        mRootLayout.setVisibility(View.GONE);
    }

    public void releaseContentViews(){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mRootLayout.getChildCount()>0){
                    mRootLayout.removeAllViews();
                }
                hiddenStateLayout();
            }
        });

    }
    public void showStateLayout(){
        if(mRootLayout.getChildCount()>0) {
            mRootLayout.setVisibility(View.VISIBLE);
        }else{
            try {
                init();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
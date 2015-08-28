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
    private BaseStateLayout mCurrentState=null;

    public StateFactory(RelativeLayout rootLayout){
        mRootLayout=rootLayout;
    }

    public BaseStateLayout getStateLayout(int typeState,Activity activity) {

        BaseStateLayout baseStateLayout=null;
        try {
            switch (typeState) {
                case ERROR_STATE_LAYOUT:
                    baseStateLayout= new ErrorStateLayout(activity, mRootLayout);
                    break;
                case LOADING_STATE_LAYOUT:
                    baseStateLayout= new LoadingStateLayout(activity, R.layout.layout_loading, mRootLayout);
                    break;
                case NO_RESULT_STATE_LAYOUT:
                    baseStateLayout =new NoResultStateLayout(activity, R.layout.layout_no_result,mRootLayout);
                    break;
                default:
                    break;
            }
            return mCurrentState=baseStateLayout;
        }
        catch (Exception e){
            e.printStackTrace();
            return mCurrentState=null;
        }

    }

    public void showStateLayout(){
        if(mCurrentState!=null){
            mCurrentState.showStateLayout();
        }
    }

    public void releaseContentViews(){
        if(mCurrentState!=null){
            mCurrentState.releaseContentViews();
        }
    }

    public void hiddenStateLayout(){
        if(mCurrentState!=null){
            mCurrentState.hiddenStateLayout();
        }
    }
}
package com.framgiatranthanhnghia.androidtrainingteam.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ngh on 8/27/2015.
 */
public class VolleyRequestQueue {
    private static VolleyRequestQueue mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleyRequestQueue(Context context){
        mContext=context;
        mRequestQueue= getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null){
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public static synchronized VolleyRequestQueue getInstance(Context context){
        if(mInstance==null){
            mInstance=new VolleyRequestQueue(context);
        }
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}

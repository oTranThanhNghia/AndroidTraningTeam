package com.framgiatranthanhnghia.androidtrainingteam.fragments;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class NotificationFragment extends BaseFragment<MainActivity> implements View.OnClickListener {

    private View mRootView;

    private EditText mEdtTitle;
    private EditText mEdtContent;

    private Button mBtnNotifi;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView=inflater.inflate(R.layout.fragment_notification,container,false);

        mEdtTitle=(EditText)mRootView.findViewById(R.id.edt_title);
        mEdtContent=(EditText)mRootView.findViewById(R.id.edt_content);

        mBtnNotifi=(Button)mRootView.findViewById(R.id.btn_notifi);
        mBtnNotifi.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onClick(View view) {
        Notification.Builder noti = new Notification.Builder(getActivity())
                .setContentTitle(mEdtTitle.getText().toString())
                .setContentText(mEdtContent.getText().toString())
                .setSmallIcon(R.drawable.framgia_notification)
                .setDefaults(Notification.DEFAULT_SOUND);


        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT < 16) {
            notificationManager.notify(0, noti.getNotification());
        } else {
            notificationManager.notify(0, noti.build());
        }

    }
}

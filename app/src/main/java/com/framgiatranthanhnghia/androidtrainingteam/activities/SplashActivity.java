package com.framgiatranthanhnghia.androidtrainingteam.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.framgiatranthanhnghia.androidtrainingteam.R;

/**
 * Created by ngh on 8/28/2015.
 */
public class SplashActivity extends BaseActivity {
    private final int MAX_PROGRESS=300;

    private ProgressBar mProbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProbar=(ProgressBar)findViewById(R.id.probar_status);
        new AsyncTask<Void,Integer,Void>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProbar.setProgress(0);
                mProbar.setMax(MAX_PROGRESS);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for(int i=0;i<MAX_PROGRESS;i++){
                    publishProgress(i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                int progress=values[0];
                mProbar.setProgress(progress);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent i=new Intent(SplashActivity.this,MainActivity.class);
                SplashActivity.this.startActivity(i);
                SplashActivity.this.finish();
            }
        }.execute();
    }
}

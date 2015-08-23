package com.framgiatranthanhnghia.androidtrainingteam.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.framgiatranthanhnghia.androidtrainingteam.R;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 21/08/2015.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startFragment(Class<? extends Fragment> fragmentClass, Bundle bundle ){
        Fragment fragment=null;
        try{
            fragment=fragmentClass.newInstance();
            if(bundle!=null){
                fragment.setArguments(bundle);
            }

            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,fragment,fragmentClass.getCanonicalName());
            fragmentTransaction.addToBackStack(fragmentClass.getCanonicalName());
            fragmentTransaction.commitAllowingStateLoss();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void popFragmentBackStack(Class<? extends Fragment> fragmentClass){
        String name=fragmentClass.getCanonicalName();
        FragmentManager fragmentManager=getSupportFragmentManager();
        boolean fragmentIsPopped=fragmentManager.popBackStackImmediate(name,0);

        if(!fragmentIsPopped){
            fragmentManager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

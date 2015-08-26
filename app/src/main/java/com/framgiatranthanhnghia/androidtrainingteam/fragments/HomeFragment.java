package com.framgiatranthanhnghia.androidtrainingteam.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.framgiatranthanhnghia.androidtrainingteam.R;
import com.framgiatranthanhnghia.androidtrainingteam.activities.MainActivity;
import com.framgiatranthanhnghia.androidtrainingteam.adapter.ViewPagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 21/08/2015.
 */
public class HomeFragment extends BaseFragment<MainActivity> {

    private final String TAG = "HomeFragment";

    private View mRootView;
    private PagerSlidingTabStrip mTabs;
    private ViewPager mPager;
    private ViewPagerAdapter mPagerAdapter;
    private List<ViewPagerAdapter.ItemViewPager> mListFragment;


    private List<ViewPagerAdapter.ItemViewPager> getListFragment() {
        List<ViewPagerAdapter.ItemViewPager> list = new ArrayList<>();
        list.add(new ViewPagerAdapter.ItemViewPager(getString(R.string.today), new CurrentWeatherFragment()));
        list.add(new ViewPagerAdapter.ItemViewPager(getString(R.string.forecast), new ForecastWeatherFragment()));
        return list;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_home, container, false);

        mTabs = (PagerSlidingTabStrip) mRootView.findViewById(R.id.tabs);
        mPager = (ViewPager) mRootView.findViewById(R.id.pager);
        mListFragment = getListFragment();
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mListFragment);

        mPager.setAdapter(mPagerAdapter);
        mTabs.setViewPager(mPager);

        return mRootView;
    }


}

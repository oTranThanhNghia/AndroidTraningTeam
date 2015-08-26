package com.framgiatranthanhnghia.androidtrainingteam.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by FRAMGIA\tran.thanh.nghia on 26/08/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<ItemViewPager> mListFrag;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<ItemViewPager> listFrag) {
        super(fragmentManager);
        mListFrag = listFrag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mListFrag.get(position).title;
    }

    @Override
    public Fragment getItem(int position) {
        return mListFrag.get(position).fragment;
    }

    @Override
    public int getCount() {
        return mListFrag == null ? 0 : mListFrag.size();
    }

    public static class ItemViewPager {
        public String title;
        public Fragment fragment;

        public ItemViewPager(String title, Fragment fragment) {
            this.title = title;
            this.fragment = fragment;
        }
    }
}

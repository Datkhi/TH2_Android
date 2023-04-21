package com.th2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.th2.fragment.FragmentInfo;
import com.th2.fragment.FragmentList;
import com.th2.fragment.FragmentSearch;

public class ViewPagerAdapterTablayout extends FragmentStatePagerAdapter {
    public ViewPagerAdapterTablayout(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FragmentInfo();
            case 2:
                return new FragmentSearch();
            default:
                return new FragmentList();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 1:
                title = "INFO";
                break;
            case 2:
                title = "SEARCH";
                break;
            default:
                title = "LIST";
                break;
        }
        return title;
    }
}

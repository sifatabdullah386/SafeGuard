package com.example.safeguard.TabActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabFireStationPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    //Constructor to the class
    public TabFireStationPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                TabFireStationList tabFireStationList = new TabFireStationList();
                return tabFireStationList;
            case 1:
                TabFireStationMaps tabFireStationMaps = new TabFireStationMaps();
                return tabFireStationMaps;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
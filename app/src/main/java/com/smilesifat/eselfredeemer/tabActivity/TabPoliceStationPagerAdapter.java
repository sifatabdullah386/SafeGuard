package com.smilesifat.eselfredeemer.tabActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TabPoliceStationPagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;

    public TabPoliceStationPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TabPoliceStationList tabPoliceStationList = new TabPoliceStationList();
                return tabPoliceStationList;
            case 1:
                TabPoliceStationMaps tabPoliceStationMaps = new TabPoliceStationMaps();
                return tabPoliceStationMaps;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}
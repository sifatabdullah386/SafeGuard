package com.example.safeguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class FireStations extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    //This is our viewPager
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_stations);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.fire_station_toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        //This is our tablayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.fire_station_tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Fire Station List"));
        tabLayout.addTab(tabLayout.newTab().setText("Fire Station Maps"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.fire_station_pager);

        //Creating our pager adapter
        TabFireStationPagerAdapter adapter = new TabFireStationPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener((TabLayout.BaseOnTabSelectedListener) FireStations.this);
    }
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

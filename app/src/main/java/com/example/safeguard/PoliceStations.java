package com.example.safeguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class PoliceStations extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_stations);

        Toolbar toolbar = findViewById(R.id.police_station_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.police_station_tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Police Station List"));
        tabLayout.addTab(tabLayout.newTab().setText("Police Station Maps"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.police_station_pager);

        TabPoliceStationPagerAdapter adapter = new TabPoliceStationPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.setOnTabSelectedListener( PoliceStations.this);
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

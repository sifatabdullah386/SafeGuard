package com.smilesifat.eselfredeemer.homeScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.smilesifat.eselfredeemer.R;
import com.smilesifat.eselfredeemer.tabActivity.TabFireStationPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class FireStations extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_stations);


        TabLayout tabLayout = findViewById(R.id.fire_station_tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Fire Station List"));
        tabLayout.addTab(tabLayout.newTab().setText("Fire Station Maps"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = findViewById(R.id.fire_station_pager);

        //Creating our pager adapter
        TabFireStationPagerAdapter adapter = new TabFireStationPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(FireStations.this);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

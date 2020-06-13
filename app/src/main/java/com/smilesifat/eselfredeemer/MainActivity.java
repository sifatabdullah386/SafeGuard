package com.smilesifat.eselfredeemer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.smilesifat.eselfredeemer.bottomMenu.Volunteers;
import com.smilesifat.eselfredeemer.admin.AdminActivity;
import com.smilesifat.eselfredeemer.bottomMenu.AlertTag;
import com.smilesifat.eselfredeemer.bottomMenu.CurrentLocation;
import com.smilesifat.eselfredeemer.bottomMenu.Organizations;
import com.smilesifat.eselfredeemer.bottomMenu.Profile;
import com.smilesifat.eselfredeemer.drawerMenu.AmbulancesActivity;
import com.smilesifat.eselfredeemer.drawerMenu.HospitalsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.onesignal.OneSignal;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    FirebaseUser user;
    static String LoggedIn_User_Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        user = mAuth.getCurrentUser();
        Log.d("LOGGED", "user: " + user);


        //Setting the tags for Current User.
        if (user != null) {
            LoggedIn_User_Email =user.getEmail();
        }
        OneSignal.sendTag("User_ID", LoggedIn_User_Email);

        //calling toolbar actionbar
        Toolbar toolbar=  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //calling drawer menu
        NavigationView navigationView=  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(drawerNavListener);

        //calling drawer layout
        DrawerLayout drawer=  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        toggle.syncState();
        //three dot coloring
        Objects.requireNonNull(toolbar.getOverflowIcon()).setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

        //calling bottom navigation menu
        BottomNavigationView bottomNav =  findViewById(R.id.nav_items);
        FrameLayout fragment_container = findViewById(R.id.fragment_container);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AlertTag()).commit();
        }
    }

    //for bottom navigation menu items in multiple selection
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_alert_tag:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AlertTag()).commit();
                    break;
                case R.id.nav_organization:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Organizations()).commit();
                    break;
                case R.id.nav_current_location:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurrentLocation()).commit();
                    break;
                case R.id.nav_volunteers:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Volunteers()).commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile()).commit();
                    break;
                default:
            }
            return true;
        }
    };
    //for Navigation Drawer menu item multiple selection
    private NavigationView.OnNavigationItemSelectedListener drawerNavListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item){
            switch (item.getItemId()) {
                case R.id.drawer_ambulances:
                    Intent ambulancesIntent=new Intent(MainActivity.this, AmbulancesActivity.class);
                    startActivity(ambulancesIntent);
                    break;
                case R.id.drawer_hospitals:
                    Intent hospitalsIntent=new Intent(MainActivity.this, HospitalsActivity.class);
                    startActivity(hospitalsIntent);
                    break;
                case R.id.drawer_admin:
                    Intent intent=new Intent(MainActivity.this, AdminActivity.class);
                    startActivity(intent);
                    break;
                case R.id.drawer_coordinator:
                    Toast.makeText(getApplicationContext(),"Coordinator",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_privacy_policy:
                    Toast.makeText(getApplicationContext(),"Privacy Policy",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.drawer_share:
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=" + getPackageName());
                    sendIntent.setType("text/plain");
                    String shareBody = "Your body is here";
                    String shareSub = "Your subject";
                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareSub);
                    startActivity(shareIntent);
                    break;
                case R.id.drawer_rate_us:
                    Intent rateUs=new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                    startActivity(rateUs);
                    break;
                case R.id.drawer_about_the_apps:
                    Intent aboutApp=new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/apps/publish/?account=4705483250201306577&noredirect=#DeveloperPagePlace"));
                    startActivity(aboutApp);
                    break;
                default:
            }
            return true;
        }

    };

    //for navigation drawer backpressed
    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    //for toolbar 3dot icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settings_menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //for settings menu items multiple selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_notification_settings:
                Toast.makeText(getApplicationContext(),"Notification Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.facebook:
                Intent aboutApp=new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.com"));
                startActivity(aboutApp);
                break;
            case R.id.drawer_logout:
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("logged",false);
                editor.apply();
                Intent i=new Intent(MainActivity.this,SplashActivity.class);
                startActivity(i);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}

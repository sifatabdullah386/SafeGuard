package com.example.safeguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AdminPanel extends AppCompatActivity {

    CardView Ambulances,FireServices,Hospitals,Organizations,PoliceStations,CriminalPhotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Ambulances =findViewById(R.id.admin_add_ambulances_card_view);
        FireServices =findViewById(R.id.admin_add_fire_station_card_view);
        Hospitals =findViewById(R.id.admin_add_hospitals_card_view);
        Organizations =findViewById(R.id.admin_add_organizations_card_view);
        PoliceStations =findViewById(R.id.admin_add_police_stations_card_view);
        CriminalPhotos =findViewById(R.id.admin_watch_criminal_card_view);

        Ambulances.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1=new Intent(AdminPanel.this,addAmbulances.class);
                startActivity(intent1);
            }
        });
        FireServices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2=new Intent(AdminPanel.this,addFireStations.class);
                startActivity(intent2);
            }
        });
        Hospitals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent3=new Intent(AdminPanel.this,addHospitals.class);
                startActivity(intent3);
            }
        });
        Organizations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent4=new Intent(AdminPanel.this,addOrganizations.class);
                startActivity(intent4);
            }
        });
        PoliceStations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent5=new Intent(AdminPanel.this,addPoliceStations.class);
                startActivity(intent5);
            }
        });
        CriminalPhotos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent5=new Intent(AdminPanel.this,CriminalPhotos.class);
                startActivity(intent5);
            }
        });
    }
}

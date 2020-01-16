package com.example.safeguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {

    Button Ambulances,FireServices,Hospitals,Organizations,PoliceStations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Ambulances =findViewById(R.id.admin_ambulances_button);
        FireServices =findViewById(R.id.admin_fire_services_button);
        Hospitals =findViewById(R.id.admin_hospitals_button);
        Organizations =findViewById(R.id.admin_organizations_button);
        PoliceStations =findViewById(R.id.admin_police_stations_button);

        Ambulances.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent1=new Intent(AdminPanel.this,addAmbulances.class);
                startActivity(intent1);
            }
        });
        FireServices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent2=new Intent(AdminPanel.this,addFireServices.class);
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
    }
}

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

        Ambulances =findViewById(R.id.add_ambulance_button);
        FireServices =findViewById(R.id.add_fire_services_button);
        Hospitals =findViewById(R.id.add_hospitals_button);
        Organizations =findViewById(R.id.add_organizations_button);
        PoliceStations =findViewById(R.id.add_police_stations_button);

        Ambulances.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,addAmbulances.class);
                startActivity(intent);
            }
        });
        FireServices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,addFireServices.class);
                startActivity(intent);
            }
        });
        Hospitals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,addHospitals.class);
                startActivity(intent);
            }
        });
        Organizations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,addOrganizations.class);
                startActivity(intent);
            }
        });
        PoliceStations.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(AdminPanel.this,addPoliceStations.class);
                startActivity(intent);
            }
        });
    }
}

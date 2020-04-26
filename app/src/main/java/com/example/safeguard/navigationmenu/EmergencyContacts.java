package com.example.safeguard.navigationmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.safeguard.R;
import com.example.safeguard.admin.addEmergencyContacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EmergencyContacts extends AppCompatActivity {

    FloatingActionButton AddEmContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        AddEmContacts=findViewById(R.id.floating_button_add_emergency_contract);

        AddEmContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmergencyContacts addEmergencyContacts=new addEmergencyContacts();
                addEmergencyContacts.show(getSupportFragmentManager(),"addEmergencyContacts");
            }
        });
    }
}

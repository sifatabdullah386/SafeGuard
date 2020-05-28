package com.example.safeguard.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.safeguard.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import com.example.safeguard.constractor.addAmbulancesConstructor;


public class addAmbulances extends AppCompatActivity {

    EditText AmbulanceName,AmbulanceLocation,AmbulancePhoneNumber,AmbulanceEmail,AmbulanceDescription;
    Spinner AmbulanceType;
    Button AmbulanceSubmit;
    DatabaseReference addAmbulanceReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ambulances_layout);

        addAmbulanceReferences = FirebaseDatabase.getInstance().getReference("Ambulance List");

        AmbulanceName=findViewById(R.id.add_ambulance_name);
        AmbulanceLocation=findViewById(R.id.add_ambulance_location);
        AmbulancePhoneNumber=findViewById(R.id.add_ambulance_phone_number);
        AmbulanceEmail=findViewById(R.id.add_ambulance_email);
        AmbulanceDescription=findViewById(R.id.add_ambulance_description);
        AmbulanceType=findViewById(R.id.add_ambulances_type);

        AmbulanceSubmit=findViewById(R.id.add_ambulance_submit);
        AmbulanceSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAmbulanceData();
            }
        });

        Toolbar toolbar = findViewById(R.id.add_ambulances_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void AddAmbulanceData(){
        String AmbulanceNameData=AmbulanceName.getText().toString().trim();
        String AmbulanceLocationData=AmbulanceLocation.getText().toString().trim();
        String AmbulancePhoneNumberData=AmbulancePhoneNumber.getText().toString().trim();
        String AmbulanceEmailData=AmbulanceEmail.getText().toString().trim();
        String AmbulanceDescriptionData=AmbulanceDescription.getText().toString().trim();
        String AmbulanceTypeData=AmbulanceType.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(AmbulanceNameData)||TextUtils.isEmpty(AmbulanceLocationData)||TextUtils.isEmpty(AmbulancePhoneNumberData)||TextUtils.isEmpty(AmbulanceDescriptionData)){
            Toast.makeText(addAmbulances.this,"Enter Your All Details",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addAmbulances.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }
        //For Real time Data Store
        String id=addAmbulanceReferences.push().getKey();
        assert id != null;
        addAmbulanceReferences.child(id).setValue(new addAmbulancesConstructor(AmbulanceNameData,AmbulanceLocationData,AmbulancePhoneNumberData,AmbulanceEmailData,AmbulanceTypeData,AmbulanceDescriptionData));
        Toast.makeText(addAmbulances.this,"Ambulance Details added successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

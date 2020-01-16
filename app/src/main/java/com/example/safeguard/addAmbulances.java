package com.example.safeguard;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class addAmbulances extends AppCompatActivity {

    EditText AmbulanceName,AmbulanceLocation,AmbulancePhoneNumber,AmbulanceEmail,AmbulanceDescription;
    Spinner AmbulanceType;
    Button AmbulanceSubmit;
    DatabaseReference addAmbulanceReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ambulances_layout);

        addAmbulanceReferences = FirebaseDatabase.getInstance().getReference("Ambulances List");

        AmbulanceName=findViewById(R.id.add_ambulance_name);
        AmbulanceLocation=findViewById(R.id.add_ambulance_location);
        AmbulancePhoneNumber=findViewById(R.id.add_ambulance_phone_number);
        AmbulanceEmail=findViewById(R.id.add_ambulance_email);
        AmbulanceDescription=findViewById(R.id.add_ambulance_description);
        AmbulanceType=findViewById(R.id.add_ambulances_type);

        AmbulanceSubmit=findViewById(R.id.add_ambulance_button);
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
        String OrganizationNameData=AmbulanceName.getText().toString().trim();
        String OrganizationLocationData=AmbulanceLocation.getText().toString().trim();
        String OrganizationPhoneNumberData=AmbulancePhoneNumber.getText().toString().trim();
        String OrganizationEmailData=AmbulanceEmail.getText().toString().trim();
        String OrganizationDescriptionData=AmbulanceDescription.getText().toString().trim();

        String OrganizationTypeData=AmbulanceType.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(OrganizationNameData)){
            Toast.makeText(addAmbulances.this,"Enter Your Name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationLocationData)){
            Toast.makeText(addAmbulances.this,"Enter Your Location",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationPhoneNumberData)){
            Toast.makeText(addAmbulances.this,"Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationEmailData)){
            Toast.makeText(addAmbulances.this,"Enter Your Email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationDescriptionData)){
            Toast.makeText(addAmbulances.this,"Description",Toast.LENGTH_LONG).show();
        }
        if(OrganizationDescriptionData.length()<10){
            Toast.makeText(addAmbulances.this,"Enter At Least 10 Words",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addAmbulances.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }

        //For Real time Data Store
        String id=addAmbulanceReferences.push().getKey();
        assert id != null;
        addAmbulanceReferences.child(id).setValue(new addOrganizationConstructor(id,OrganizationNameData,OrganizationLocationData,OrganizationPhoneNumberData,OrganizationEmailData,OrganizationTypeData,OrganizationDescriptionData));
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

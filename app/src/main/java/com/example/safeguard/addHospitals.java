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

public class addHospitals extends AppCompatActivity {

    EditText HospitalName,HospitalLocation,HospitalPhoneNumber,HospitalEmail,HospitalDescription;
    Spinner HospitalType;
    Button HospitalSubmit;
    DatabaseReference addHospitalReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_hospitals_layout);

        addHospitalReferences= FirebaseDatabase.getInstance().getReference("Hospital List");

        HospitalName=findViewById(R.id.add_hospital_name);
        HospitalLocation=findViewById(R.id.add_hospital_location);
        HospitalPhoneNumber=findViewById(R.id.add_hospital_phone_number);
        HospitalEmail=findViewById(R.id.add_hospital_email);
        HospitalDescription=findViewById(R.id.add_hospital_description);
        HospitalType=findViewById(R.id.add_hospital_type);

        HospitalSubmit=findViewById(R.id.add_hospital_submit);
        HospitalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddHospitalData();
            }
        });

        Toolbar toolbar = findViewById(R.id.add_hospitals_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void AddHospitalData(){
        String HospitalNameData=HospitalName.getText().toString().trim();
        String HospitalLocationData=HospitalLocation.getText().toString().trim();
        String HospitalPhoneNumberData=HospitalPhoneNumber.getText().toString().trim();
        String HospitalEmailData=HospitalEmail.getText().toString().trim();
        String HospitalDescriptionData=HospitalDescription.getText().toString().trim();

        String HospitalTypeData=HospitalType.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(HospitalNameData)||TextUtils.isEmpty(HospitalLocationData)||TextUtils.isEmpty(HospitalPhoneNumberData)||TextUtils.isEmpty(HospitalEmailData)||TextUtils.isEmpty(HospitalDescriptionData)){
            Toast.makeText(addHospitals.this,"Enter your Hospital Details",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addHospitals.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }
        String id=addHospitalReferences.push().getKey();
        assert id != null;
        addHospitalReferences.child(id).setValue(new addOrganizationConstructor(id,HospitalNameData,HospitalLocationData,HospitalPhoneNumberData,HospitalEmailData,HospitalTypeData,HospitalDescriptionData));
        Toast.makeText(addHospitals.this,"Hospital details added successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

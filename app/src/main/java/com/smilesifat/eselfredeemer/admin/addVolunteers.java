package com.smilesifat.eselfredeemer.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smilesifat.eselfredeemer.R;
import com.smilesifat.eselfredeemer.constractor.addVolunteersConstructor;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class addVolunteers extends AppCompatActivity {
    EditText VolunteerName,VolunteerLocation,VolunteerPhoneNumber,VolunteerEmail,VolunteerDescription;
    Button VolunteerSubmit;
    DatabaseReference addVolunteerReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_volunteers);

        addVolunteerReferences= FirebaseDatabase.getInstance().getReference("Volunteer List");

        VolunteerName=findViewById(R.id.add_volunteer_name);
        VolunteerLocation=findViewById(R.id.add_volunteer_location);
        VolunteerPhoneNumber=findViewById(R.id.add_volunteer_phone_number);
        VolunteerEmail=findViewById(R.id.add_volunteer_email);
        VolunteerDescription=findViewById(R.id.add_volunteer_description);


        VolunteerSubmit=findViewById(R.id.add_volunteer_submit);
        VolunteerSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushAddVolunteerData();
            }
        });

        Toolbar toolbar = findViewById(R.id.volunteers_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void pushAddVolunteerData(){
        String VolunteerNameData=VolunteerName.getText().toString().trim();
        String VolunteerLocationData=VolunteerLocation.getText().toString().trim();
        String VolunteerPhoneNumberData=VolunteerPhoneNumber.getText().toString().trim();
        String VolunteerEmailData=VolunteerEmail.getText().toString().trim();
        String VolunteerDescriptionData=VolunteerDescription.getText().toString().trim();

        if(TextUtils.isEmpty(VolunteerNameData)){
            Toast.makeText(addVolunteers.this,"Enter Your Name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(VolunteerLocationData)){
            Toast.makeText(addVolunteers.this,"Enter Your Location",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(VolunteerPhoneNumberData)){
            Toast.makeText(addVolunteers.this,"Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(VolunteerEmailData)){
            Toast.makeText(addVolunteers.this,"Enter Your Email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(VolunteerDescriptionData)){
            Toast.makeText(addVolunteers.this,"Description",Toast.LENGTH_LONG).show();
        }
        if(VolunteerDescriptionData.length()<10){
            Toast.makeText(addVolunteers.this,"Enter At Least 10 Words",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addVolunteers.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }
        //For Real time Data Store
        String ID=addVolunteerReferences.push().getKey();
        assert ID != null;
        addVolunteerReferences.child(ID).setValue(new addVolunteersConstructor(ID,VolunteerNameData,VolunteerLocationData,VolunteerPhoneNumberData,VolunteerEmailData,VolunteerDescriptionData));
        Toast.makeText(addVolunteers.this,"Volunteer Details Added Successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

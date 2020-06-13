package com.smilesifat.eselfredeemer.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.smilesifat.eselfredeemer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import com.smilesifat.eselfredeemer.constractor.addOrganizationsConstructor;

public class addOrganizations extends AppCompatActivity {
    EditText OrganizationName,OrganizationLocation,OrganizationPhoneNumber,OrganizationEmail,OrganizationDescription;
    Spinner OrganizationType;
    Button OrganizationSubmit;
    DatabaseReference addOrganizationReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_organizations);

        addOrganizationReferences= FirebaseDatabase.getInstance().getReference("Organization List");

        OrganizationName=findViewById(R.id.add_organization_name);
        OrganizationLocation=findViewById(R.id.add_organization_location);
        OrganizationPhoneNumber=findViewById(R.id.add_organization_phone_number);
        OrganizationEmail=findViewById(R.id.add_organization_email);
        OrganizationDescription=findViewById(R.id.add_organization_description);
        OrganizationType=findViewById(R.id.add_organization_type);

        OrganizationSubmit=findViewById(R.id.add_organization_submit);
        OrganizationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushAddOrganizationData();
            }
        });

        Toolbar toolbar = findViewById(R.id.add_organizations_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void pushAddOrganizationData(){
        String OrganizationNameData=OrganizationName.getText().toString().trim();
        String OrganizationLocationData=OrganizationLocation.getText().toString().trim();
        String OrganizationPhoneNumberData=OrganizationPhoneNumber.getText().toString().trim();
        String OrganizationEmailData=OrganizationEmail.getText().toString().trim();
        String OrganizationDescriptionData=OrganizationDescription.getText().toString().trim();

        String OrganizationTypeData=OrganizationType.getSelectedItem().toString().trim();

        if(TextUtils.isEmpty(OrganizationNameData)){
            Toast.makeText(addOrganizations.this,"Enter Your Name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationLocationData)){
            Toast.makeText(addOrganizations.this,"Enter Your Location",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationPhoneNumberData)){
            Toast.makeText(addOrganizations.this,"Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationEmailData)){
            Toast.makeText(addOrganizations.this,"Enter Your Email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(OrganizationDescriptionData)){
            Toast.makeText(addOrganizations.this,"Description",Toast.LENGTH_LONG).show();
        }
        if(OrganizationDescriptionData.length()<10){
            Toast.makeText(addOrganizations.this,"Enter At Least 10 Words",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addOrganizations.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }
        //For Real time Data Store
        String id=addOrganizationReferences.push().getKey();
        assert id != null;
        addOrganizationReferences.child(id).setValue(new addOrganizationsConstructor(id,OrganizationNameData,OrganizationLocationData,OrganizationPhoneNumberData,OrganizationEmailData,OrganizationTypeData,OrganizationDescriptionData));
        Toast.makeText(addOrganizations.this,"Organization Add Successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.safeguard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class addFireServices extends AppCompatActivity {

    EditText FireStationName,FireStationLocation,FireStationPhoneNumber,FireStationEmail,FireStationDescription;
    Button FireStationSubmit;
    DatabaseReference addFireStationReferences;
    private List<Place.Field> fields;
    final int AUTOCOMPLETE_REQUEST_CODE=1;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fire_services_layout);

        addFireStationReferences= FirebaseDatabase.getInstance().getReference().child("FireStation List");

        FireStationName=findViewById(R.id.add_fire_station_name);
        FireStationLocation=findViewById(R.id.add_fire_station_location);
        FireStationPhoneNumber=findViewById(R.id.add_fire_station_phone_number);
        FireStationEmail=findViewById(R.id.add_fire_station_email);
        FireStationDescription=findViewById(R.id.add_fire_station_description);

        FireStationSubmit=findViewById(R.id.add_fire_services_submit);
        FireStationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addFireServices.this,AdminPanel.class);
                startActivity(intent);
                AddOrganizationData();
            }
        });

        if (!Places.isInitialized()) {
            String apiKey="AIzaSyB7S9-1M1j_LjVGo3HirR_bibNhB9tKK84";
            Places.initialize(getApplicationContext(),apiKey);
        }
        fields= Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);

        FireStationLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(addFireServices.this);
                startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE);
            }
        });
        Toolbar toolbar = findViewById(R.id.add_fire_services_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void AddOrganizationData(){
        String FireStationNameData=FireStationName.getText().toString().trim();
        String FireStationPhoneNumberData=FireStationPhoneNumber.getText().toString().trim();
        String FireStationEmailData=FireStationEmail.getText().toString().trim();
        String FireStationDescriptionData=FireStationDescription.getText().toString().trim();

        if(TextUtils.isEmpty(FireStationNameData)){
            Toast.makeText(addFireServices.this,"Enter Fire Station Name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(FireStationPhoneNumberData)){
            Toast.makeText(addFireServices.this,"Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(FireStationEmailData)){
            Toast.makeText(addFireServices.this,"Enter Your Email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(FireStationDescriptionData)){
            Toast.makeText(addFireServices.this,"Description",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addFireServices.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }

        //For Real time Data Store
        String id=addFireStationReferences.push().getKey();
        assert id != null;
        DatabaseReference InfoReferences=addFireStationReferences.child("Info").child(id);
        InfoReferences.setValue(new addFireStationConstructor(id,FireStationNameData,FireStationPhoneNumberData,FireStationEmailData,FireStationDescriptionData));
        Toast.makeText(addFireServices.this,"Fire Station added successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location = place.getName();
                final LatLng latLang = place.getLatLng();
                Log.i("Place", "Place: " + place.getName() + ", " + place.getId());
                FireStationLocation.setTag(location);

                String user_id=addFireStationReferences.push().getKey();
                assert user_id != null;
                addFireStationReferences.child("latLang").child(user_id).setValue(latLang);
                addFireStationReferences.child("locations").child(user_id).setValue(location);

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                assert status.getStatusMessage() != null;
                Log.i("Message", status.getStatusMessage());
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

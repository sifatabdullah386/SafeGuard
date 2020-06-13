package com.smilesifat.eselfredeemer.admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smilesifat.eselfredeemer.constractor.LocationConstructor;
import com.smilesifat.eselfredeemer.R;
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

import com.smilesifat.eselfredeemer.constractor.addPoliceStationsConstructor;

public class addPoliceStations extends AppCompatActivity {

    EditText PoliceStationName,PoliceStationPhoneNumber,PoliceStationEmail,PoliceStationDescription;
    TextView PoliceStationLocation;
    Button PoliceStationSubmit;
    DatabaseReference addPoliceStationReferences;
    private List<Place.Field> fields;
    final int AUTOCOMPLETE_REQUEST_CODE=1;
    private  String location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_police_stations_layout);

        Toolbar toolbar = findViewById(R.id.add_police_stations_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        addPoliceStationReferences= FirebaseDatabase.getInstance().getReference("PoliceStation List");

        PoliceStationName=findViewById(R.id.add_police_station_name);
        PoliceStationLocation=findViewById(R.id.add_police_station_location);
        PoliceStationPhoneNumber=findViewById(R.id.add_police_station_phone_number);
        PoliceStationEmail=findViewById(R.id.add_police_station_email);
        PoliceStationDescription=findViewById(R.id.add_police_station_description);

        PoliceStationSubmit=findViewById(R.id.add_police_station_submit);
        PoliceStationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addPoliceStations.this,AdminPanel.class);
                startActivity(intent);
                String PoliceStationNameData=PoliceStationName.getText().toString().trim();
                String PoliceStationPhoneNumberData=PoliceStationPhoneNumber.getText().toString().trim();
                String PoliceStationEmailData=PoliceStationEmail.getText().toString().trim();
                String PoliceStationLocationData=location;
                String PoliceStationDescriptionData=PoliceStationDescription.getText().toString().trim();

                if(TextUtils.isEmpty(PoliceStationNameData)||TextUtils.isEmpty(PoliceStationPhoneNumberData)||TextUtils.isEmpty(PoliceStationEmailData)||TextUtils.isEmpty(PoliceStationDescriptionData)){
                    Toast.makeText(addPoliceStations.this,"Enter All Details",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(addPoliceStations.this,"Data Entry Success",Toast.LENGTH_LONG).show();
                }
                String id=addPoliceStationReferences.push().getKey();
                assert id != null;
                addPoliceStationReferences.child("Info").child(id).setValue(new addPoliceStationsConstructor(PoliceStationNameData,PoliceStationPhoneNumberData,PoliceStationEmailData,PoliceStationLocationData,PoliceStationDescriptionData));
                Toast.makeText(addPoliceStations.this,"Police Stations Added Successfully",Toast.LENGTH_LONG).show();
            }
        });

        if (!Places.isInitialized()) {
            String apiKey="@string/google_maps_key";
            Places.initialize(getApplicationContext(),apiKey);
        }
        fields= Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);

        PoliceStationLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(addPoliceStations.this);
                startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location = place.getName();
                LatLng latLang = place.getLatLng();
                PoliceStationLocation.setText(location);

                String id=addPoliceStationReferences.push().getKey();
                assert id != null;
                addPoliceStationReferences.child("locations").child(id).setValue(new LocationConstructor(location,latLang));

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

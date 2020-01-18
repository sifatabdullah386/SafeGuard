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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class addPoliceStations extends AppCompatActivity {

    EditText PoliceStationName,PoliceStationLocation,PoliceStationPhoneNumber,PoliceStationEmail,PoliceStationDescription;
    Button PoliceStationSubmit;
    DatabaseReference addPoliceStationReferences;

    private List<Place.Field> fields;
    final int AUTOCOMPLETE_REQUEST_CODE=1;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_police_stations_layout);

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
                AddOrganizationData();
            }
        });

        if (!Places.isInitialized()) {
            String apiKey="AIzaSyB7S9-1M1j_LjVGo3HirR_bibNhB9tKK84";
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

        Toolbar toolbar = findViewById(R.id.add_police_stations_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }
    public void AddOrganizationData(){
        String PoliceStationNameData=PoliceStationName.getText().toString().trim();
        final String PoliceStationLocationData=PoliceStationLocation.getText().toString().trim();
        String PoliceStationPhoneNumberData=PoliceStationPhoneNumber.getText().toString().trim();
        String PoliceStationEmailData=PoliceStationEmail.getText().toString().trim();
        String PoliceStationDescriptionData=PoliceStationDescription.getText().toString().trim();

        if(TextUtils.isEmpty(PoliceStationNameData)){
            Toast.makeText(addPoliceStations.this,"Enter Police Station Name",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(PoliceStationLocationData)){
            Toast.makeText(addPoliceStations.this,"Enter Your Location",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(PoliceStationPhoneNumberData)){
            Toast.makeText(addPoliceStations.this,"Enter Your Phone Number",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(PoliceStationEmailData)){
            Toast.makeText(addPoliceStations.this,"Enter Your Email",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(PoliceStationDescriptionData)){
            Toast.makeText(addPoliceStations.this,"Description",Toast.LENGTH_LONG).show();
        }
        if(PoliceStationDescriptionData.length()<10){
            Toast.makeText(addPoliceStations.this,"Enter At Least 10 Words",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(addPoliceStations.this,"Data Entry Success",Toast.LENGTH_LONG).show();
        }

        //For Real time Data Store
        String id=addPoliceStationReferences.push().getKey();
        assert id != null;
        addPoliceStationReferences.child(id).setValue(new addFireStationConstructor(id,PoliceStationNameData,PoliceStationLocationData,PoliceStationPhoneNumberData,PoliceStationEmailData,PoliceStationDescriptionData));
        Toast.makeText(addPoliceStations.this,"addPolice Stations Added Successfully",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location = place.getName();
                final LatLng latlang = place.getLatLng();
                Log.i("Place", "Place: " + place.getName() + ", " + place.getId());
                PoliceStationLocation.setTag(location);

                String user_id= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                addPoliceStationReferences.child("latLangs/").child(user_id).setValue(latlang);
                addPoliceStationReferences.child("locations/").child(user_id).setValue(location);

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

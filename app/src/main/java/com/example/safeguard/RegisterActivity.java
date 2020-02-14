package com.example.safeguard;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;

import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity   {

    EditText UserName,UserPhoneNumber,UserEmail,UserPassword;
    Button signUpButton,UserAddress,GuestLogin;
    FirebaseUser CurrentUser;
    ProgressBar progressBar;
    private DatabaseReference UserDatabase;
    private FirebaseAuth firebaseAuth;
    private List<Place.Field> fields;
    final int AUTOCOMPLETE_REQUEST_CODE=1;
    String location;
    //private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        UserName = findViewById(R.id.userNameEditText);
        UserPhoneNumber = findViewById(R.id.phoneNumberEditText);
        UserAddress = findViewById(R.id.locationButton);
        UserEmail = findViewById(R.id.emailEditText);
        UserPassword = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        progressBar = findViewById(R.id.progressBar);


        GuestLogin=findViewById(R.id.button_guest_login);
        GuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        doRegistration();

        if (!Places.isInitialized()) {
            String apiKey="AIzaSyDuzb58bZ3zMAbDn8pEsTq867UYGaC6RPA";
            Places.initialize(getApplicationContext(),apiKey);
        }
        fields= Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);

        UserAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(RegisterActivity.this);
                startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE);
            }
        });
    }
    @Override
    public void onStart(){
        super.onStart();
        CurrentUser = firebaseAuth.getCurrentUser();
       /* if(CurrentUser!=null){
            finish();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            doRegistration();
        }*/
    }
    private void doRegistration(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = UserName.getText().toString().trim();
                final String phoneNumber = UserPhoneNumber.getText().toString().trim();
                final String email = UserEmail.getText().toString().trim();
                String password = UserPassword.getText().toString().trim();

                if(TextUtils.isEmpty(userName) ||TextUtils.isEmpty(phoneNumber) ||TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_LONG).show();
                            }
                            else{
                                //For Real time Data Store
                                String user_id= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                                userDataConstructor send=new userDataConstructor(userName,phoneNumber,email);
                                UserDatabase.child("Info").child(user_id).setValue(send);
                                Toast.makeText(RegisterActivity.this,"Information Added Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
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
                final LatLng latLang = place.getLatLng();
                Log.i("Place", "Place: " + place.getName() + ", " + place.getId());
                UserAddress.setText(location);

                String user_id= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                DatabaseReference latLagReference = UserDatabase.child("latLang").child(user_id);
                DatabaseReference location = UserDatabase.child("locations").child(user_id);
                latLagReference.setValue(latLang);
                location.setValue(location);

            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                assert status.getStatusMessage() != null;
                Log.i("Message", status.getStatusMessage());
            }

        }
    }
}
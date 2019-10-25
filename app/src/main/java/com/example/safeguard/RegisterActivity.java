package com.example.safeguard;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText UserName,UserPhoneNumber,UserAddress,UserEmail,UserPassword;
    Button signUpButton;
    TextView GuestLogin;
    int PLACE_PICKER_REQUEST=1;
    private FirebaseAuth firebaseAuth;
    FirebaseUser CurrentUser;
    ProgressBar progressBar;
    DatabaseReference UserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserName = findViewById(R.id.userNameEditText);
        UserPhoneNumber = findViewById(R.id.phoneNumberEditText);
        UserAddress = findViewById(R.id.locationEditText);
        UserEmail = findViewById(R.id.emailEditText);
        UserPassword = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        progressBar = findViewById(R.id.progressBar);

        UserAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlacePicker();
            }
        });
        GuestLogin=findViewById(R.id.button_guest_login);
        GuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        UserDatabase= FirebaseDatabase.getInstance().getReference("Users");
        doRegistration();
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
                final String location = UserAddress.getText().toString().trim();
                final String email = UserEmail.getText().toString().trim();
                String password = UserPassword.getText().toString().trim();

                if(TextUtils.isEmpty(userName) ||TextUtils.isEmpty(phoneNumber) ||TextUtils.isEmpty(location) ||TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
                else{
                        progressBar.setVisibility(View.VISIBLE);
                        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    finish();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Registration failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
                //For Real time Data Store
                String id= firebaseAuth.getUid();
                userDataConstructor send=new userDataConstructor(userName,phoneNumber,location,email);
                assert id != null;
                UserDatabase.child(id).setValue(send);
                Toast.makeText(RegisterActivity.this,"Information Added Successfully",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void openPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
    GoogleMap mGoogleMap;
    Marker mCurrLocationMarker;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                Place place = PlacePicker.getPlace(this, data);
                final String placeName = String.format("Place: %s", place.getName());
                final double latitude = place.getLatLng().latitude;
                final double longitude = place.getLatLng().longitude;
                LatLng coordinate = new LatLng(latitude, longitude);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(coordinate);
                markerOptions.title(placeName); //Here Total Address is address which you want to show on marker
                mGoogleMap.clear();
                markerOptions.icon(
                        BitmapDescriptorFactory
                                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

                markerOptions.getPosition();
                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
                mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
                UserAddress.setText(placeName.toString().trim());
            } else {
                throw new IllegalStateException("Unexpected value: " + requestCode);
            }
        }
    }

}
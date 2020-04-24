package com.example.safeguard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, DialogLogin.DialogLoginListener {

    EditText UserName,UserPhoneNumber,UserEmail,UserPassword,LoginUserName,LoginPassword;
    private TextView UserAddress;
    ProgressBar progressBar;
    private DatabaseReference UserDatabase;
    private FirebaseAuth firebaseAuth;
    private Location location;
    private String userLocation;
    private double  latitude;
    private double  longitude;
    private static final int REQUEST_CODE_LOCATION_PERMISSION=1;
    private ResultReceiver resultReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        UserName = findViewById(R.id.userNameEditText);
        UserPhoneNumber = findViewById(R.id.phoneNumberEditText);
        UserAddress = findViewById(R.id.locationTextView);
        UserEmail = findViewById(R.id.emailEditText);
        UserPassword = findViewById(R.id.passwordEditText);
        LoginUserName = findViewById(R.id.loginEmailEditText);
        LoginPassword = findViewById(R.id.loginPasswordEditText);
        TextView loginTextView = findViewById(R.id.loginTextView);

        Button signUpButton = findViewById(R.id.signUpButton);
        progressBar = findViewById(R.id.progressBar);

        signUpButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);

        doAutomaticLogin();
        findLocation();

        resultReceiver=new AddressResultReceiver(new Handler());
    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.signUpButton){
            doRegistration();
        }
        else if(view.getId()==R.id.loginTextView){
            OpenDialogLogin();
        }
    }
    //open on activity result
    private void findLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
            }
        }
        else {
            getCurrentLocation();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE_LOCATION_PERMISSION && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
            else{
                Toast.makeText(RegisterActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
            }
        }
    }
    private  void getCurrentLocation(){
        progressBar.setVisibility(View.VISIBLE);

        LocationRequest locationRequest=new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(RegisterActivity.this)
                .requestLocationUpdates(locationRequest,new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(RegisterActivity.this).removeLocationUpdates(this);
                        if(locationResult!=null & locationResult.getLocations().size()>0){
                            int latestLocationIndex=locationResult.getLocations().size()-1;
                            latitude= locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            longitude=locationResult.getLocations().get(latestLocationIndex).getLongitude();
                            //UserAddress.setText(String.format("Latitude: %s Longitude:%s",latitude,longitude));

                            location=new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAddressFromLatLong(location);
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, Looper.getMainLooper());
    }
    private void fetchAddressFromLatLong(Location location){
        Intent intent =new Intent(this,FetchAddressIntentService.class);
        intent.putExtra(LocationConstants.RECEIVER, resultReceiver);
        intent.putExtra(LocationConstants.LOCATION_DATA_EXTRA,location);
        startService(intent);
    }
    private class AddressResultReceiver extends ResultReceiver{
        AddressResultReceiver(Handler handler){
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if(resultCode==LocationConstants.SUCCESS_RESULT){
                userLocation=resultData.getString(LocationConstants.RESULT_DATA_KEY);
                UserAddress.setText(userLocation);
            }
            else{
                Toast.makeText(RegisterActivity.this,resultData.getString(LocationConstants.RESULT_DATA_KEY),Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }
    }
    private void doRegistration(){
                final String userName = UserName.getText().toString().trim();
                final String phoneNumber = UserPhoneNumber.getText().toString().trim();
                final String email = UserEmail.getText().toString().trim();
                final String UserLocation=userLocation;
                final Location AddressLocation=location;
                final double LocationLatitude=latitude;
                final double LocationLongitude=longitude;
                final String password = UserPassword.getText().toString().trim();

                if(TextUtils.isEmpty(userName) ||TextUtils.isEmpty(phoneNumber) ||TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    //progressbar visible
                    progressBar.setVisibility(View.VISIBLE);
                    //register users with email password
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_LONG).show();
                            }
                            else{
                                //For auto login
                                SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("emailKey",email);
                                editor.putString("passwordKey",password);
                                editor.apply();

                                //For Real time Data Store
                                String user_id= Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                                userDataConstructor send=new userDataConstructor(userName,phoneNumber,email,AddressLocation,UserLocation,LocationLatitude,LocationLongitude);
                                UserDatabase.child("Info").child(user_id).setValue(send);
                                Toast.makeText(RegisterActivity.this,"Information Added Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                //go to main activity
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                }
    }
    private void doAutomaticLogin() {
        progressBar.setVisibility(View.VISIBLE);
        //read data from shared preferences
        SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("usernameKey")&& sharedPreferences.contains("emailKey") && sharedPreferences.contains("passwordKey")){
            String userEmail=sharedPreferences.getString("emailKey","Data Not Found");
            String password=sharedPreferences.getString("passwordKey","Data Not Found");

            //firebase login
            firebaseAuth.signInWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "Login failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(RegisterActivity.this,"Complete your Registration First ",Toast.LENGTH_LONG).show();
        }
        progressBar.setVisibility(View.GONE);
    }
    //open dialog login
    private void OpenDialogLogin() {
        progressBar.setVisibility(View.VISIBLE);
        DialogLogin dialogLogin=new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(),"Login Dialog");
        progressBar.setVisibility(View.GONE);
    }
    //Dialog Login
    @Override
    public void applyTexts(String LoginEmail, String LoginPassword) {
        //For auto login
        SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("emailKey",LoginEmail);
        editor.putString("passwordKey",LoginPassword);
        editor.apply();

        //firebase login
        firebaseAuth.signInWithEmailAndPassword(LoginEmail,LoginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.VISIBLE);
                if(task.isSuccessful()){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Login failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
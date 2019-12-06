package com.example.safeguard;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
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

public class RegisterActivity extends AppCompatActivity {

    EditText UserName,UserPhoneNumber,UserEmail,UserPassword;
    Button signUpButton,UserAddress,GuestLogin;
    private FirebaseAuth firebaseAuth;
    FirebaseUser CurrentUser;
    ProgressBar progressBar;
    private DatabaseReference UserDatabase;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserName = findViewById(R.id.userNameEditText);
        UserPhoneNumber = findViewById(R.id.phoneNumberEditText);
        UserAddress = findViewById(R.id.locationButton);
        UserEmail = findViewById(R.id.emailEditText);
        UserPassword = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        UserAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(RegisterActivity.this,PickPlace.class);
              startActivity(intent);
            }
        });
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String value=bundle.getString("currentLocation");
            UserAddress.setText(value);
        }
        GuestLogin=findViewById(R.id.button_guest_login);
        GuestLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
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
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_LONG).show();
                            }
                            else{
                                //For Real time Data Store
                                String user_id= firebaseAuth.getCurrentUser().getUid();
                                DatabaseReference UserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                userDataConstructor send=new userDataConstructor(userName,phoneNumber,location,email);
                                UserDatabase.setValue(send);
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
}
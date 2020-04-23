package com.example.safeguard;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, DialogLogin.DialogLoginListener {

    EditText UserName,UserPhoneNumber,UserEmail,UserPassword,LoginUserName,LoginPassword;
    private TextView UserAddress;
    ProgressBar progressBar;
    private DatabaseReference UserDatabase;
    private FirebaseAuth firebaseAuth;
    private List<Place.Field> fields;
    final int AUTOCOMPLETE_REQUEST_CODE=1;
    String location;
    LatLng latLang;

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
        UserAddress.setOnClickListener(this);

        doAutomaticLogin();

        if (!Places.isInitialized()) {
            String apiKey="AIzaSyAQ51Bejel1ohyxPdoK4-N9q8yMmYJYnDA";
            Places.initialize(getApplicationContext(),apiKey);
        }
        fields= Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG);
    }

    @Override
    public void onClick(View view){
        if(view.getId()==R.id.signUpButton){
            doRegistration();
        }
        else if(view.getId()==R.id.loginTextView){
            OpenDialogLogin();
        }
        else if(view.getId()==R.id.locationTextView){
            findLocation();
        }
    }
    private void doRegistration(){
                final String userName = UserName.getText().toString().trim();
                final String phoneNumber = UserPhoneNumber.getText().toString().trim();
                final String email = UserEmail.getText().toString().trim();
                final String Location=location;
                final LatLng LocationLatLng=latLang;
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
                            progressBar.setVisibility(View.GONE);
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
                                userDataConstructor send=new userDataConstructor(userName,phoneNumber,email,Location,LocationLatLng);
                                UserDatabase.child("Info").child(user_id).setValue(send);
                                Toast.makeText(RegisterActivity.this,"Information Added Successfully",Toast.LENGTH_LONG).show();
                                finish();
                                //go to main activity
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
    }
    private void doAutomaticLogin() {
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
    }
    //open dialog login
    private void OpenDialogLogin() {
        DialogLogin dialogLogin=new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(),"Login Dialog");
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
    //open on activity result
    private void findLocation() {
        Intent intent =new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(RegisterActivity.this);
        startActivityForResult(intent,AUTOCOMPLETE_REQUEST_CODE);
    }
    //auto complete places
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                location = place.getName();
                latLang = place.getLatLng();
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
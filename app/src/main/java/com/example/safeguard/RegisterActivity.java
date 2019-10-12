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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText UserName,PhoneNumber,Address,Email,Password;
    Button signUpButton;
    TextView AlreadySign;
    private FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    ProgressBar progressBar;
    DatabaseReference userDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserName = findViewById(R.id.userNameEditText);
        PhoneNumber = findViewById(R.id.phoneNumberEditText);
        Address = findViewById(R.id.locationEditText);
        Email = findViewById(R.id.emailEditText);
        Password = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        progressBar = findViewById(R.id.progressBar);

        AlreadySign=findViewById(R.id.already_sign_text_view);
        AlreadySign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
        userDatabase= FirebaseDatabase.getInstance().getReference("users");
        doRegistration();
    }
    @Override
    public void onStart(){
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void doRegistration(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = UserName.getText().toString().trim();
                String phoneNumber = PhoneNumber.getText().toString().trim();
                String location = Address.getText().toString().trim();
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

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
                                    FirebaseUser user=firebaseAuth.getCurrentUser();
                                    updateUI(user);

                                    /*Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);*/
                                }
                                else{
                                    Toast.makeText(RegisterActivity.this, "Registration failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
                }
                //For Real time Data Store
                String id=userDatabase.push().getKey();
                userDataConstructor send=new userDataConstructor(id,userName,phoneNumber,location,email);
                assert id != null;
                userDatabase.child(id).setValue(send);
                Toast.makeText(RegisterActivity.this,"Information Added Successfully",Toast.LENGTH_LONG).show();
            }
        });

    }
    private void updateUI(FirebaseUser user){
        if(user!=null){
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            doRegistration();
        }
    }

}
package com.example.safeguard;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AdminActivity extends AppCompatActivity {

    EditText AdminUsername, AdminPassword;
    Button AdminLogin;
    FirebaseAuth firebaseAuth;
    String Email="smilesifat002@gmail.com";
    String PassWord="coders360";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_layout);

        AdminUsername = findViewById(R.id.adminUsername);
        AdminPassword = findViewById(R.id.adminPassword);
        AdminLogin = findViewById(R.id.admin_button);

        firebaseAuth = FirebaseAuth.getInstance();

        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        doLoginIn();
    }
    private void doLoginIn(){
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = AdminUsername.getText().toString().trim();
                final String password = AdminPassword.getText().toString().trim();

                if(Email.equals(email) && PassWord.equals(password)){
                    if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                        Toast.makeText(AdminActivity.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(AdminActivity.this, AdminPanel.class);
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(AdminActivity.this, "Login failed! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
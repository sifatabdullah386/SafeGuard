package com.example.safeguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.welcome_progress_bar);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doProgressWork();
                doAutomaticLogin();
            }
        });
        thread.start();
    }
    public void doProgressWork(){

        int progress;
        for(progress=1;progress<=100;progress=progress+5){
            try {
                Thread.sleep(30);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private void doAutomaticLogin() {

        SharedPreferences sharedPreferences=getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("logged",false)){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}

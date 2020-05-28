package com.example.safeguard.homeScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.constractor.FreeConstructor;
import com.example.safeguard.R;
import com.example.safeguard.adapters.RequestAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class Request extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RequestAdapter requestAdapter;
    ArrayList<FreeConstructor> helpRequestList;
    ArrayList<FreeConstructor> sexualHelpRequestList;
    ArrayList<FreeConstructor> TrafficAccidentHelpRequestList;
    ArrayList<FreeConstructor> EmergencyMHelpRequestList;

    DatabaseReference helpReference = FirebaseDatabase.getInstance().getReference().child("Requests");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        recyclerView = findViewById(R.id.request_recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        helpRequestList = new ArrayList<>();
        sexualHelpRequestList = new ArrayList<>();
        TrafficAccidentHelpRequestList = new ArrayList<>();
        EmergencyMHelpRequestList = new ArrayList<>();

        getPersonalHelpRequestList();
    }
    private void getPersonalHelpRequestList() {
        helpReference.child("HelpRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot m : dataSnapshot.getChildren()){
                    FreeConstructor d = m.getValue(FreeConstructor.class);
                    helpRequestList.add(d);
                }
                requestAdapter = new RequestAdapter(Request.this, helpRequestList);
                recyclerView.setAdapter(requestAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        helpReference.child("SexualHarassmentHelpRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot m : dataSnapshot.getChildren()){
                    FreeConstructor d = m.getValue(FreeConstructor.class);
                    sexualHelpRequestList.add(d);
                }
                requestAdapter = new RequestAdapter(Request.this, sexualHelpRequestList);
                recyclerView.setAdapter(requestAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        helpReference.child("TrafficAccidentsHelpRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot m : dataSnapshot.getChildren()){
                    FreeConstructor d = m.getValue(FreeConstructor.class);
                    TrafficAccidentHelpRequestList.add(d);
                }
                requestAdapter = new RequestAdapter(Request.this, TrafficAccidentHelpRequestList);
                recyclerView.setAdapter(requestAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        helpReference.child("EmergencyMedicalHelpRequest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot m : dataSnapshot.getChildren()){
                    FreeConstructor d = m.getValue(FreeConstructor.class);
                    EmergencyMHelpRequestList.add(d);
                }
                requestAdapter = new RequestAdapter(Request.this, EmergencyMHelpRequestList);
                recyclerView.setAdapter(requestAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

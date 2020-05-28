package com.example.safeguard.homeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.safeguard.R;
import com.example.safeguard.adapters.ResponseAdapter;
import com.example.safeguard.constractor.FreeConstructor;
import com.example.safeguard.constractor.userDataConstructor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Response extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ResponseAdapter responseAdapter;
    ArrayList<FreeConstructor> helpResponseList;
    ArrayList<userDataConstructor> userDetailsList;

    DatabaseReference helpReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);

        recyclerView = findViewById(R.id.response_recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        helpResponseList = new ArrayList<>();
        userDetailsList = new ArrayList<>();

        getResponseList();
    }
    private void getResponseList() {
        helpReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot m : dataSnapshot.getChildren()) {
                    FreeConstructor d = m.child("Requests").child("HelpRequest").getValue(FreeConstructor.class);
                    helpResponseList.add(d);
                    String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
                    userDataConstructor u = m.child("Users").child("Info").child(uid).getValue(userDataConstructor.class);
                    userDetailsList.add(u);
                }
                responseAdapter = new ResponseAdapter(Response.this,helpResponseList,userDetailsList);
                recyclerView.setAdapter(responseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

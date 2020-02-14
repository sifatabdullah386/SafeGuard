package com.example.safeguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class Hospitals extends AppCompatActivity {

    private FirebaseRecyclerOptions<addOrganizationConstructor> options;
    private FirebaseRecyclerAdapter<addOrganizationConstructor,FirebaseOrganizationViewHolder> adapter;

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);

        Toolbar toolbar = findViewById(R.id.add_hospitals_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        RecyclerView recyclerView = findViewById(R.id.hospitals_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<addOrganizationConstructor> arrayList = new ArrayList<>();
        DatabaseReference databaseHospitalsReference = FirebaseDatabase.getInstance().getReference().child("Hospital List");
        databaseHospitalsReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addOrganizationConstructor>().setQuery(databaseHospitalsReference,addOrganizationConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addOrganizationConstructor, FirebaseOrganizationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseOrganizationViewHolder firebaseOrganizationViewHolder, int i, @NonNull addOrganizationConstructor addOrganizationConstructor) {
                firebaseOrganizationViewHolder.OrganizationListName.setText(addOrganizationConstructor.getOrganizationName());
                firebaseOrganizationViewHolder.OrganizationListPhoneNumber.setText(addOrganizationConstructor.getOrganizationPhoneNumber());
                firebaseOrganizationViewHolder.OrganizationListEmail.setText(addOrganizationConstructor.getOrganizationEmail());
                firebaseOrganizationViewHolder.OrganizationListType.setText(addOrganizationConstructor.getOrganizationType());
                firebaseOrganizationViewHolder.OrganizationListLocation.setText(addOrganizationConstructor.getOrganizationLocation());
            }

            @NonNull
            @Override
            public FirebaseOrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseOrganizationViewHolder(LayoutInflater.from(Hospitals.this).inflate(R.layout.organization_list_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

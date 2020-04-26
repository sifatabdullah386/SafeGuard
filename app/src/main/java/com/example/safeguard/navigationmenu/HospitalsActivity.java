package com.example.safeguard.navigationmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.example.safeguard.FirebaseViewHolder.FirebaseHospitalsViewHolder;
import com.example.safeguard.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import com.example.safeguard.constractor.addHospitalsConstructor;

public class HospitalsActivity extends AppCompatActivity {

    private FirebaseRecyclerOptions<addHospitalsConstructor> options;
    private FirebaseRecyclerAdapter<addHospitalsConstructor, FirebaseHospitalsViewHolder> adapter;

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

        Toolbar toolbar = findViewById(R.id.hospitals_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        RecyclerView recyclerView = findViewById(R.id.hospitals_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference databaseHospitalsReference = FirebaseDatabase.getInstance().getReference().child("Hospital List");
        databaseHospitalsReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addHospitalsConstructor>().setQuery(databaseHospitalsReference,addHospitalsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addHospitalsConstructor, FirebaseHospitalsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseHospitalsViewHolder firebaseHospitalsViewHolder, int i, @NonNull addHospitalsConstructor addHospitalsConstructor) {
                firebaseHospitalsViewHolder.HospitalListName.setText(addHospitalsConstructor.getHospitalName());
                firebaseHospitalsViewHolder.HospitalListPhoneNumber.setText(addHospitalsConstructor.getHospitalPhoneNumber());
                firebaseHospitalsViewHolder.HospitalListEmail.setText(addHospitalsConstructor.getHospitalEmail());
                firebaseHospitalsViewHolder.HospitalListType.setText(addHospitalsConstructor.getHospitalType());
                firebaseHospitalsViewHolder.HospitalListLocation.setText(addHospitalsConstructor.getHospitalLocation());
            }

            @NonNull
            @Override
            public FirebaseHospitalsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseHospitalsViewHolder(LayoutInflater.from(HospitalsActivity.this).inflate(R.layout.hospitals_recyclerview,parent,false));
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

package com.example.safeguard.homeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.safeguard.R;
import com.example.safeguard.constractor.FreeConstructor;
import com.example.safeguard.firebaseViewHolder.FirebaseResponseViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Response extends AppCompatActivity {

    private FirebaseRecyclerOptions<FreeConstructor> options;
    private FirebaseRecyclerAdapter<FreeConstructor, FirebaseResponseViewHolder> adapter;
    private SearchView ResponseSearch;
    private RecyclerView recyclerView;
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
        setContentView(R.layout.activity_response);

        Toolbar toolbar = findViewById(R.id.response_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        ResponseSearch=findViewById(R.id.response_search_bar);

        ResponseSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                List<FreeConstructor> newList = new ArrayList();
                for(FreeConstructor name: newList)
                {
                    if(name.getUserName().toLowerCase().contains(userInput)){
                        newList.add(name);
                    }

                }
                recyclerView.setAdapter(adapter);
                return true;
            }
        });

        recyclerView = findViewById(R.id.response_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Response.this));
        final DatabaseReference databaseResponseReference = FirebaseDatabase.getInstance().getReference().child("Requests");
        databaseResponseReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<FreeConstructor>().setQuery(databaseResponseReference,FreeConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<FreeConstructor, FirebaseResponseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseResponseViewHolder firebaseResponseViewHolder, int i, @NonNull final FreeConstructor FreeConstructor) {

                firebaseResponseViewHolder.ResponseListName.setText(FreeConstructor.getUserName());
                firebaseResponseViewHolder.ResponseListPhoneNumber.setText(FreeConstructor.getUserPhoneName());
                firebaseResponseViewHolder.ResponseListLocation.setText(FreeConstructor.getLocation());
                firebaseResponseViewHolder.ResponseListMessage.setText(FreeConstructor.getMessage());
                firebaseResponseViewHolder.ResponseListCategory.setText(FreeConstructor.getRequestType());

                firebaseResponseViewHolder.ResponseListCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visitorPhoneNumber=FreeConstructor.getUserPhoneName();
                        String call="tel:"+visitorPhoneNumber.trim();
                        Intent callIntent=new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse(call));
                        startActivity(callIntent);
                    }
                });

            }
            @NonNull
            @Override
            public FirebaseResponseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseResponseViewHolder(LayoutInflater.from(Response.this).inflate(R.layout.list_cardview_response_recyclerview,parent,false));
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

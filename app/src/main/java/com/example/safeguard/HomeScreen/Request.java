package com.example.safeguard.HomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.safeguard.FirebaseViewHolder.FirebaseRequestViewHolder;
import com.example.safeguard.R;
import com.example.safeguard.constractor.RequestConstructor;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Request extends AppCompatActivity {

    private FirebaseRecyclerOptions<RequestConstructor> options;
    private FirebaseRecyclerAdapter<RequestConstructor, FirebaseRequestViewHolder> adapter;

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
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.request_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        RecyclerView recyclerView = findViewById(R.id.request_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseRequestReference = FirebaseDatabase.getInstance().getReference().child("helpRequest");
        databaseRequestReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<RequestConstructor>().setQuery(databaseRequestReference,RequestConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<RequestConstructor, FirebaseRequestViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseRequestViewHolder firebaseRequestViewHolder, int i, @NonNull RequestConstructor RequestConstructor) {
                firebaseRequestViewHolder.RequestListMessages.setText(RequestConstructor.getUserRequesterMessages());
                firebaseRequestViewHolder.RequestListCategoryType.setText(R.string.help_request);
                firebaseRequestViewHolder.RequestListLocation.setText(RequestConstructor.getUserRequesterLocation());
            }
            @NonNull
            @Override
            public FirebaseRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseRequestViewHolder(LayoutInflater.from(Request.this).inflate(R.layout.list_request_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
    }
}

package com.smilesifat.eselfredeemer.bottomMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;
import com.smilesifat.eselfredeemer.admin.addVolunteers;
import com.smilesifat.eselfredeemer.constractor.addVolunteersConstructor;
import com.smilesifat.eselfredeemer.firebaseViewHolder.FirebaseVolunteerViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Volunteers extends Fragment {

    private FirebaseRecyclerOptions<addVolunteersConstructor> options;
    private FirebaseRecyclerAdapter<addVolunteersConstructor, FirebaseVolunteerViewHolder> adapter;
    private DatabaseReference databaseVolunteerReference = FirebaseDatabase.getInstance().getReference();
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_volunteers, container, false);

        FloatingActionButton floatingActionButton=view.findViewById(R.id.floating_button_volunteer);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), addVolunteers.class);
                startActivity(intent);
            }
        });
        final RecyclerView recyclerView = view.findViewById(R.id.volunteer_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final DatabaseReference databaseVolunteerRef = databaseVolunteerReference.child("Volunteer List");
        databaseVolunteerRef.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addVolunteersConstructor>().setQuery(databaseVolunteerRef,addVolunteersConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addVolunteersConstructor, FirebaseVolunteerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseVolunteerViewHolder firebaseVolunteerViewHolder, int i, @NonNull final addVolunteersConstructor addVolunteersConstructor) {
                firebaseVolunteerViewHolder.VolunteerListName.setText(addVolunteersConstructor.getVolunteerName());
                firebaseVolunteerViewHolder.VolunteerListPhoneNumber.setText(addVolunteersConstructor.getVolunteerPhoneNumber());
                firebaseVolunteerViewHolder.VolunteerListEmail.setText(addVolunteersConstructor.getVolunteerEmail());
                firebaseVolunteerViewHolder.VolunteerListLocation.setText(addVolunteersConstructor.getVolunteerLocation());

                firebaseVolunteerViewHolder.VolunteerListCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visitorPhoneNumber= addVolunteersConstructor.getVolunteerPhoneNumber();
                        String call="tel:"+visitorPhoneNumber.trim();
                        Intent callIntent=new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse(call));
                        startActivity(callIntent);
                    }
                });
            }
            @NonNull
            @Override
            public FirebaseVolunteerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseVolunteerViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.list_cardview_volunteer_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return view;
    }

}

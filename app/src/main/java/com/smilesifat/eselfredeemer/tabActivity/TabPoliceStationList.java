package com.smilesifat.eselfredeemer.tabActivity;

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

import com.smilesifat.eselfredeemer.firebaseViewHolder.FirebasePoliceStationsViewHolder;
import com.smilesifat.eselfredeemer.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.smilesifat.eselfredeemer.constractor.addPoliceStationsConstructor;

public class TabPoliceStationList extends Fragment {

    private FirebaseRecyclerOptions<addPoliceStationsConstructor> options;
    private FirebaseRecyclerAdapter<addPoliceStationsConstructor, FirebasePoliceStationsViewHolder> adapter;

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
        View view = inflater.inflate(R.layout.tab_police_station_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.police_station_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        DatabaseReference PoliceStationReference = FirebaseDatabase.getInstance().getReference().child("PoliceStation List").child("Info");
        PoliceStationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addPoliceStationsConstructor>().setQuery(PoliceStationReference,addPoliceStationsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addPoliceStationsConstructor, FirebasePoliceStationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePoliceStationsViewHolder  firebasePoliceStationViewHolder , int i, @NonNull final addPoliceStationsConstructor addPoliceStationsConstructor) {
                firebasePoliceStationViewHolder.PoliceStationListName.setText(addPoliceStationsConstructor.getPoliceStationsName());
                firebasePoliceStationViewHolder.PoliceStationListPhoneNumber.setText(addPoliceStationsConstructor.getPoliceStationsPhoneNumber());
                firebasePoliceStationViewHolder.PoliceStationListEmail.setText(addPoliceStationsConstructor.getPoliceStationsEmail());
                firebasePoliceStationViewHolder.PoliceStationListLocation.setText(addPoliceStationsConstructor.getPoliceStationsLocation());

                firebasePoliceStationViewHolder.PoliceStationListCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visitorPhoneNumber= addPoliceStationsConstructor.getPoliceStationsPhoneNumber();
                        String call="tel:"+visitorPhoneNumber.trim();
                        Intent callIntent=new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse(call));
                        startActivity(callIntent);
                    }
                });
            }
            @NonNull
            @Override
            public FirebasePoliceStationsViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebasePoliceStationsViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.list_cardview_police_stations_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }
}


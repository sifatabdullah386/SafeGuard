package com.example.safeguard.TabActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.FirebaseViewHolder.FirebaseFireStationViewHolder;
import com.example.safeguard.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.safeguard.constractor.addFireStationsConstructor;

public class TabFireStationList extends Fragment {

    private FirebaseRecyclerOptions<addFireStationsConstructor> options;
    private FirebaseRecyclerAdapter<addFireStationsConstructor, FirebaseFireStationViewHolder> adapter;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab_fire_station_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fire_services_list_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        DatabaseReference databaseFireStationReference = FirebaseDatabase.getInstance().getReference().child("FireStation List").child("Info");
        databaseFireStationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addFireStationsConstructor>().setQuery(databaseFireStationReference,addFireStationsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addFireStationsConstructor, FirebaseFireStationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseFireStationViewHolder  firebaseFireStationViewHolder , int i, @NonNull addFireStationsConstructor addFireStationsConstructor) {
                firebaseFireStationViewHolder.FireStationListName.setText(addFireStationsConstructor.getFireStationName());
                firebaseFireStationViewHolder.FireStationListPhoneNumber.setText(addFireStationsConstructor.getFireStationPhoneNumber());
                firebaseFireStationViewHolder.FireStationListEmail.setText(addFireStationsConstructor.getFireStationEmail());
                firebaseFireStationViewHolder.FireStationListLocation.setText(addFireStationsConstructor.getFireStationLocation());
            }
            @NonNull
            @Override
            public FirebaseFireStationViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseFireStationViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.fire_station_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }
}

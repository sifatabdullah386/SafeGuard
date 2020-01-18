package  com.example.safeguard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TabPoliceStationList extends Fragment {

    private FirebaseRecyclerOptions<addFireStationConstructor> options;
    private FirebaseRecyclerAdapter<addFireStationConstructor,FirebaseFireStationViewHolder> adapter;

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
        View view = inflater.inflate(R.layout.tab_police_station_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fire_services_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ArrayList<addOrganizationConstructor> arrayList = new ArrayList<>();
        DatabaseReference databaseFireStationReference = FirebaseDatabase.getInstance().getReference().child("Organization List");
        databaseFireStationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addFireStationConstructor>().setQuery(databaseFireStationReference,addFireStationConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addFireStationConstructor, FirebaseFireStationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseFireStationViewHolder  firebaseFireStationViewHolder , int i, @NonNull addFireStationConstructor addFireStationConstructor) {
                firebaseFireStationViewHolder.FireStationListName.setText(addFireStationConstructor.getFireStationName());
                firebaseFireStationViewHolder.FireStationListPhoneNumber.setText(addFireStationConstructor.getFireStationPhoneNumber());
                firebaseFireStationViewHolder.FireStationListEmail.setText(addFireStationConstructor.getFireStationEmail());
                firebaseFireStationViewHolder.FireStationListLocation.setText(addFireStationConstructor.getFireStationLocation());
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


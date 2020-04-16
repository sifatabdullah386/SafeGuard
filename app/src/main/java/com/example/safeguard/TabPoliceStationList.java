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

    private FirebaseRecyclerOptions<addPoliceStationsConstructor> options;
    private FirebaseRecyclerAdapter<addPoliceStationsConstructor,FirebasePoliceStationsViewHolder> adapter;

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
        ArrayList<addPoliceStationsConstructor> arrayList = new ArrayList<>();
        DatabaseReference PoliceStationReference = FirebaseDatabase.getInstance().getReference().child("PoliceStation List").child("Info");
        PoliceStationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addPoliceStationsConstructor>().setQuery(PoliceStationReference,addPoliceStationsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addPoliceStationsConstructor, FirebasePoliceStationsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebasePoliceStationsViewHolder  firebasePoliceStationViewHolder , int i, @NonNull addPoliceStationsConstructor addPoliceStationsConstructor) {
                firebasePoliceStationViewHolder.PoliceStationListName.setText(addPoliceStationsConstructor.getPoliceStationsName());
                firebasePoliceStationViewHolder.PoliceStationListPhoneNumber.setText(addPoliceStationsConstructor.getPoliceStationsPhoneNumber());
                firebasePoliceStationViewHolder.PoliceStationListEmail.setText(addPoliceStationsConstructor.getPoliceStationsEmail());
                firebasePoliceStationViewHolder.PoliceStationListLocation.setText(addPoliceStationsConstructor.getPoliceStationsLocation());
            }
            @NonNull
            @Override
            public FirebasePoliceStationsViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebasePoliceStationsViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.police_stations_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }
}


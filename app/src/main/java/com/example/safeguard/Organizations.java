package  com.example.safeguard;
import android.content.Intent;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Organizations extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_organization, container, false);

        FloatingActionButton floatingActionButton=view.findViewById(R.id.floating_button_add_organization);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),addOrganizations.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.organization_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ArrayList<addOrganizationConstructor> arrayList = new ArrayList<>();
        DatabaseReference databaseOrganizationReference = FirebaseDatabase.getInstance().getReference().child("Organization List");
        databaseOrganizationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addOrganizationConstructor>().setQuery(databaseOrganizationReference,addOrganizationConstructor.class).build();

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
                return new FirebaseOrganizationViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.add_organization_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }

}

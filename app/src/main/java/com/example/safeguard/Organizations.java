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

public class Organizations extends Fragment {

    private FirebaseRecyclerOptions<addOrganizationsConstructor> options;
    private FirebaseRecyclerAdapter<addOrganizationsConstructor,FirebaseOrganizationViewHolder> adapter;

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
        final RecyclerView recyclerView = view.findViewById(R.id.organization_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        DatabaseReference databaseOrganizationReference = FirebaseDatabase.getInstance().getReference().child("Organization List");
        databaseOrganizationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addOrganizationsConstructor>().setQuery(databaseOrganizationReference,addOrganizationsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addOrganizationsConstructor, FirebaseOrganizationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseOrganizationViewHolder firebaseOrganizationViewHolder, int i, @NonNull final addOrganizationsConstructor addOrganizationsConstructor) {
                firebaseOrganizationViewHolder.OrganizationListName.setText(addOrganizationsConstructor.getOrganizationName());
                firebaseOrganizationViewHolder.OrganizationListPhoneNumber.setText(addOrganizationsConstructor.getOrganizationPhoneNumber());
                firebaseOrganizationViewHolder.OrganizationListEmail.setText(addOrganizationsConstructor.getOrganizationEmail());
                firebaseOrganizationViewHolder.OrganizationListType.setText(addOrganizationsConstructor.getOrganizationType());
                firebaseOrganizationViewHolder.OrganizationListLocation.setText(addOrganizationsConstructor.getOrganizationLocation());

                firebaseOrganizationViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_CALL);
                        intent.putExtra(Intent.EXTRA_PHONE_NUMBER,addOrganizationsConstructor.getOrganizationPhoneNumber());
                        intent.setType("text/number");
                        startActivity(intent);

                        //intent.putExtra("Organization Phone Number",addOrganizationConstructor.getOrganizationPhoneNumber());
                        //Onclick item new class
                        //String CharityListName=getInstance().getStringExtra(name:"CharityListName");
                    }
                });
            }

            @NonNull
            @Override
            public FirebaseOrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseOrganizationViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.organization_list_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);

        return view;
    }

}

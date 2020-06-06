package com.example.safeguard.bottomMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.constractor.FreeConstructor;
import com.example.safeguard.firebaseViewHolder.FirebaseOrganizationViewHolder;
import com.example.safeguard.R;
import com.example.safeguard.admin.addOrganizations;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.safeguard.constractor.addOrganizationsConstructor;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Organizations extends Fragment {

    private FirebaseRecyclerOptions<addOrganizationsConstructor> options;
    private FirebaseRecyclerAdapter<addOrganizationsConstructor, FirebaseOrganizationViewHolder> adapter;
    private SearchView OrganizationSearch;
    private DatabaseReference databaseOrganizationReference;
    ArrayList<addOrganizationsConstructor>addOrganizationsArrayList;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_organization, container, false);

        OrganizationSearch=view.findViewById(R.id.organization_search);

        OrganizationSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                return true;
            }
        });
        FloatingActionButton floatingActionButton=view.findViewById(R.id.floating_button_add_organization);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), addOrganizations.class);
                startActivity(intent);
            }
        });
        recyclerView = view.findViewById(R.id.organization_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        databaseOrganizationReference = FirebaseDatabase.getInstance().getReference().child("Organization List");
        databaseOrganizationReference.keepSynced(true);
        options=new FirebaseRecyclerOptions.Builder<addOrganizationsConstructor>().setQuery(databaseOrganizationReference,addOrganizationsConstructor.class).build();

        adapter=new FirebaseRecyclerAdapter<addOrganizationsConstructor, FirebaseOrganizationViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final FirebaseOrganizationViewHolder firebaseOrganizationViewHolder, int i, @NonNull final addOrganizationsConstructor addOrganizationsConstructor) {
                firebaseOrganizationViewHolder.OrganizationListName.setText(addOrganizationsConstructor.getOrganizationName());
                firebaseOrganizationViewHolder.OrganizationListPhoneNumber.setText(addOrganizationsConstructor.getOrganizationPhoneNumber());
                firebaseOrganizationViewHolder.OrganizationListEmail.setText(addOrganizationsConstructor.getOrganizationEmail());
                firebaseOrganizationViewHolder.OrganizationListType.setText(addOrganizationsConstructor.getOrganizationType());
                firebaseOrganizationViewHolder.OrganizationListLocation.setText(addOrganizationsConstructor.getOrganizationLocation());

                //final String key=getRef(i).getKey();

                firebaseOrganizationViewHolder.OrganizationListCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String visitorPhoneNumber=addOrganizationsConstructor.getOrganizationPhoneNumber();
                        String call="tel:"+visitorPhoneNumber.trim();
                        Intent callIntent=new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse(call));
                        startActivity(callIntent);
                    }
                });

            }
            @NonNull
            @Override
            public FirebaseOrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FirebaseOrganizationViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.list_cardview_organization_recyclerview,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return view;
    }
}

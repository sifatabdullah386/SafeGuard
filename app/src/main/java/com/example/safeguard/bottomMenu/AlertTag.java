package com.example.safeguard.bottomMenu;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.safeguard.homeScreen.FireStations;
import com.example.safeguard.homeScreen.HelpActivity;
import com.example.safeguard.R;
import com.example.safeguard.homeScreen.TotalUsers;
import com.example.safeguard.homeScreen.TrafficAccidents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlertTag extends Fragment {

    private TextView TotalUserNumber,TotalVolunteerNumber,TotalOrganizationNumber;
    private DatabaseReference userDta= FirebaseDatabase.getInstance().getReference();
    private int userCount,volunteerCount,organizationCount;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alert_tag, container, false);

        Button ButtonHelp=view.findViewById(R.id.button_help);
        CardView FireStation=view.findViewById(R.id.fire_station_card_view);
        CardView FeedBack=view.findViewById(R.id.feed_back_card_view);
        CardView PoliceStations=view.findViewById(R.id.police_stations_card_view);
        CardView EmergencyMedical=view.findViewById(R.id.medical_emergency_card_view);
        CardView SexualHarassment=view.findViewById(R.id.sexual_harassment_card_view);
        CardView TrafficAccident=view.findViewById(R.id.traffic_accident_card_view);
        CardView TotalUser=view.findViewById(R.id.total_user_card_view);
        TotalUserNumber=view.findViewById(R.id.total_user_number);
        TotalVolunteerNumber = view.findViewById(R.id.total_user_volunteer);
        TotalOrganizationNumber = view.findViewById(R.id.total_user_organization);

        DatabaseReference userTotalDta=userDta.child("Users").child("Info");
        DatabaseReference volunteersTotalDta=userDta.child("Volunteer List");
        DatabaseReference organizationsTotalDta=userDta.child("Organization List");

        userTotalDta.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    userCount = (int) dataSnapshot.getChildrenCount();
                    TotalUserNumber.setText(Integer.toString(userCount));
                }
                else{
                    TotalVolunteerNumber.setText(R.string.total_user_number);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        volunteersTotalDta.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    volunteerCount = (int) dataSnapshot.getChildrenCount();
                    TotalVolunteerNumber.setText(Integer.toString(volunteerCount));
                }
                else{
                    TotalVolunteerNumber.setText(R.string.total_user_volunteer);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        organizationsTotalDta.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    organizationCount = (int) dataSnapshot.getChildrenCount();
                    TotalOrganizationNumber.setText(Integer.toString(organizationCount));
                }
                else{
                    TotalOrganizationNumber.setText(R.string.total_user_organization);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
        FireStation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FireStations.class);
                startActivity(intent);
            }
        });
        FeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.homeScreen.FeedBack.class);
                startActivity(intent);
            }
        });
        PoliceStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.homeScreen.PoliceStations.class);
                startActivity(intent);
            }
        });
        EmergencyMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.homeScreen.EmergencyMedical.class);
                startActivity(intent);
            }
        });
        SexualHarassment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.homeScreen.SexualHarassment.class);
                startActivity(intent);
            }
        });
        TrafficAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), TrafficAccidents.class);
                startActivity(intent);
            }
        });
        TotalUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), TotalUsers.class);
                startActivity(intent);
            }
        });
        return view;
    }

}


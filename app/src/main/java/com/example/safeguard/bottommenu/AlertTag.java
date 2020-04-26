package com.example.safeguard.bottommenu;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.safeguard.HomeScreen.FireStations;
import com.example.safeguard.HomeScreen.HelpActivity;
import com.example.safeguard.R;
import com.example.safeguard.HomeScreen.TrafficAccidents;

public class AlertTag extends Fragment {

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
                Intent intent=new Intent(getActivity(), com.example.safeguard.HomeScreen.FeedBack.class);
                startActivity(intent);
            }
        });
        PoliceStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.HomeScreen.PoliceStations.class);
                startActivity(intent);
            }
        });
        EmergencyMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.HomeScreen.EmergencyMedical.class);
                startActivity(intent);
            }
        });
        SexualHarassment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), com.example.safeguard.HomeScreen.SexualHarassment.class);
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
        return view;
    }

   /* public boolean onOption(View view) {
        switch (view.getId()) {
            case R.id.fire_station_card_view:
                Intent intent=new Intent(getActivity(),FireStations.class);
                startActivity(intent);
                break;
            case R.id.feed_back_card_view:
                Intent intent1=new Intent(getActivity(),FeedBack.class);
                startActivity(intent1);
                break;
            case R.id.police_stations_card_view:
                Intent intent2=new Intent(getActivity(),PoliceStations.class);
                startActivity(intent2);
                break;
            case R.id.medical_emergency_card_view:
                Intent intent3=new Intent(getActivity(),EmergencyMedical.class);
                startActivity(intent3);
                break;
            case R.id.sexual_harassment_card_view:
                Intent intent4=new Intent(getActivity(),SexualHarassment.class);
                startActivity(intent4);
                break;
            case R.id.traffic_accident_card_view:
                Intent intent5=new Intent(getActivity(),TrafficAccidents.class);
                startActivity(intent5);
                break;
        }
    }*/
}


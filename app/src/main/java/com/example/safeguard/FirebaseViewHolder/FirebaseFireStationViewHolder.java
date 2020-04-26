package com.example.safeguard.FirebaseViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebaseFireStationViewHolder extends RecyclerView.ViewHolder  {
    public TextView FireStationListName,FireStationListPhoneNumber,FireStationListEmail,FireStationListLocation;
    public FirebaseFireStationViewHolder(View itemView) {
        super(itemView);
        FireStationListName=itemView.findViewById(R.id.fire_station_list_name);
        FireStationListPhoneNumber=itemView.findViewById(R.id.fire_station_list_phone);
        FireStationListEmail=itemView.findViewById(R.id.fire_station_list_email);
        FireStationListLocation=itemView.findViewById(R.id.fire_station_list_location);
    }
}
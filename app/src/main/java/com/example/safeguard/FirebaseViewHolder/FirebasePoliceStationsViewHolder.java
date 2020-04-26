package com.example.safeguard.FirebaseViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebasePoliceStationsViewHolder extends RecyclerView.ViewHolder  {
    public TextView PoliceStationListName,PoliceStationListPhoneNumber,PoliceStationListEmail,PoliceStationListLocation;
    public FirebasePoliceStationsViewHolder(View itemView) {
        super(itemView);
        PoliceStationListName=itemView.findViewById(R.id.police_station_list_name);
        PoliceStationListPhoneNumber=itemView.findViewById(R.id.police_station_list_phone_number);
        PoliceStationListEmail=itemView.findViewById(R.id.police_station_list_email);
        PoliceStationListLocation=itemView.findViewById(R.id.police_station_list_location);
    }
}

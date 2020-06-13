package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebasePoliceStationsViewHolder extends RecyclerView.ViewHolder  {
    public TextView PoliceStationListName,PoliceStationListPhoneNumber,PoliceStationListEmail,PoliceStationListLocation;
    public ImageView PoliceStationListCall;
    public FirebasePoliceStationsViewHolder(View itemView) {
        super(itemView);
        PoliceStationListName=itemView.findViewById(R.id.police_station_list_name);
        PoliceStationListPhoneNumber=itemView.findViewById(R.id.police_station_list_phone_number);
        PoliceStationListEmail=itemView.findViewById(R.id.police_station_list_email);
        PoliceStationListLocation=itemView.findViewById(R.id.police_station_list_location);
        PoliceStationListCall=itemView.findViewById(R.id.police_station_list_dial_call);
    }
}

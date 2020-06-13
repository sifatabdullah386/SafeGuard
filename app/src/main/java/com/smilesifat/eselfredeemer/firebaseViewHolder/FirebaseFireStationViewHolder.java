package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebaseFireStationViewHolder extends RecyclerView.ViewHolder  {
    public TextView FireStationListName,FireStationListPhoneNumber,FireStationListEmail,FireStationListLocation;
    public ImageView FireStationListCall;
    public FirebaseFireStationViewHolder(View itemView) {
        super(itemView);
        FireStationListName=itemView.findViewById(R.id.fire_station_list_name);
        FireStationListPhoneNumber=itemView.findViewById(R.id.fire_station_list_phone);
        FireStationListEmail=itemView.findViewById(R.id.fire_station_list_email);
        FireStationListLocation=itemView.findViewById(R.id.fire_station_list_location);
        FireStationListCall=itemView.findViewById(R.id.fire_services_list_dial_call);
    }
}

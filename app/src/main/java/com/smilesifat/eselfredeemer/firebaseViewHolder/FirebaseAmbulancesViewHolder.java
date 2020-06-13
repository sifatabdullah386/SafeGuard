package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebaseAmbulancesViewHolder extends RecyclerView.ViewHolder {
    public TextView AmbulancesListName,AmbulancesListPhoneNumber,AmbulancesListEmail,AmbulancesListType,AmbulancesListLocation;
    public ImageView AmbulancesListCall;
    public FirebaseAmbulancesViewHolder(View itemView) {
        super(itemView);
        AmbulancesListName=itemView.findViewById(R.id.ambulance_name);
        AmbulancesListPhoneNumber=itemView.findViewById(R.id.ambulance_phone);
        AmbulancesListEmail=itemView.findViewById(R.id.ambulance_email);
        AmbulancesListType=itemView.findViewById(R.id.ambulance_type);
        AmbulancesListLocation=itemView.findViewById(R.id.ambulance_location);
        AmbulancesListCall=itemView.findViewById(R.id.ambulances_list_dial_call);

    }
}

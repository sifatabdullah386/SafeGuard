package com.example.safeguard.FirebaseViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebaseAmbulancesViewHolder extends RecyclerView.ViewHolder {
    public TextView AmbulancesListName,AmbulancesListPhoneNumber,AmbulancesListEmail,AmbulancesListType,AmbulancesListLocation;
    public FirebaseAmbulancesViewHolder(View itemView) {
        super(itemView);
        AmbulancesListName=itemView.findViewById(R.id.ambulance_name);
        AmbulancesListPhoneNumber=itemView.findViewById(R.id.ambulance_phone);
        AmbulancesListEmail=itemView.findViewById(R.id.ambulance_email);
        AmbulancesListType=itemView.findViewById(R.id.ambulance_type);
        AmbulancesListLocation=itemView.findViewById(R.id.ambulance_location);

    }
}

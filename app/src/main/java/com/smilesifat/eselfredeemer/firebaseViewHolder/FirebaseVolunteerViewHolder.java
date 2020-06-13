package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebaseVolunteerViewHolder extends RecyclerView.ViewHolder {
    public TextView VolunteerListName,VolunteerListPhoneNumber,VolunteerListEmail,VolunteerListLocation;
    public ImageView VolunteerListCall;
    public FirebaseVolunteerViewHolder(View itemView) {
        super(itemView);
        VolunteerListName=itemView.findViewById(R.id.volunteer_list_name);
        VolunteerListPhoneNumber=itemView.findViewById(R.id.volunteer_list_phone);
        VolunteerListEmail=itemView.findViewById(R.id.volunteer_list_email);
        VolunteerListLocation=itemView.findViewById(R.id.volunteer_list_location);
        VolunteerListCall=itemView.findViewById(R.id.volunteer_list_dial_call);

    }
}

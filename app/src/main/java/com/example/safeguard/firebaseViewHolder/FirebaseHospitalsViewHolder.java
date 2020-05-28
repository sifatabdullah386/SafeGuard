package com.example.safeguard.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebaseHospitalsViewHolder extends RecyclerView.ViewHolder  {
public TextView HospitalListName,HospitalListPhoneNumber,HospitalListEmail,HospitalListType,HospitalListLocation;
    public ImageView HospitalListCall;
public FirebaseHospitalsViewHolder(View itemView) {
        super(itemView);
    HospitalListName=itemView.findViewById(R.id.hospital_list_name);
    HospitalListPhoneNumber=itemView.findViewById(R.id.hospital_list_phone_number);
    HospitalListEmail=itemView.findViewById(R.id.hospital_list_email);
    HospitalListType=itemView.findViewById(R.id.hospital_list_type);
    HospitalListLocation=itemView.findViewById(R.id.hospital_list_location);
    HospitalListCall=itemView.findViewById(R.id.hospital_list_dial_call);
        }
}

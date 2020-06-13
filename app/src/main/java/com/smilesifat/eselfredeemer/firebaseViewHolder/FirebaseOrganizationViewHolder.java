package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebaseOrganizationViewHolder extends RecyclerView.ViewHolder {
    public TextView OrganizationListName,OrganizationListPhoneNumber,OrganizationListEmail,OrganizationListType,OrganizationListLocation;
    public ImageView OrganizationListCall;
    public FirebaseOrganizationViewHolder(View itemView) {
        super(itemView);
        OrganizationListName=itemView.findViewById(R.id.organization_list_name);
        OrganizationListPhoneNumber=itemView.findViewById(R.id.organization_list_phone);
        OrganizationListEmail=itemView.findViewById(R.id.organization_list_email);
        OrganizationListType=itemView.findViewById(R.id.organization_list_type);
        OrganizationListLocation=itemView.findViewById(R.id.organization_list_location);
        OrganizationListCall=itemView.findViewById(R.id.organization_list_dial_call);

    }
}

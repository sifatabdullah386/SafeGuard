package com.example.safeguard;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
public class FirebaseOrganizationViewHolder extends RecyclerView.ViewHolder {
    public TextView OrganizationListName,OrganizationListPhoneNumber,OrganizationListEmail,OrganizationListType,OrganizationListLocation;
    public FirebaseOrganizationViewHolder(View itemView) {
        super(itemView);
        OrganizationListName=itemView.findViewById(R.id.organization_list_name);
        OrganizationListPhoneNumber=itemView.findViewById(R.id.organization_list_phone);
        OrganizationListEmail=itemView.findViewById(R.id.organization_list_email);
        OrganizationListType=itemView.findViewById(R.id.organization_list_type);
        OrganizationListLocation=itemView.findViewById(R.id.organization_list_location);

    }
}

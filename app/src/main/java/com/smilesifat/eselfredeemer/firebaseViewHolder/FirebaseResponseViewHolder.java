package com.smilesifat.eselfredeemer.firebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.smilesifat.eselfredeemer.R;

public class FirebaseResponseViewHolder  extends RecyclerView.ViewHolder {
    public TextView ResponseListName,ResponseListPhoneNumber,ResponseListMessage,ResponseListLocation,ResponseListCategory;
    public ImageView ResponseListCall;
    public FirebaseResponseViewHolder(View itemView) {
        super(itemView);
        ResponseListName=itemView.findViewById(R.id.response_list_name);
        ResponseListPhoneNumber=itemView.findViewById(R.id.response_list_phone_number);
        ResponseListMessage=itemView.findViewById(R.id.response_list_messages);
        ResponseListLocation=itemView.findViewById(R.id.response_list_location);
        ResponseListCall=itemView.findViewById(R.id.response_list_dial_call);
        ResponseListCategory=itemView.findViewById(R.id.response_list_category);

    }
}

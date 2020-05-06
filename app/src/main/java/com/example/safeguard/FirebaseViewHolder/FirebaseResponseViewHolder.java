package com.example.safeguard.FirebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebaseResponseViewHolder extends RecyclerView.ViewHolder {
    public TextView ResponseListName,ResponseListPhoneNumber,ResponseListMessages,ResponseListCategoryType,ResponseListLocation;
    public ImageView ResponseProfileImage;
    public LinearLayout ResponseItemClick;
    public FirebaseResponseViewHolder(View itemView) {
        super(itemView);
        ResponseListName=itemView.findViewById(R.id.response_list_name);
        ResponseListPhoneNumber=itemView.findViewById(R.id.response_list_phone_number);
        ResponseListMessages=itemView.findViewById(R.id.response_list_messages);
        ResponseProfileImage=itemView.findViewById(R.id.response_profile_image);
        ResponseListLocation=itemView.findViewById(R.id.response_list_location);
        ResponseListCategoryType=itemView.findViewById(R.id.response_list_category);
        ResponseItemClick=itemView.findViewById(R.id.item_click_response);
    }
}

package com.example.safeguard.FirebaseViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.R;

public class FirebaseRequestViewHolder   extends RecyclerView.ViewHolder {
        public TextView RequestListName,RequestListPhoneNumber,RequestListMessages,RequestListCategoryType,RequestListLocation;
        public ImageView RequestProfileImage;
        public LinearLayout RequestItemClick;
    public FirebaseRequestViewHolder(View itemView) {
            super(itemView);
            RequestListName=itemView.findViewById(R.id.request_list_name);
            RequestListPhoneNumber=itemView.findViewById(R.id.request_list_phone_number);
            RequestListMessages=itemView.findViewById(R.id.request_list_messages);
            RequestProfileImage=itemView.findViewById(R.id.request_profile_image);
            RequestListLocation=itemView.findViewById(R.id.request_list_location);
            RequestListCategoryType=itemView.findViewById(R.id.request_list_category);
            RequestItemClick=itemView.findViewById(R.id.item_click_request);
            }
    }

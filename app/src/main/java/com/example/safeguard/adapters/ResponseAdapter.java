package com.example.safeguard.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeguard.constractor.FreeConstructor;
import com.example.safeguard.R;
import com.example.safeguard.constractor.userDataConstructor;

import java.util.ArrayList;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.MyViewHolder> {
    private ArrayList<FreeConstructor> helpResponseList;
    private ArrayList<userDataConstructor> userDetailsList;

    public ResponseAdapter(Context context, ArrayList<FreeConstructor> helpResponseList,ArrayList<userDataConstructor> userDetailsList) {
        this.helpResponseList = helpResponseList;
        this.userDetailsList = userDetailsList;
    }
    @NonNull
    @Override
    public ResponseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview_response_recyclerview,parent,false);
        return new MyViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ResponseAdapter.MyViewHolder holder, int position) {
        final FreeConstructor help = helpResponseList.get(position);
        String category = "Help Request ";

        String messages=help.getMessage();
        String location=help.getLocation();

        holder.CategoryHelp.setText(category);
        holder.HelpMessages.setText(messages);
        holder.HelpLocation.setText(location);

        final userDataConstructor user = userDetailsList.get(position);
        String userName=user.getUserName();
        String phoneNumber=user.getPhoneNumber();

        holder.HelpUserName.setText(userName);
        holder.HelpPhoneNumber.setText(phoneNumber);
    }
    @Override
    public int getItemCount() {
        return helpResponseList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CategoryHelp;
        TextView HelpMessages,HelpLocation;
        TextView HelpUserName,HelpPhoneNumber;

        private MyViewHolder(View itemView) {
            super(itemView);
            CategoryHelp = itemView.findViewById(R.id.response_list_category);
            HelpMessages = itemView.findViewById(R.id.response_list_messages);
            HelpLocation = itemView.findViewById(R.id.response_list_location);
            HelpUserName = itemView.findViewById(R.id.response_list_name);
            HelpPhoneNumber = itemView.findViewById(R.id.response_list_phone_number);
        }
    }
}

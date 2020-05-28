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

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {
    private ArrayList<FreeConstructor> helpRequestList;

    public RequestAdapter(Context context, ArrayList<FreeConstructor> helpRequestList) {
        this.helpRequestList = helpRequestList;
    }
    @NonNull
    @Override
    public RequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview_request_recyclerview,parent,false);
        return new MyViewHolder(v);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RequestAdapter.MyViewHolder holder, int position) {
        final FreeConstructor help = helpRequestList.get(position);
        String category = "Help Request ";

        String messages=help.getMessage();
        String location=help.getLocation();

        holder.CategoryHelp.setText(category);
        holder.HelpMessages.setText(messages);
        holder.HelpLocation.setText(location);
    }
    @Override
    public int getItemCount() {
        return helpRequestList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView CategoryHelp;
        TextView HelpMessages,HelpLocation;

        private MyViewHolder(View itemView) {
            super(itemView);
            CategoryHelp = itemView.findViewById(R.id.request_list_category);
            HelpMessages = itemView.findViewById(R.id.request_list_messages);
            HelpLocation = itemView.findViewById(R.id.request_list_location);
        }
    }
}

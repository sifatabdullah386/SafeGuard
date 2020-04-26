package com.example.safeguard.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.safeguard.R;

public class addEmergencyContacts extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_emergency_contacts, container, false);

        EditText name1 = view.findViewById(R.id.add_emergency_contact_name1);
        EditText name2 = view.findViewById(R.id.add_emergency_contact_name2);
        EditText name3 = view.findViewById(R.id.add_emergency_contact_name3);

        EditText phoneNumber1 = view.findViewById(R.id.add_emergency_contact_phone_number1);
        EditText phoneNumber2 = view.findViewById(R.id.add_emergency_contact_phone_number2);
        EditText phoneNumber3 = view.findViewById(R.id.add_emergency_contact_phone_number3);

        Button ECSubmit = view.findViewById(R.id.add_emergency_contact_submit);

        ECSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}

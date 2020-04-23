package com.example.safeguard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogLogin extends AppCompatDialogFragment {

    private EditText LoginUserEmail,LoginUserPassword;
    private DialogLoginListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view =inflater.inflate(R.layout.dialog_login,null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String LoginEmail = LoginUserEmail.getText().toString().trim();
                        final String LoginPassword = LoginUserPassword.getText().toString().trim();
                        listener.applyTexts(LoginEmail,LoginPassword);
                    }
                });

        LoginUserEmail=view.findViewById(R.id.loginEmailEditText);
        LoginUserPassword=view.findViewById(R.id.loginPasswordEditText);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(DialogLoginListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+
                    "must implement DialogLoginListener");
        }
    }

    public  interface  DialogLoginListener{
        void applyTexts(String LoginEmail,String LoginPassword);
    }
}

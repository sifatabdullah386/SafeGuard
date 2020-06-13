package com.smilesifat.eselfredeemer.homeScreen;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.smilesifat.eselfredeemer.R;
import com.smilesifat.eselfredeemer.constractor.FreeConstructor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Request extends AppCompatActivity {

    TextView RequestMessages,RequestLocation,RequestCategory;
    ImageView RequestEdit;
    LinearLayout RequestLayout;
    DatabaseReference helpReference = FirebaseDatabase.getInstance().getReference().child("Requests");
    private String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Toolbar toolbar = findViewById(R.id.request_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        RequestMessages=findViewById(R.id.request_list_messages);
        RequestLocation=findViewById(R.id.request_list_location);
        RequestCategory=findViewById(R.id.request_list_category);
        RequestEdit=findViewById(R.id.request_list_edit);
        RequestLayout =findViewById(R.id.request_list_layout);

        RequestEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChildRemoved();
            }
        });
        helpReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    final FreeConstructor u =dataSnapshot.getValue(FreeConstructor.class);
                    String requestMessages= u.getMessage();
                    String requestLocation= u.getLocation();
                    String requestCategory= u.getRequestType();

                    RequestMessages.setText(requestMessages);
                    RequestLocation.setText(requestLocation);
                    RequestCategory.setText(requestCategory);

                }
                else{
                    RequestLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getChildRemoved() {
        helpReference.orderByKey().equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot :dataSnapshot.getChildren()) {
                    //String key = postSnapshot.getKey();
                    postSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

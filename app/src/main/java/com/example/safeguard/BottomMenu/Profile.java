package com.example.safeguard.BottomMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.safeguard.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.safeguard.Constractor.userDataConstructor;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class Profile extends Fragment {

    private static final int CAMERA_REQUEST_CODE =201 ;
    private TextView ProfileName;
    private TextView profilePhoneNumber;
    private TextView profileEmail;
    private TextView profileLocation;
    private TextView profileRequest;
    private TextView profileResponse;
    private ImageView CaptureImage,ProfileSettings;
    private FirebaseAuth fAuth;
    private DatabaseReference userReference;
    private StorageReference userStorageReference;
    private String uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileName=view.findViewById(R.id.profile_name);
        profilePhoneNumber = view.findViewById(R.id.profile_phone);
        profileEmail = view.findViewById(R.id.profile_email);
        profileLocation = view.findViewById(R.id.profile_location);
        CaptureImage = view.findViewById(R.id.profile_image);
        profileRequest = view.findViewById(R.id.profile_send_request);
        profileResponse = view.findViewById(R.id.profile_response);
        ProfileSettings = view.findViewById(R.id.go_profile_settings);

        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getCurrentUser().getUid();
        userStorageReference=FirebaseStorage.getInstance().getReference().child("profileImages/");
        userReference = FirebaseDatabase.getInstance().getReference("Users").child("Info").child(uid);

        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final userDataConstructor u =dataSnapshot.getValue(userDataConstructor.class);
                String profileUserName= u.getUserName();
                String profileUserPhoneNumber= u.getPhoneNumber();
                String profileUserEmail= u.getEmail();
                String profileUserLocation= u.getUserLocation();

                ProfileName.setText(profileUserName);
                profilePhoneNumber.setText(profileUserPhoneNumber);
                profileEmail.setText(profileUserEmail);
                profileLocation.setText(profileUserLocation);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        CaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_REQUEST_CODE){
            if (requestCode == RESULT_OK) {
                Uri ImageData=data.getData();
                final StorageReference ImageName=userStorageReference.child("image"+ImageData.getLastPathSegment());

                ImageName.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getActivity(),"Upload Successfully",Toast.LENGTH_SHORT).show();
 /*                       ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference profileImages=FirebaseDatabase.getInstance().getReference("userProfile").child(uid);
                                HashMap<String, String> hashMap=new HashMap<>();
                                hashMap.put("ImageUrl", String.valueOf(hashMap));
                                profileImages.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getActivity(),"Upload Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });*/
                    }
                });
            }
        }
    }

    //for settings menu items multiple selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_profile:
                Toast.makeText(getContext(),"Update Profile",Toast.LENGTH_SHORT).show();
                break;
            case R.id.your_requests:
                Toast.makeText(getContext(),"Your Request",Toast.LENGTH_SHORT).show();
                break;
            case R.id.your_response:
                Toast.makeText(getContext(),"Your Response",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
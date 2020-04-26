package com.example.safeguard.bottommenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.safeguard.constractor.userDataConstructor;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile extends Fragment {

    private TextView ProfileName;
    private TextView profilePhoneNumber;
    private TextView profileEmail;
    private TextView profileLocation;
    private TextView profileRequest;
    private TextView profileResponse;
    private ImageView CaptureImage,ProfileSettings;
    private FirebaseAuth fAuth;
    private DatabaseReference userReference;
    private StorageReference storageReference;
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
        storageReference = FirebaseStorage.getInstance().getReference();

        uid = fAuth.getCurrentUser().getUid();

        userReference = FirebaseDatabase.getInstance().getReference("Users").child("Info").child(uid);
        userStorageReference = FirebaseStorage.getInstance().getReferenceFromUrl("Users").child(uid).child("profile.jpg");

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
                // open gallery
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);

            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                //profileImage.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        // Upload image to firebase storage
        final StorageReference fileRef = storageReference.child("Users/"+uid+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(CaptureImage);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public  void onStart(){
        super.onStart();

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

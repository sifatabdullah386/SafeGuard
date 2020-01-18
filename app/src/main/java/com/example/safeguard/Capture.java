package  com.example.safeguard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Capture extends Fragment {

    private ImageView captureImage;
    private static final int CAMERA_PERM_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int GALLERY_REQUEST_CODE = 105;
    private StorageReference storageReference;
    private String currentPhotoPath;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capture, container, false);

        storageReference = FirebaseStorage.getInstance().getReference();

        captureImage = view.findViewById(R.id.image_capture);
        Button Capture = view.findViewById(R.id.image_capture_button);
        Button Upload = view.findViewById(R.id.image_upload_button);

        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });
        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
            }
        });
        return view;
    }

    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else{
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==CAMERA_PERM_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
              openCamera();
            }
            else{
                Toast.makeText(getActivity(), "Camera open Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
    }
    @SuppressLint("SimpleDateFormat")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      if(requestCode==CAMERA_REQUEST_CODE && resultCode== Activity.RESULT_OK ){

          File f=new File(currentPhotoPath);
          //captureImage.setImageURI(Uri.fromFile(f));
          Log.d("tag","Url of Image is"+Uri.fromFile(f));

          Intent mediaScanIntent=new Intent (Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
          Uri contentUri=Uri.fromFile(f);
          getActivity().sendBroadcast(mediaScanIntent);

          uploadImageToServer(f.getName(),contentUri);

        /*assert data != null;
        Bitmap bitmap=(Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
        captureImage.setImageBitmap(bitmap);*/

      }
        if(requestCode==GALLERY_REQUEST_CODE && resultCode== Activity.RESULT_OK ){
            assert data != null;
            Uri contentUri = data.getData();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp +"."+getFileExt(contentUri);
            Log.d("tag", "onActivityResult: Gallery Image Uri:  " +  imageFileName);
            //captureImage.setImageURI(contentUri);

            uploadImageToServer(imageFileName,contentUri);
        }
    }
    private void uploadImageToServer(String name, Uri contentUri) {
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        final StorageReference image = storageReference.child("User_uploaded_pictures/").child(uid).child(name);
        image.putFile(contentUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        image.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri).into(captureImage);
                                    }
                                });
                        Toast.makeText(getActivity(), "Image Is Uploaded.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Upload Failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private String getFileExt(Uri contentUri) {
        ContentResolver c = Objects.requireNonNull(getActivity()).getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    @SuppressLint("SimpleDateFormat")
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ignored) {

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getActivity(), "sifatabdullah386@gmail.com",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
            }
        }
    }

}

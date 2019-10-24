package  com.example.safeguard;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static androidx.media.MediaBrowserServiceCompat.RESULT_OK;

public class Capture extends Fragment {

    ImageView CaptureImage;
    Button Capture;
    private static final int CAMERA_REQUEST_CODE=1;
    StorageReference picStorage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_capture, container, false);

        picStorage= FirebaseStorage.getInstance().getReference();
        CaptureImage = view.findViewById(R.id.image_capture);
        Capture = view.findViewById(R.id.image_capture_button);
        Capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap=(Bitmap) data.getExtras().get("data");
        CaptureImage.setImageBitmap(bitmap);

        /*if(requestCode==CAMERA_REQUEST_CODE && resultCode==Activity.RESULT_OK){
            assert data != null;
            Uri uri=data.getData();
            assert uri != null;
            final StorageReference filepath=mStorage.child("Photos").child(Objects.requireNonNull(uri.getLastPathSegment()));
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri= taskSnapshot.getUploadSessionUri();
                    Picasso.get().load(downloadUri).fit().centerCrop().into(CaptureImage);
                    Toast.makeText(getActivity(),"Upload Finished",Toast.LENGTH_LONG).show();
                }
            });
        }*/
    }
}

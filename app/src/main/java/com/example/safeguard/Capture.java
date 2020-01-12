package  com.example.safeguard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class Capture extends Fragment {

    private ImageView CaptureImage;
    private Button Capture;
    private static final int CAMERA_REQUEST_CODE=1;
    private StorageReference picStorage;
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

/*        if(requestCode==CAMERA_REQUEST_CODE && resultCode==RESULT_OK){
            assert data != null;
            Uri uri=data.getData();
            assert uri != null;
            StorageReference filepath=picStorage.child("Captures").child(Objects.requireNonNull(uri.getLastPathSegment()));
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                }
            });
        }*/
        assert data != null;
        Bitmap bitmap=(Bitmap) data.getExtras().get("data");
        CaptureImage.setImageBitmap(bitmap);

    }
}

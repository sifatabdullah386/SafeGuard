package  com.example.safeguard;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CurrentLocation extends Fragment implements OnMapReadyCallback {

    GoogleMap gMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current_location, container, false);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        return view;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();    //Creating Marker
                markerOptions.position(latLng);     //set marker position
                markerOptions.title(latLng.latitude + ": " + latLng.longitude);      //set latitude and longitude on Marker
                gMap.clear();       //clear the previously click position
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));   //camera zoom
                gMap.addMarker(markerOptions);      //marker options
            }
        });
    }
}
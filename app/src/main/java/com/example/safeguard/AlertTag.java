package  com.example.safeguard;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class AlertTag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alert_tag, container, false);

        Button ButtonHelp=view.findViewById(R.id.button_help);
        CardView FireStation=view.findViewById(R.id.fire_station_card_view);
        ButtonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MapsActivity.class);
                startActivity(intent);
            }
        });
        FireStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),FireStations.class);
                startActivity(intent);
            }
        });
        return view;
    }

   /* public boolean onOption(View view) {
        switch (view.getId()) {
            case R.id.fire_station_card_view:
                Intent intent=new Intent(getActivity(),FireStations.class);
                startActivity(intent);
                break;
            case R.id.feed_back_card_view:
                Intent intent1=new Intent(getActivity(),FeedBack.class);
                startActivity(intent1);
                break;
            case R.id.police_stations_card_view:
                Intent intent2=new Intent(getActivity(),PoliceStations.class);
                startActivity(intent2);
                break;
            case R.id.medical_emergency_card_view:
                Intent intent3=new Intent(getActivity(),EmergencyMedical.class);
                startActivity(intent3);
                break;
            case R.id.sexual_harassment_card_view:
                Intent intent4=new Intent(getActivity(),SexualHarassment.class);
                startActivity(intent4);
                break;
            case R.id.traffic_accident_card_view:
                Intent intent5=new Intent(getActivity(),TrafficAccidents.class);
                startActivity(intent5);
                break;
        }
    }*/
}


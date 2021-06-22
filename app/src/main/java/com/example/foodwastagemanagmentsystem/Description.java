package com.example.foodwastagemanagmentsystem;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;

public class Description extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView uiEventName, uiEventType, uiEventPeoples, uiEventAddress, uiEventTime, uiEventDate, uiEventFoodMade, uiEventDressCode, uiContinental, uiDesi, uiLocal, uiChinese, uiFoodSaved;
    private MapView mapView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Users");

    String eventName, eventType, uId, userEmail, eventAddress, eventDate, eventLat, eventLon, eventPeoples, eventTime, eventFoodMade, eventDressCode;
    int eventFoodSaved;
    private ArrayList<String> foods;

    Double convertedLatitude, convertedLongitude;

    public Description() {
    }

    public Description(String eventName, String eventType, String uId, String userEmail, String eventAddress,
                       String eventDate, String eventLat, String eventLon, String eventPeoples, String eventTime,
                       String eventFoodMade, String eventDressCode, ArrayList<String> foods) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.uId = uId;
        this.userEmail = userEmail;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.eventLat = eventLat;
        this.eventLon = eventLon;
        this.eventPeoples = eventPeoples;
        this.eventTime = eventTime;
        this.eventFoodMade = eventFoodMade;
        this.eventDressCode = eventDressCode;
        this.foods = foods;
        this.eventFoodSaved = (Integer.parseInt(eventFoodMade) - Integer.parseInt(eventPeoples));
    }

    public static Description newInstance(String param1, String param2) {
        Description fragment = new Description();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Mapbox.getInstance(getContext().getApplicationContext(), getString(R.string.mapBoxKey));
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);

        //Text View
        uiEventName = view.findViewById(R.id.desc_event_name);
        uiEventType = view.findViewById(R.id.desc_event_type);
        uiEventPeoples = view.findViewById(R.id.desc_people);
        uiEventAddress = view.findViewById(R.id.desc_address);
        uiEventDate = view.findViewById(R.id.desc_event_date);
        uiEventTime = view.findViewById(R.id.desc_event_time);
        uiEventFoodMade = view.findViewById(R.id.desc_food_made);
        uiEventDressCode = view.findViewById(R.id.desc_dress_code);
        uiContinental = view.findViewById(R.id.continental_text);
        uiLocal = view.findViewById(R.id.local_text);
        uiChinese = view.findViewById(R.id.chinese_text);
        uiDesi = view.findViewById(R.id.desi_text);
        uiFoodSaved = view.findViewById(R.id.desc_food_saved);

        //Other
        mapView = view.findViewById(R.id.desc_map);

        //Calculation
        convertedLatitude = Double.parseDouble(eventLat);
        convertedLongitude = Double.parseDouble(eventLon);

        mapView.onCreate(savedInstanceState);
        mapView.setStyleUrl(Style.MAPBOX_STREETS);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.setTitle("Event Location");
                markerOptions.position(new LatLng(convertedLatitude, convertedLongitude));
                mapboxMap.addMarker(markerOptions);

                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(convertedLatitude, convertedLongitude))
                        .zoom(13)
                        .build());
            }
        });

        uiEventType.setText(eventType);
        uiEventName.setText(eventName);
        uiEventPeoples.setText(eventPeoples);
        uiEventAddress.setText(eventAddress);
        uiEventDate.setText(eventDate);
        uiEventTime.setText("Event Time " + eventTime);
        uiEventDressCode.setText(eventDressCode);
        uiEventFoodMade.setText(eventFoodMade);
        uiFoodSaved.setText("Food for " +eventFoodSaved + " peoples.");

        switch (foods.toArray().length) {
            case 4 :
                uiContinental.setText(foods.toArray()[0].toString());
                uiDesi.setText(foods.toArray()[1].toString());
                uiLocal.setText(foods.toArray()[2].toString());
                uiChinese.setText(foods.toArray()[3].toString());
                break;

            case 3 :
                uiContinental.setText(foods.toArray()[0].toString());
                uiDesi.setText(foods.toArray()[1].toString());
                uiLocal.setText(foods.toArray()[2].toString());
                break;

            case 2 :
                uiContinental.setText(foods.toArray()[0].toString());
                uiDesi.setText(foods.toArray()[1].toString());
                break;

            case 1 :
                uiContinental.setText(foods.toArray()[0].toString());
                break;
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}
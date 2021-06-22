package com.example.foodwastagemanagmentsystem;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.foodwastagemanagmentsystem.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import static androidx.core.content.ContextCompat.getSystemService;

public class CreateEventFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText eventName, eventType, eventDate, eventTime, eventAddress, eventPeoples, eventLat, eventLon, eventFoodMade, eventDressCode;
    CheckBox continental, desi, local, chinese;
    TimePickerDialog timePicker;
    DatePickerDialog datePicker;
    Button createEventBtn;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private String mParam1;
    private String mParam2;

    public CreateEventFragment() {
    }

    public static CreateEventFragment newInstance(String param1, String param2) {
        CreateEventFragment fragment = new CreateEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);

        //EditText
        eventName = view.findViewById(R.id.event_name);
        eventType = view.findViewById(R.id.event_type);
        eventDate = view.findViewById(R.id.event_date);
        eventTime = view.findViewById(R.id.event_time);
        eventAddress = view.findViewById(R.id.event_address);
        eventLat = view.findViewById(R.id.event_address_lat);
        eventLon = view.findViewById(R.id.event_address_lon);
        eventPeoples = view.findViewById(R.id.event_peoples);
        eventFoodMade = view.findViewById(R.id.event_food_made);
        eventDressCode = view.findViewById(R.id.event_dress_code);

        //CheckBox
        continental = view.findViewById(R.id.continental_check);
        desi = view.findViewById(R.id.desi_check);
        chinese = view.findViewById(R.id.chinese_check);
        local = view.findViewById(R.id.local_check);

        //Button
        createEventBtn = view.findViewById(R.id.create_event_btn);

        //Other
        mAuth = FirebaseAuth.getInstance();

        eventTime.setInputType(InputType.TYPE_NULL);
        eventDate.setInputType(InputType.TYPE_NULL);

        eventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                eventTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });

        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // time picker dialog
                datePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eventDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });


        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //Text
                String eventNameText = eventName.getText().toString();
                String eventTypeText = eventType.getText().toString();
                String eventAddressText = eventAddress.getText().toString();
                String eventLatText = eventLat.getText().toString();
                String eventLonText = eventLon.getText().toString();
                String eventPeoplesText = eventPeoples.getText().toString();
                String eventDateText = eventDate.getText().toString();
                String eventTimeText = eventTime.getText().toString();
                String eventDressText = eventDressCode.getText().toString();
                String eventFoodMadeText = eventFoodMade.getText().toString();
                ArrayList<String> foods = new ArrayList<>();
                String allText[] = {
                        eventNameText,
                        eventTypeText,
                        eventAddressText,
                        eventLatText,
                        eventLonText,
                        eventPeoplesText,
                        eventDateText,
                        eventTimeText,
                        eventDressText,
                        eventFoodMadeText
                };

                EditText allEditText[] = {
                        eventName,
                        eventType,
                        eventAddress,
                        eventLat,
                        eventLon,
                        eventPeoples,
                        eventDate,
                        eventTime,
                        eventDressCode,
                        eventFoodMade
                };

                if(continental.isChecked()){
                    foods.add(continental.getText().toString());
                }
                if(desi.isChecked()){
                    foods.add(desi.getText().toString());
                }
                if(local.isChecked()){
                    foods.add(local.getText().toString());
                }
                if(chinese.isChecked()){
                    foods.add(chinese.getText().toString());
                }

                if(eventNameText.isEmpty() || eventTypeText.isEmpty() || eventAddressText.isEmpty() || eventLatText.isEmpty() ||
                        eventLonText.isEmpty() || eventPeoplesText.isEmpty() || eventTimeText.isEmpty() || eventDateText.isEmpty()
                        || eventDressText.isEmpty() || eventFoodMadeText.isEmpty() || foods.isEmpty()) {

                    Utils utils = new Utils();
                    utils.validateAllEditText(allEditText, allText);
                    if(foods.isEmpty()) Toast.makeText(getContext(), "Please check the food menu", Toast.LENGTH_LONG).show();

                }else {
                    Event event = new Event(user.getUid(), eventNameText, eventTypeText, eventAddressText, eventPeoplesText, eventLatText,
                            eventLonText, eventDateText, eventTimeText, eventFoodMadeText, eventDressText, foods);
                    FirebaseDatabase.getInstance().getReference("Events")
                            .child(eventName.getText().toString() + " " + FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(getContext(), Dashboard.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(getContext(), "Failed to create event.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return  view;
    }
}
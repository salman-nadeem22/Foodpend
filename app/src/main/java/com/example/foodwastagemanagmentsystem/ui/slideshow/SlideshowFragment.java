package com.example.foodwastagemanagmentsystem.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodwastagemanagmentsystem.Dashboard;
import com.example.foodwastagemanagmentsystem.R;
import com.example.foodwastagemanagmentsystem.User;
import com.example.foodwastagemanagmentsystem.UserInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Users");
    TextView userPhone, userName, userEmail, userCountry;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        userName = root.findViewById(R.id.uf_name);
        userPhone = root.findViewById(R.id.uf_phone);
        userEmail = root.findViewById(R.id.uf_email);
        userCountry = root.findViewById(R.id.uf_country);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)  {

                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                showData(dataSnapshot);
                if(user != null) {
                    userName.setText(dataSnapshot.child(user.getUid()).getValue(User.class).getName());
                    userPhone.setText(dataSnapshot.child(user.getUid()).getValue(User.class).getPhone());
                    userEmail.setText(user.getEmail());
                    userCountry.setText(dataSnapshot.child(user.getUid()).getValue(User.class).getCountry());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
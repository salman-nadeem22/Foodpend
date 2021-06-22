package com.example.foodwastagemanagmentsystem.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwastagemanagmentsystem.RecycleViewItemModel;
import com.example.foodwastagemanagmentsystem.RecyclerViewAdapter;
import com.example.foodwastagemanagmentsystem.ui.home.HomeViewModel;
import com.example.foodwastagemanagmentsystem.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    private FirebaseDatabase firebaseInstance = FirebaseDatabase.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<RecycleViewItemModel> options = new FirebaseRecyclerOptions.Builder<RecycleViewItemModel>()
                .setQuery(firebaseInstance.getReference().child("Events"), RecycleViewItemModel.class)
                .build();

        adapter = new RecyclerViewAdapter(options);
        recyclerView.setAdapter(adapter);
        return root;
    }

    public HomeFragment(){
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
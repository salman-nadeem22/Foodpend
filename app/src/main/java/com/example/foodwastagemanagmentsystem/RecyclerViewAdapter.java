package com.example.foodwastagemanagmentsystem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RecyclerViewAdapter extends FirebaseRecyclerAdapter<RecycleViewItemModel, RecyclerViewAdapter.MyViewHolder> {
    
    public RecyclerViewAdapter(@NonNull FirebaseRecyclerOptions<RecycleViewItemModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull final RecycleViewItemModel model) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        holder.rec_view_event_name.setText(model.getEventName());
        holder.rec_view_event_type.setText("Event type " + model.getEventType());
        holder.rec_view_user_email.setText(user.getEmail());

        switch (model.getEventType().toLowerCase()) {
            case "party" :
                holder.item_img.setImageResource(R.drawable.party);
                break;
            case "wedding" :
                holder.item_img.setImageResource(R.drawable.wedding);
                break;
            case "engagement":
                holder.item_img.setImageResource(R.drawable.engagement);
                break;
            case "festival":
            case "festivals":
                holder.item_img.setImageResource(R.drawable.festival);
                break;
            default:
                holder.item_img.setImageResource(R.drawable.other);
        }

        holder.item_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity
                        .getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new Description(model.getEventName(), model.getEventType(), model.getuId(),
                                        model.getUserEmail(), model.getEventAddress(), model.getEventDate(),
                                        model.getEventLat(), model.getEventLon(), model.getEventPeoples(),
                                        model.getEventTime(), model.getEventFoodMade(), model.getEventDressCode(), model.getFoods()))
                        .addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return  new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView item_img;
        TextView rec_view_event_name, rec_view_event_type, rec_view_user_email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.item_img);
            rec_view_event_name = itemView.findViewById(R.id.rec_view_event_name);
            rec_view_event_type = itemView.findViewById(R.id.rec_view_event_type);
            rec_view_user_email = itemView.findViewById(R.id.rec_view_user_email);
        }
    }
}

package com.example.cityguideapp.Common.LoginSignup;



import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.cityguideapp.R;


public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder> {
    private List<Place11> places;
    private Context context;

    public PlaceAdapter(List<Place11> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        final Place11 place = places.get(position);

        holder.textViewName.setText(place.getName());
        holder.textViewCategory.setText("Category: " + place.getCategory());
        holder.textViewPhoneNumber.setText("Phone: " + place.getPhoneNumber());
        holder.textViewEmail.setText("Email: " + place.getEmail());
        holder.textViewMapsLink.setText("Map Location: " + place.getMapsLink());

        holder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = place.getPhoneNumber();
                if (!phoneNumber.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Phone number not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = place.getEmail();
                if (!email.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
                    context.startActivity(Intent.createChooser(intent, "Send email"));
                } else {
                    Toast.makeText(context, "Email address not available", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mapsLink = place.getMapsLink();
                if (!mapsLink.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mapsLink));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Map location not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }



    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewCategory;
        public TextView textViewPhoneNumber;
        public TextView textViewEmail;
        public TextView textViewMapsLink;
        public Button buttonCall;
        public Button buttonEmail;
        public Button buttonMap;

        public PlaceViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewMapsLink = itemView.findViewById(R.id.textViewMapsLink);
            buttonCall = itemView.findViewById(R.id.buttonCall);
            buttonEmail = itemView.findViewById(R.id.buttonEmail);
            buttonMap = itemView.findViewById(R.id.buttonMap);
        }
    }
}



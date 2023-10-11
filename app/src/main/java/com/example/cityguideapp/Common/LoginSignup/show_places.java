package com.example.cityguideapp.Common.LoginSignup;

import android.os.Bundle;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;
        import com.example.cityguideapp.R;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.Query;
        import com.google.firebase.firestore.QuerySnapshot;
        import java.util.ArrayList;
        import java.util.List;

public class show_places extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaceAdapter placeAdapter;
    private FirebaseFirestore firestore;
    private Spinner categorySpinner;
    private String selectedCategory = "All"; // Default to show all categories

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_places);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeAdapter = new PlaceAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(placeAdapter);

        categorySpinner = findViewById(R.id.categorySpinner);

        // Set up the category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Add an item selected listener for the category spinner
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected category
                selectedCategory = categorySpinner.getSelectedItem().toString();

                // Fetch data from Firestore based on the selected category
                fetchPlacesFromFirestore();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    private void fetchPlacesFromFirestore() {
        // Query Firestore based on the selected category
        Query query;
        if (selectedCategory.equals("All")) {
            // Show all categories
            query = firestore.collection("places");
        } else {
            // Filter by the selected category
            query = firestore.collection("places").whereEqualTo("Category", selectedCategory);
        }

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<Place11> places = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        Place11 place = document.toObject(Place11.class);
                        places.add(place);
                    }
                    placeAdapter = new PlaceAdapter(places, show_places.this);
                    recyclerView.setAdapter(placeAdapter);
                } else {
                    // Handle errors here
                }
            }
        });
    }
}






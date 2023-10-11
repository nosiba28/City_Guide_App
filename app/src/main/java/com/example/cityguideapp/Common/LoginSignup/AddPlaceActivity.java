package com.example.cityguideapp.Common.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cityguideapp.Common.userhome;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import com.example.cityguideapp.R;

public class AddPlaceActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    private EditText nameEditText;
    private Spinner categorySpinner;
    private EditText phoneNumberEditText;
    private EditText emailEditText;
    private EditText descriptionEditText;
    private EditText mapsLinkEditText;
    private Button submitButton,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.editTextName);
        categorySpinner = findViewById(R.id.spinnerCategory);
        phoneNumberEditText = findViewById(R.id.editTextPhone);
        emailEditText = findViewById(R.id.editTextEmail);
        descriptionEditText = findViewById(R.id.editTextDescription);
        mapsLinkEditText = findViewById(R.id.editTextMapsLink);
        submitButton = findViewById(R.id.buttonSubmit);
        show = findViewById(R.id.buttonshow);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedCategory = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String category = categorySpinner.getSelectedItem().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String mapsLink = mapsLinkEditText.getText().toString();

                // Create a data object
                Map<String, Object> placeData = new HashMap<>();
                placeData.put("Name", name);
                placeData.put("Category", category);
                placeData.put("PhoneNumber", phoneNumber);
                placeData.put("Email", email);
                placeData.put("Description", description);
                placeData.put("MapsLink", mapsLink);

                // Add data to Firestore
                db.collection("places")
                        .add(placeData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddPlaceActivity.this, "Place added to Firestore", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddPlaceActivity.this, "Error adding place to Firestore", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPlaceActivity.this, show_places.class);
                startActivity(intent);
            }



        });


    }
}

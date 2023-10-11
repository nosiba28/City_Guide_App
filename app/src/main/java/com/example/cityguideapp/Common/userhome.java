package com.example.cityguideapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cityguideapp.Common.LoginSignup.AddPlaceActivity;
import com.example.cityguideapp.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguideapp.R;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class userhome extends AppCompatActivity implements View.OnClickListener{
    private Button logout,bt1;

    private FirebaseAuth firebaseAuth;
    private boolean canGoBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhome);
        firebaseAuth = FirebaseAuth.getInstance();
        logout= (Button) findViewById(R.id.logoutid);
        bt1= (Button) findViewById(R.id.bt1);


        // Button selectImagesButton = findViewById(R.id.imageBT);
        logout.setOnClickListener(this);
        bt1.setOnClickListener(this);



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        firestore.collection("users").document(userId).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String firstName = documentSnapshot.getString("firstName");
                        firstName=firstName + " ";
                        String lastName = documentSnapshot.getString("lastName");

                        String phoneNo = documentSnapshot.getString("phoneNo");
                        String email = documentSnapshot.getString("email");

                        // Populate UI elements with retrieved data
                        TextView firstNameTextView = findViewById(R.id.firstNameTextView);
                        TextView lastNameTextView = findViewById(R.id.lastNameTextView);
                        TextView phoneNoTextView = findViewById(R.id.phoneNoTextView);
                        TextView emailTextView = findViewById(R.id.emailTextView);

                        firstNameTextView.setText(firstName);
                        lastNameTextView.setText(lastName);
                        phoneNoTextView.setText(phoneNo);
                        emailTextView.setText(email);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle failure to retrieve data from Firestore
                });








    }

    public void onBackPressed() {
        if (!canGoBack) {
            // Prevent going back without logging out
            Toast.makeText(this, "please sign out.", Toast.LENGTH_SHORT).show();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.logoutid)
            logOut();
        else
        {
            Intent intent = new Intent(userhome.this, AddPlaceActivity.class);
            startActivity(intent);
        }


    }



    private void logOut() {
        firebaseAuth.signOut();
        finish();

        Toast.makeText(this, "Sign Out!.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(userhome.this, RetailerStartUpScreen.class);
        startActivity(intent);

    }
}
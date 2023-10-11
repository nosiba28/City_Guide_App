package com.example.cityguideapp.Common.LoginSignup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguideapp.Common.userhome;
import com.example.cityguideapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;


public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private Button btnsu11,btnsu12;
    private EditText email,password,firstNameEditText,lastNameEditText,phoneNoEditText;

    private FirebaseAuth firebaseAuth;
    //  private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        btnsu11 = (Button) findViewById(R.id.signupid11);
        btnsu12= (Button) findViewById(R.id.signinid11);
        email=(EditText) findViewById(R.id.userid11);
        password=(EditText) findViewById(R.id.passwordid11);

        firstNameEditText = findViewById(R.id.firstnameid);
        lastNameEditText = findViewById(R.id.lastnameid);
        phoneNoEditText = findViewById(R.id.phone_id);




        btnsu11.setOnClickListener(this);
        btnsu12.setOnClickListener(this);

    }



    public void onClick(View view) {
        if(view.getId()==R.id.signupid11)
        {

            userRegister();


        }
        else {

            Intent intent= new Intent(SignUp.this, test11.class);
            startActivity(intent);

        }


    }
    private void userRegister()
    {
        //Inside your signup activity/fragment


        // Initialization (inside onCreate or onCreateView method)


        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        String userFirstName = firstNameEditText.getText().toString();
        String userLastName = lastNameEditText.getText().toString();
        String userPhoneNo = phoneNoEditText.getText().toString();


        if(userFirstName.isEmpty())
        {
            firstNameEditText.setError("This field must not be empty.");
            firstNameEditText.requestFocus();
            return;

        }

        if(userLastName.isEmpty())
        {
            lastNameEditText.setError("This field must not be empty.");
            lastNameEditText.requestFocus();
            return;

        }

        if(userPhoneNo.isEmpty())
        {
            phoneNoEditText.setError("This field must not be empty.");
            phoneNoEditText.requestFocus();
            return;

        }

        if(userEmail.isEmpty())
        {
            email.setError("Enter an email address");
            email.requestFocus();
            return;

        }


        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches())
        {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        if(userPassword.length()<8)
        {

            password.setError("Please use a password at least of 6 characters");
            password.requestFocus();
            return;

        }


        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign-up successful

                // Save user details to Firestore
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                String userId = firebaseAuth.getCurrentUser().getUid();

                // Create a user object with the details


                // Save the user object to Firestore
                Map<String, Object> userData = new HashMap<>();
                userData.put("firstName", userFirstName);
                userData.put("lastName", userLastName);
                userData.put("phoneNo", userPhoneNo);
                userData.put("email", userEmail);

                // Save the userData to Firestore
                firestore.collection("users").document(userId).set(userData)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, userhome.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Failed to save user data to Firestore.", Toast.LENGTH_SHORT).show();
                        });




                Intent intent= new Intent(SignUp.this, userhome.class);
                startActivity(intent);
            } else {
                // Sign-up failed
                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    // User already exists with the same email
                    Toast.makeText(this, "User with this email already exists.", Toast.LENGTH_SHORT).show();
                } else {
                    // Other sign-up errors
                    Toast.makeText(this, "Sorry! registration isn't successful.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}

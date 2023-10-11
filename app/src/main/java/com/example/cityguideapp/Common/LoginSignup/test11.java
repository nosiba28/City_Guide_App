package com.example.cityguideapp.Common.LoginSignup;

/*import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cityguideapp.R;

public class test11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
    }
}*/





import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cityguideapp.Common.userhome;
import com.example.cityguideapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class test11 extends AppCompatActivity implements View.OnClickListener{
    private Button btnsi;
    private EditText email,password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);

        firebaseAuth = FirebaseAuth.getInstance();

        btnsi=(Button) findViewById(R.id.signinid);

        email=(EditText) findViewById(R.id.userid);
        password=(EditText) findViewById(R.id.passwordid);

        btnsi.setOnClickListener(this);


    }



    public void onClick(View view) {
        if(view.getId()==R.id.signinid)
        {

            login();



        }


    }

    private void login()
    {

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
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

            password.setError("Password is at least of 8 characters ");
            password.requestFocus();
            return;

        }

        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in successful



                        Intent intent = new Intent(test11.this, userhome.class);
                        startActivity(intent);
                        Toast.makeText(this, "Sign-in successful!", Toast.LENGTH_SHORT).show();

                    } else {
                        // Sign-in failed
                        email.setError( "Invalid email or password.");
                    }
                });
    }


}


package com.example.cityguideapp.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.cityguideapp.R;

public class Banks extends AppCompatActivity {
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);

        backBtn = findViewById(R.id.back_pressed);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Banks.super.onBackPressed();
            }
        });
    }
}

package com.example.cityguideapp.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.cityguideapp.R;

public class RetailerStartUpScreen extends AppCompatActivity implements View.OnClickListener{
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_start_up_screen);
    bt1 = findViewById(R.id.login_btn);
    bt2 = findViewById(R.id.signup_btn);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

    }
  /*  public void callLoginScreen(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);

        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn), "transition_login");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }*/

    public void onClick(View view) {
        if(view.getId()==R.id.login_btn)
        {

            Intent intent = new Intent(RetailerStartUpScreen.this,test11.class);
            startActivity(intent);
            finish();


        }
        else {

            Intent intent = new Intent(RetailerStartUpScreen.this, SignUp.class);
            startActivity(intent);
        }


    }
//    public void callSignUpScreen(View view) {
//
//
//        Intent intent = new Intent(getApplicationContext(), Login.class);
//
//
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.login_btn), "transition_login");
//
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RetailerStartUpScreen.this, pairs);
//            startActivity(intent, options.toBundle());
//        } else {
//            startActivity(intent);
//        }
//
//
//    }
}
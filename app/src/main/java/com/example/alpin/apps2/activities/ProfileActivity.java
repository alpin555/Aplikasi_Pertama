package com.example.alpin.apps2.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.alpin.apps2.Storage.SharedPrefManager;
import com.example.alpin.apps2.R;
import com.example.alpin.apps2.models.User;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textView = findViewById(R.id.textView);

        User user = SharedPrefManager.getmInstance(this).getUser();

        textView.setText("Welcome back "+((User) user).getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getmInstance(this).isLoggedin()){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}

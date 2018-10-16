package com.example.alpin.apps2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alpin.apps2.Storage.SharedPrefManager;
import com.example.alpin.apps2.R;
import com.example.alpin.apps2.fragments.HomeFragment;
import com.example.alpin.apps2.fragments.SettingsFragment;
import com.example.alpin.apps2.fragments.UsersFragment;
import com.example.alpin.apps2.models.User;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_users:
                fragment = new UsersFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;
        }
        if (fragment != null){
            displayFragment(fragment);
        }
        return false;
    }
}

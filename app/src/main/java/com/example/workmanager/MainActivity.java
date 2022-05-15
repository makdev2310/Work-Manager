package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import Activities.Payroll.PayrollCal;
import Activities.User.UserInfo;
import Fragments.AboutFragment;
import Fragments.HomeFragment;
import Fragments.NotificationFragment;
import SignIn_SignUp.SaveSharedPreference;
import SignIn_SignUp.LoginActivity;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if (savedInstanceState == null){
            //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.HomePage);
        }
        btnHandler();
    }

    void init(){
        findViewById();
        NavigationBar();
    }

    void findViewById(){
        bottomNavigationView = findViewById(R.id.NavigationBar);
    }

    void btnHandler(){
    }

    void LogOut() {
        if (SaveSharedPreference.getUserName(this).isEmpty()) {
            Intent Login = new Intent(this, LoginActivity.class);
            startActivity(Login);
            finish();
        }
    }

    private void NavigationBar() {
        bottomNavigationView = findViewById(R.id.NavigationBar);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.NotificationPage:
                    selectedFragment = new NotificationFragment();
                    break;
                case R.id.HomePage:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.AboutPage:
                    selectedFragment = new AboutFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });
        bottomNavigationView.setOnItemReselectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.NotificationPage:
                    selectedFragment = new NotificationFragment();
                    break;
                case R.id.HomePage:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.AboutPage:
                    selectedFragment = new AboutFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogOut();
    }
}
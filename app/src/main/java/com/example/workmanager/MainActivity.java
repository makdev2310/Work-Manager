package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import Activities.Payroll.PayrollCal;
import Activities.User.UserInfo;
import SignIn_SignUp.SaveSharedPreference;
import SignIn_SignUp.loginActivity;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintPayroll, constraintLUserInfo, constraintLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if(savedInstanceState != null){
//            onResume();
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnHandler();
    }

    void init(){
        findViewById();
    }

    void findViewById(){
        constraintPayroll = findViewById(R.id.home_Payroll);
        constraintLUserInfo = findViewById(R.id.home_UserInfo);
        constraintLogin = findViewById(R.id.home_Login);
    }

    void btnHandler(){
        constraintPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PayrollCal.class);
                startActivity(intent);
            }
        });
        constraintLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });

        constraintLUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserInfo.class);
                startActivity(intent);
            }
        });
    }

    void LogOut() {
        if (SaveSharedPreference.getUserName(this).isEmpty()) {
            Intent Login = new Intent(this, loginActivity.class);
            startActivity(Login);
            finish();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogOut();
    }
}
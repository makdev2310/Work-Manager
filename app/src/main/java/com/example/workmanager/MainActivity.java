package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import Activities.Payroll.PayrollCal;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintPayroll, constraintLUserInfo;

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
    }

    void btnHandler(){
        constraintPayroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PayrollCal.class);
                startActivity(intent);
            }
        });
    }
}
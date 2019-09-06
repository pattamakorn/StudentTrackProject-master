package com.example.studenttrackproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class startapp extends AppCompatActivity {
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getBaseContext(),login.class);
                startActivity(i);
                finish();
            }
        };new Handler().postDelayed(runnable,3500);
    }
}

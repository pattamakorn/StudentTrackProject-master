package com.example.studenttrackproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    SessionManager sessionManager;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new home()).commit();
                    return true;
                case R.id.timetable:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new TimeTable()).commit();
                    return true;
                case R.id.checknames:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checkname()).commit();
                    return true;
                case R.id.transcript:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new transcript()).commit();
                    return true;
                case R.id.gps:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new maps()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new home()).commit();
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String,String> user = sessionManager.getUserDetail();
        String mid = user.get(sessionManager.STD_ID);
        String mname = user.get(sessionManager.NAME);
        String memail = user.get(sessionManager.USERNAME);
        String mclass = user.get(sessionManager.STD_CLASS);
        Toast.makeText(this, "Wellcome \n ID : "+mid+"\n Name : "+mname+"\n Username : "+memail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemtop) {
        int id = itemtop.getItemId();
        switch (id) {
            case R.id.qrcode_menu:
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new qrcode()).commit();
                return true;
            case R.id.logout_menu:
                sessionManager.logout();
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }
}

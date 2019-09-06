package com.example.studenttrackproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class checkname extends Fragment {
    private TextView datenow,hello;

public checkname() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkname, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datenow = getView().findViewById(R.id.datenow);
        hello = getView().findViewById(R.id.hello);
        // Current Date

        Calendar c = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat("dd/MM");
        String myear = year.format(c.getTime());
        int d = c.get(Calendar.YEAR)+543;
        String day = new SimpleDateFormat("EEEE").format(c.getTime());

        // txtResult
        datenow.setText(myear+"/"+d);
        hello.setText("สวัสดี"+day);
    }
}

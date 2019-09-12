package com.example.studenttrackproject;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeTable extends Fragment {
    private View view;
    private TextView day,date;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<timetableday> listtimetable;
    private static String URL_timetable = "http://192.168.64.2/android_register_login/timetable.php";

    private RecyclerView.LayoutManager recyclerViewlayoutManager;

    private RecyclerView.Adapter recyclerViewadapter;




    public TimeTable() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_table, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        day = getView().findViewById(R.id.day);
        date = getView().findViewById(R.id.date);
        // Current Date

        Calendar c = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat("dd/MM");
        String myear = year.format(c.getTime());
        int d = c.get(Calendar.YEAR)+543;
        String mday = new SimpleDateFormat("EEEE").format(c.getTime());

        // txtResult
        date.setText(myear+"/"+d);
        day.setText(mday);

        timetableja();

        listtimetable = new ArrayList<>();
        recyclerView = view.findViewById(R.id.showtimetable);

        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);

    }
        private void timetableja(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_timetable,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject posts = jsonArray.getJSONObject(i);
                                    listtimetable.add(new timetableday(
                                            posts.getString("idsubject"),
                                            posts.getString("subjectname"),
                                            posts.getString("teachsubject"),
                                            posts.getString("classroom"),
                                            posts.getString("starttime"),
                                            posts.getString("stoptime")
                                    ));
                                    timetabledayAdpter rvt = new timetabledayAdpter(view.getContext(),listtimetable);
                                    recyclerView.setAdapter(rvt);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error:"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id","593021017-2");
                params.put("term","1");
                params.put("year","2562");
                params.put("day1","จันทร์");
                params.put("day2","จันทร์");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}

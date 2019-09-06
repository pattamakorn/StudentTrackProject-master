package com.example.studenttrackproject;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {

    private TextView fullname,studentclass,tel,nameteach;
    private ImageView profile;
    private String mclass,teacher_name,tel_teacher;
    SessionManager sessionManager;
    private static String URL_Detail = "http://192.168.64.2/android_register_login/home.php";


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fullname = getView().findViewById(R.id.fullname);
        studentclass = getView().findViewById(R.id.classstudent);
        nameteach = getView().findViewById(R.id.myteacher);
        tel = getView().findViewById(R.id.tel_teacherClass);
        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();
        HashMap<String,String> user = sessionManager.getUserDetail();
        String mid = user.get(sessionManager.STD_ID);
        String mname = user.get(sessionManager.NAME);
        String memail = user.get(sessionManager.USERNAME);
        mclass = user.get(sessionManager.STD_CLASS);
        fullname.setText(mname);
        studentclass.setText("ชั้นมัธยมศึกษาปีที่"+" "+mclass);
        profile();
    }

    public void profile(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_Detail, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("read");
                    if (success.equals("1")){
                        for (int i = 0;i < jsonArray.length();i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            String firstname = object.getString("firstname").trim();
                            String lastname = object.getString("lastname").trim();
                            String telephone = object.getString("tel").trim();
                            tel.setText(telephone);
                            nameteach.setText(firstname+" "+lastname);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error:"+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error:"+error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("adviser",mclass);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }



}

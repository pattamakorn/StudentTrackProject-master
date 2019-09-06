package com.example.studenttrackproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class login extends AppCompatActivity {
    private Button buttonlogin;
    private EditText eusername,epassword;
    private static String URL_LOGIN = "http://192.168.64.2/android_register_login/login.php";
//private static String URL_LOGIN = "http://10.10.0.179/android_register_login/login.php";
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonlogin = findViewById(R.id.btnlogin);
        eusername = findViewById(R.id.editusername);
        epassword = findViewById(R.id.editpassword);
        sessionManager = new SessionManager(this);


        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String muser = eusername.getText().toString().trim();
                String mpass = epassword.getText().toString().trim();
                if (!muser.isEmpty() || !mpass.isEmpty()){
                    login(muser,mpass);
//                    Intent intent = new Intent(login.this,MainActivity.class);
//                    startActivity(intent);
                }else {
                    eusername.setError("Please Insert username");
                    epassword.setError("Please Insert password");
                }
            }
        });

    }

    private void login(final String user, final String pass){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")){
                                for (int i = 0;i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String username = object.getString("username").trim();
                                    String studentID = object.getString("stdid").trim();
                                    String classStudent = object.getString("class").trim();
                                    sessionManager.createSession(name,username,studentID,classStudent);
                                    Toast.makeText(login.this, "Hello"+name+" "+classStudent, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(login.this,MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login.this, "Error:"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",user);
                params.put("password",pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

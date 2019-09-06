package com.example.studenttrackproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String USERNAME = "USERNAME";
    public static final String STD_ID = "STUDENT_ID";
    public static final String STD_CLASS = "STUDENT_CLASS";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name,String username,String IDstd,String stdclass){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(USERNAME,username);
        editor.putString(STD_ID,IDstd);
        editor.putString(STD_CLASS,stdclass);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if (!this.isLogin()){
            Intent i = new Intent(context,login.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(NAME,sharedPreferences.getString(NAME,null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
        user.put(STD_ID,sharedPreferences.getString(STD_ID,null));
        user.put(STD_CLASS,sharedPreferences.getString(STD_CLASS,null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,login.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }
}

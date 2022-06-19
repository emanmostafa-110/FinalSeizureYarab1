package com.example.finalseizureyarab.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.finalseizureyarab.Models.User;
import com.example.finalseizureyarab.UI.Login;


public class SessionManager {

    private static final String SHARED_PREF_NAME = "userToken";
    private static final String KEY_firstName = "firstName";
    private static final String KEY_lastName = "lastName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_password = "password";
    private static final String KEY_city = "city";
    private static final String KEY_country = "country";
    private static final String KEY_gender = "gender";
    private static final String KEY_national_id = "national_id";
    private static final String KEY_phone = "phone";
    private static final String KEY_birth_day = "birth_day";
    private static final String KEY_TOKEN = "token";

    private  static  SessionManager mInstance;
    private static Context mCtx;


    public SessionManager(Context context) {
        mCtx = context;
    }

    public static synchronized SessionManager getInstance(Context context){
        if(mInstance == null){
            mInstance = new SessionManager(context);
        }
        return  mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_firstName,user.getFirstName());
        editor.putString(KEY_lastName,user.getLastName());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_password,user.getPassword());
        editor.putString(KEY_country,user.getCountry());
        editor.putString(KEY_city,user.getCity());
        editor.putString(KEY_national_id,user.getNational_id());
        editor.putString(KEY_gender,user.getGender());
        editor.putString(KEY_birth_day,user.getBirth_day());
        editor.putString(KEY_phone,user.getPhone());
        editor.putString(KEY_TOKEN,user.getToken());
        editor.apply();
    }

    public  boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN,null) != null;
    }

    public User getToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_firstName, null), sharedPreferences.getString(KEY_lastName, null), sharedPreferences.getString(KEY_EMAIL, null), sharedPreferences.getString(KEY_password, null), sharedPreferences.getString(KEY_country, null), sharedPreferences.getString(KEY_city, null), sharedPreferences.getString(KEY_national_id, null), sharedPreferences.getString(KEY_phone, null), sharedPreferences.getString(KEY_gender, null), sharedPreferences.getString(KEY_birth_day, null), sharedPreferences.getString(KEY_TOKEN,null)
        );
    }
    public void userLogout(){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }

    public User getUser(User user){
        SharedPreferences sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString(KEY_firstName,null),
                sharedPreferences.getString(KEY_lastName,null),
                sharedPreferences.getString(KEY_EMAIL,null),
                sharedPreferences.getString(KEY_password,null),
                sharedPreferences.getString(KEY_country,null),
                sharedPreferences.getString(KEY_city,null),
                sharedPreferences.getString(KEY_national_id,null),
                sharedPreferences.getString(KEY_phone,null),
                sharedPreferences.getString(KEY_gender,null),
                sharedPreferences.getString(KEY_birth_day,null),
                sharedPreferences.getString(KEY_TOKEN,null)
        );

    }
}

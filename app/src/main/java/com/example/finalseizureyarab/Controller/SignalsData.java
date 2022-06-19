package com.example.finalseizureyarab.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalseizureyarab.Models.Signals;


public class SignalsData {
    private static final String SHARED_PREF_NAME = "userToken";
    private static final String KEY_classification = "firstName";
    private static final String KEY_probabilityNon = "lastName";
    private static final String KEY_probabilitySeizure = "email";
    private static final String KEY_type = "password";
    private static final String KEY_TOKEN = "token";

    private  static  SignalsData mInstance3;
    private static Context mCtx3;

    public SignalsData(Context context) {
        mCtx3 = context;
    }

    public static synchronized SignalsData getInstance(Context context){
        if(mInstance3 == null){
            mInstance3 = new SignalsData(context);
        }
        return  mInstance3;
    }

    public void saveEMG(Signals signals){
        SharedPreferences sharedPreferences =
                mCtx3.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_classification,signals.getClassificationEMG());
        editor.putString(KEY_probabilityNon,signals.getProbabilityNonEMG());
        editor.putString(KEY_probabilitySeizure,signals.getProbabilitySeizureEMG());
        editor.putString(KEY_type,signals.getTypeEMG());
        editor.putString(KEY_TOKEN,signals.getToken());
        editor.apply();
    }

    public void saveEEG(Signals signals){
        SharedPreferences sharedPreferences =
                mCtx3.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_classification,signals.getClassificationEEG());
        editor.putString(KEY_probabilityNon,signals.getProbabilityNonEEG());
        editor.putString(KEY_probabilitySeizure,signals.getProbabilitySeizureEEG());
        editor.putString(KEY_type,signals.getTypeEEG());
        editor.putString(KEY_TOKEN,signals.getToken());
        editor.apply();
    }




}

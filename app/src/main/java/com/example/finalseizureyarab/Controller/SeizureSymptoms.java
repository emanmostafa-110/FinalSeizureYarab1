package com.example.finalseizureyarab.Controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalseizureyarab.Models.User;

public class SeizureSymptoms {
    private static final String SHARED_PREF_NAME = "userToken";
    private static final String KEY_et_1 = "et_1";
    private static final String KEY_et_2 = "ey_2";
    private static final String KEY_et_3 = "et_3";
    private static final String KEY_et_4 = "et_4";
    private static final String KEY_et_5 = "et_5";
    private static final String KEY_et_6 = "et_6";
    private static final String KEY_TOKEN = "token";

    private  static SeizureSymptoms mInstance2;
    private static Context mCtx2;

    public SeizureSymptoms(Context context) {
        mCtx2 = context;
    }
    public static synchronized SeizureSymptoms getInstance(Context context){
        if(mInstance2 == null){
            mInstance2 = new SeizureSymptoms(context);
        }
        return  mInstance2;
    }
    public void UserSaveSymptoms(User user){
        SharedPreferences sharedPreferences =
                mCtx2.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_et_1,user.getEt_1());
        editor.putString(KEY_et_2,user.getEt_2());
        editor.putString(KEY_et_3,user.getEt_3());
        editor.putString(KEY_et_4,user.getEt_4());
        editor.putString(KEY_et_5,user.getEt_5());
        editor.putString(KEY_et_6,user.getEt_6());
        editor.putString(KEY_TOKEN,user.getToken());
        editor.apply();
    }

    public User getSeizureSymptoms(User user){
        SharedPreferences sharedPreferences =
                mCtx2.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);

        return new User(
                sharedPreferences.getString(KEY_et_1,null),
                sharedPreferences.getString(KEY_et_2,null),
                sharedPreferences.getString(KEY_et_3,null),
                sharedPreferences.getString(KEY_et_4,null),
                sharedPreferences.getString(KEY_et_5,null),
                sharedPreferences.getString(KEY_et_6,null),
                sharedPreferences.getString(KEY_TOKEN,null)
        );

    }
}

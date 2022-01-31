package com.aqib.secupay.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aqib.secupay.R;


public class SharedPref {
    public Context context;
    private static final String TAG = "SharedPref";
    private static SharedPref sharedPref;


    private SharedPref(Context context) {
        super();
        this.context = context;

    }

    public static SharedPref getObj() {
        return sharedPref;
    }

    public static void initializeResources(Context context) {
        if (sharedPref == null) {
            sharedPref = new SharedPref(context);
        }
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(String.valueOf(R.string.mainFile), 0);
    }


    public int getCounter() {
        SharedPreferences pref = getSharedPreferences();
        return pref.getInt(String.valueOf(R.string.countr), 0);
    }

    public void setCounter(int counter) {
        SharedPreferences pref = getSharedPreferences();
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(String.valueOf(R.string.countr), counter);
        editor.apply();
    }



}

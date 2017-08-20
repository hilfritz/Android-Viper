package com.hilfritz.android.viper.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.hilfritz.android.viper.application.MyApplication;

/**
 *
 * @author Hilfritz on 1/12/15.
 * you Application class should have the following
 * <pre>
 * private SharedPreferences prefs;
 * prefs = PreferenceManager.getDefaultSharedPreferences(this);
 *
 * public SharedPreferences getPrefs() {
    return prefs;
   }
 * </pre>
 */
public class SharedPrefUtil {


    public static SharedPreferences getPreferences(Context context){
        return ((MyApplication) context.getApplicationContext()).getPrefs();
    }

    public static void updatePref(String prefKey, String prefValue, Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefKey, prefValue);
        if (prefs.contains(prefKey) ==false) {
            editor.commit();
        }else {
            editor.apply();
        }
    }
    public static String getPrefValue(String prefKey, Context context){
        SharedPreferences prefs = getPreferences(context);
        return prefs.getString(prefKey, null);

    }

    public static void updatePref(String prefKey, int prefValue, Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(prefKey, prefValue);
        if (prefs.contains(prefKey) ==false) {
            editor.commit();
        }else {
            editor.apply();
        }
    }

    public static void updatePref(String prefKey, long prefValue, Context context){
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(prefKey, prefValue);
        if (prefs.contains(prefKey) ==false) {
            editor.commit();
        }else {
            editor.apply();
        }
    }
    public static long getPrefValue(String prefKey, long defaultValue, Context context){
        SharedPreferences prefs = getPreferences(context);
        return prefs.getLong(prefKey, defaultValue);

    }

    /**
     *
     * @param prefKey
     * @param context
     * @return int -1 if not found
     */
    public static int getIntPrefValue(String prefKey, Context context){
        SharedPreferences prefs = getPreferences(context);
        return prefs.getInt(prefKey, -1);

    }

    /**
     *
     * @param prefKey
     * @param context
     * @return int -1 if not found
     */
    public static long getLongPrefValue(String prefKey, Context context){
        SharedPreferences prefs = getPreferences(context);
        return prefs.getLong(prefKey, 0l);

    }

}

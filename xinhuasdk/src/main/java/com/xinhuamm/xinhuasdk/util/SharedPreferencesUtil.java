package com.xinhuamm.xinhuasdk.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    protected SharedPreferences mSharedPreferences;
    protected SharedPreferences.Editor mEditor;

    public SharedPreferencesUtil(Context context, String spName) {
        this.mSharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        this.mEditor = mSharedPreferences.edit();
    }

    public String getString(String key, String defaultStr) {
        return mSharedPreferences.getString(key, defaultStr);
    }

    public boolean getBoolean(String key, boolean defaultBoolean) {
        return mSharedPreferences.getBoolean(key, defaultBoolean);
    }

    public int getInt(String key, int defaultInt) {
        return mSharedPreferences.getInt(key, defaultInt);
    }

    public long getLong(String key, long defaultLong) {
        return mSharedPreferences.getLong(key, defaultLong);
    }

    public float getFloat(String key, float defaultFloat) {
        return mSharedPreferences.getFloat(key, defaultFloat);
    }


    public void put(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public void put(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    public void put(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public void put(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public void put(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }

    public void remove(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }

    public boolean contains(String key) {
        return mSharedPreferences.contains(key);
    }

    public SharedPreferences getmSharedPreferences() {
        return mSharedPreferences;
    }

    public void setmSharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

}

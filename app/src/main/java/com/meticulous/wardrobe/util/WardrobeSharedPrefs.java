package com.meticulous.wardrobe.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by c74241 on 8/29/17.
 */

public class WardrobeSharedPrefs {

    private static final String PREFS_NAME = "wardrobeDataPrefs";
    private static final String PREFS_FIRST_TIME_DEFAULT_OUTFIT_LOAD = "default_outfits";

    private final SharedPreferences mPrefs;
    private final SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    public WardrobeSharedPrefs(Context context){
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    public boolean isFirstLoad() {
        return mPrefs.getBoolean(PREFS_FIRST_TIME_DEFAULT_OUTFIT_LOAD, true);
    }

    public void setFirstLoad(boolean firstLoad){
        mEditor.putBoolean(PREFS_FIRST_TIME_DEFAULT_OUTFIT_LOAD, firstLoad);
        mEditor.commit();
    }
}

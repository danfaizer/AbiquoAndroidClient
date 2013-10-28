package com.abiquo.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public final class AndroidUtils {

	 /**
	 * Private constructor to prevent instantiation
	 */
	private AndroidUtils() {}
    
	/**
	 * Check if app has been run once or not
	 */
	public static Boolean firstTimeRun(Context context) {
		SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		String firstTimeRunPref = appPreferences.getString("prefappfirstrun","true");
		if (firstTimeRunPref == "true")	return true;
		else return false;
	}
    
    /**
     * Change preference prefappfirstrun to false to 
     * confirm that app has been ran at least once
     */
	public static void setRunned(Context context) {
		SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor edit = appPreferences.edit();
		edit.putBoolean("prefappfirstrun", false);
		edit.commit();
		Log.i("AbiquoAndroidClient","INFO: Set preference firstRun to false");
	}
}

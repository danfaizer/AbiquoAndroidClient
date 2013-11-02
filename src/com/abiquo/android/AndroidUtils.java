package com.abiquo.android;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
		return appPreferences.getBoolean("prefappfirstrun",true);
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
		Log.i("Abiquo Viewer","INFO: Set preference firstRun to false");
	}

	public static void enableProgressSpinner(Fragment fragment){
		ProgressBar spinner = (ProgressBar) fragment.getView().findViewById(R.id.spinner);
		spinner.setVisibility(View.VISIBLE);      
	}

	public static void disableProgressSpinner(Fragment fragment){
		ProgressBar spinner = (ProgressBar) fragment.getView().findViewById(R.id.spinner);
		spinner.setVisibility(View.GONE);      
	}

}

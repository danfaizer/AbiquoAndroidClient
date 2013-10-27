package com.abiquo.android;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AbiquoAndroidClient extends Application {
	SharedPreferences userPreferences;

	 @Override
	 public void onCreate() {
	  super.onCreate();

	  Context mContext = this.getApplicationContext();
	  /*
	   * 0 = mode private. Only this App can read these preferences
	   */
	  userPreferences = mContext.getSharedPreferences("AbiquoAndroidClientPreferences", 0);

	  // the rest of your app initialization code goes here
	 }
	 
	 public boolean getFirstRun() {
	  return userPreferences.getBoolean("firstRun", true);
	 }

	 /*
	  * If app has been ran once, we set firstRun to false
	  */
	 public void setRunned() {
	  SharedPreferences.Editor edit = userPreferences.edit();
	  edit.putBoolean("firstRun", false);
	  edit.commit();
	 }
	  
}

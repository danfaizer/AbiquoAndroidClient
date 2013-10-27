package com.abiquo.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/*
 * Activities should extend from BaseActivity to allow use custom
 * methods (for example, GoogleAnalytincs, session management, etc.)
 */
public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
		setContentView(R.layout.mainactivity);
	
		/*
		 * Check if is 1st time application is run so we
		 * can show Preferences screen and show a disclaimer
		 * if is required, etc.
		 */
		
		 // 0 = mode private. Only this App can read these preferences
		 SharedPreferences appPreferences = this.getSharedPreferences("settings", 0);
		 if (appPreferences.getBoolean("prefappfirstrun", true)) {
			 setRunned(appPreferences);
	         Intent i = new Intent(this, PreferencesActivity.class);            
	         startActivity(i);	         
		 }

	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
 
        case R.id.menu_settings:
            Intent i = new Intent(this, PreferencesActivity.class);            
            startActivity(i);
            break;
        }
 
        return true;
    }

	public void setRunned(SharedPreferences appPreferences) {
		  SharedPreferences.Editor edit = appPreferences.edit();
		  edit.putBoolean("prefappfirstrun", false);
		  edit.commit();
	}
    
	@Override
	protected int getLayoutResourceId() {
		return R.layout.mainactivity;
	}
	
}

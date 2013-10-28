package com.abiquo.android;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Activities should extend from BaseActivity to allow use custom
 * methods (for example, GoogleAnalytincs, session management, etc.)
 */
public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
		setContentView(R.layout.mainactivity);
	
		/**
		 * Check if is 1st time application is run so we
		 * can show Preferences screen and show a disclaimer
		 * if is required, etc.
		 */		
		 
		 if (AndroidUtils.firstTimeRun(appContext)) {
			 Log.i("AbiquoAndroidClient","INFO: First time Abiquo Android Client is run");
			 AndroidUtils.setRunned(appContext);			 
		 }
		 
		 /**
		  * 
		  */
		 Button connect_button = (Button) findViewById(R.id.connect);
		 connect_button.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {
				 if (AbiquoUtils.checkCredentials(appContext)) {
					 new AlertDialog.Builder(appContext)
					    .setTitle("Good credentials!")
					    .setMessage("Good credentials!")
					    .show();
				 }
				 else {
					 new AlertDialog.Builder(appContext)
					    .setTitle("WRONG credentials!")
					    .setMessage("WRONG credentials!")
					    .show();
				 }
		    }
		 });		 
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
    
	@Override
	protected int getLayoutResourceId() {
		return R.layout.mainactivity;
	}
	
}

package com.abiquo.android;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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
		 
		 if (AndroidUtils.firstTimeRun(AbiquoApplication.getAbiquoAppContext())){
			 Log.i("AbiquoAndroidClient","INFO: First time Abiquo Android Client is run");
			 AndroidUtils.setRunned(AbiquoApplication.getAbiquoAppContext());
			 Intent i = new Intent(MainActivity.this, PreferencesActivity.class);
             startActivity(i);
		 }
		 
    	 FragmentManager fragmentManager = getFragmentManager();
    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    	 ResourcesFragment fragment = new ResourcesFragment();
    	 fragmentTransaction.add(R.id.fragment_container, fragment);
    	 fragmentTransaction.commit();	    	 
		 
		 /**
		  * 
		  */
    	 
		 ImageButton resourcesBtn = (ImageButton) findViewById(R.id.resourcesButton);
		 resourcesBtn.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {		    	 
		    	 FragmentManager fragmentManager = getFragmentManager();
		    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    	 ResourcesFragment fragment = new ResourcesFragment();
		    	 fragmentTransaction.replace(R.id.fragment_container, fragment);
		    	 fragmentTransaction.commit();		    	 
		    }
		 });

		 ImageButton virtualdatacenterBtn = (ImageButton) findViewById(R.id.virtualdatacenterButton);
		 virtualdatacenterBtn.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {		    	 
		    	 FragmentManager fragmentManager = getFragmentManager();
		    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    	 VirtualDataCenterFragment fragment = new VirtualDataCenterFragment();
		    	 fragmentTransaction.replace(R.id.fragment_container, fragment);
		    	 fragmentTransaction.commit();		    	 
		    }
		 });
		 
		 ImageButton eventsBtn = (ImageButton) findViewById(R.id.eventsButton);
		 eventsBtn.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {		    	 
		    	 FragmentManager fragmentManager = getFragmentManager();
		    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    	 EventsFragment fragment = new EventsFragment();
		    	 fragmentTransaction.replace(R.id.fragment_container, fragment);
		    	 fragmentTransaction.commit();		    	 
		    }
		 });
		 
		 ImageButton virtualappliancesBtn = (ImageButton) findViewById(R.id.virtualappliancesButton);
		 virtualappliancesBtn.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {		    	 
		    	 FragmentManager fragmentManager = getFragmentManager();
		    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    	 VirtualAppliancesFragment fragment = new VirtualAppliancesFragment();
		    	 fragmentTransaction.replace(R.id.fragment_container, fragment);
		    	 fragmentTransaction.commit();		    	 
		    }
		 });
		 
		 ImageButton enterprisesBtn = (ImageButton) findViewById(R.id.enterprisesButton);
		 enterprisesBtn.setOnClickListener(new View.OnClickListener() {
		     @Override
		     public void onClick(View v) {		    	 
		    	 FragmentManager fragmentManager = getFragmentManager();
		    	 FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		    	 EnterprisesFragment fragment = new EnterprisesFragment();
		    	 fragmentTransaction.replace(R.id.fragment_container, fragment);
		    	 fragmentTransaction.commit();		    	 
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

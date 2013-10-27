package com.abiquo.android;
 
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
 
public class PreferencesActivity extends PreferenceActivity {
 
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        
        /*
         * Remove app hidden preferences
         */
        PreferenceCategory hiddenPreferencesCategory = (PreferenceCategory) findPreference("pref_hidden");
        hiddenPreferencesCategory.removeAll();          
        
    }
}
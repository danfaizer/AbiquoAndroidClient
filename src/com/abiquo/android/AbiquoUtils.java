package com.abiquo.android;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class AbiquoUtils {
	
	 /**
     * Private constructor to prevent instantiation
     */
    private AbiquoUtils() {}
	
	public static Boolean correctCredentials(Context context) {
		HashMap<String, String> apiConnection = apiConnectionDetails(context);
		
		if (apiConnection != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static HashMap<String, String> apiConnectionDetails(Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        
        HashMap<String,String> apiConnection = new HashMap<String,String>();
        
        String api_url = appPreferences.getString("prefapiurl", "");
        String api_port = appPreferences.getString("prefapiport", "");
        String api_user = appPreferences.getString("prefuserusername", "");
        String api_password = appPreferences.getString("prefuserpassword", "");
        
        if (api_url.trim() != "" || api_port.trim() != "" && api_user.trim() != "" && api_password.trim() != "") {
        	apiConnection.put("api_url", api_url);
        	apiConnection.put("api_port", api_port);
        	apiConnection.put("api_user", api_user);
        	apiConnection.put("api_password", api_password);        	
        }
        else { apiConnection = null; }
        	        
		return apiConnection;		
	}
	
	
}

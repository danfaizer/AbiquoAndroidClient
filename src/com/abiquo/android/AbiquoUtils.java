package com.abiquo.android;


import java.util.HashMap;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public final class AbiquoUtils {

	/**
	 * Private constructor to prevent instantiation
	 */
	public AbiquoUtils() {}

	/*
	 * apiConnectionDetails returns in a HashMap api URI, api PORT, api SSL enabled,api USER, api PASSWORD
	 * extracted from shared preferences
	 */
	public static HashMap<String, String> apiConnectionDetails() {
		SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(AbiquoApplication.getAbiquoAppContext());

		HashMap<String,String> apiConnection = new HashMap<String,String>();

		String api_url = appPreferences.getString("prefapiurl", "");
		String api_port = appPreferences.getString("prefapiport", "443");
		String api_path = appPreferences.getString("prefapipath", "/api");
		String api_ssl = appPreferences.getString("prefapissl", "yes");
		String api_user = appPreferences.getString("prefuserusername", "admin");
		String api_password = appPreferences.getString("prefuserpassword", "xabiquo");

		if (api_ssl.trim() != "" && api_url.trim() != "" && api_port.trim() != "" && api_user.trim() != "" && api_password.trim() != "") {
			apiConnection.put("api_url", api_url);
			apiConnection.put("api_port", api_port);
			apiConnection.put("api_path", api_path);
			apiConnection.put("api_ssl", api_ssl);
			apiConnection.put("api_user", api_user);
			apiConnection.put("api_password", api_password);
		}
		else { apiConnection = null; }

		return apiConnection;		
	}

	public static String abiquoVersion(){
		SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(AbiquoApplication.getAbiquoAppContext());
		return appPreferences.getString("prefabiquoversion","2.6");
	}


}

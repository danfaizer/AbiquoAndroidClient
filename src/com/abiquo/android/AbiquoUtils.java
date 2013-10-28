package com.abiquo.android;

import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public final class AbiquoUtils {
	
	
	private static Context UtilsContext;
	
	 /**
     * Private constructor to prevent instantiation
     */
    public AbiquoUtils(Context context) {}
	
	public static Boolean checkCredentials(Context context) {
		
		UtilsContext = context;
		
		HashMap<String, String> apiConnection = apiConnectionDetails(context);
		
		if (apiConnection != null) {
			 String uri =  "http://"+apiConnection.get("api_url")+":"+apiConnection.get("api_port")+apiConnection.get("api_path")+"/login";
			 Log.d("AbiquoAndroidClien","INFO: HTTP Request URI: "+uri);
			 AsyncHttpClient client = new AsyncHttpClient();
			 client.addHeader("Accept", "application/vnd.abiquo.user+json; version=2.6;");
			 client.setBasicAuth(apiConnection.get("api_user"),apiConnection.get("api_password"));
			 client.get(uri,new AsyncHttpResponseHandler() {				 
				 
			     @Override
			     public void onSuccess(String response) {
			         Log.d("AbiquoAndroidClien","INFO: HTTP Response: "+response);
			     }
			     @Override
	             public void onFailure(Throwable e, String response) {			    	 
			    	 AlertDialog.Builder builder = new AlertDialog.Builder(UtilsContext);builder.setMessage("Error connecting to Abiquo API \n"+e.toString())
			         .setTitle("Error")
			         .setNeutralButton("OK", new DialogInterface.OnClickListener() {
			              public void onClick(DialogInterface dialog,int which) {
			                                        dialog.cancel();                                                    
			         }
			     }).create().show();
			    	 Log.d("AbiquoAndroidClien","ERROR: HTTP checkCredentials failed "+ e.toString() +" - "+ e.getCause());
	             }			     
			 });
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * apiConnectionDetails returns in a HashMap api URI, api PORT, api USER, api PASSWORD
	 * extracted from shared preferences
	 */
	private static HashMap<String, String> apiConnectionDetails(Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        
        HashMap<String,String> apiConnection = new HashMap<String,String>();
        
        String api_url = appPreferences.getString("prefapiurl", "");
        String api_port = appPreferences.getString("prefapiport", "");
        String api_path = appPreferences.getString("prefapipath", "");
        String api_user = appPreferences.getString("prefuserusername", "");
        String api_password = appPreferences.getString("prefuserpassword", "");
        
        if (api_url.trim() != "" || api_port.trim() != "" && api_user.trim() != "" && api_password.trim() != "") {
        	apiConnection.put("api_url", api_url);
        	apiConnection.put("api_port", api_port);
        	apiConnection.put("api_path", api_path);
        	apiConnection.put("api_user", api_user);
        	apiConnection.put("api_password", api_password);        	
        }
        else { apiConnection = null; }
        	        
		return apiConnection;		
	}
	
	
}

package com.abiquo.android;

import java.security.KeyStore;
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
			
			String api_protocol = "https";
			if (apiConnection.get("api_ssl").equalsIgnoreCase("no")) {  api_protocol= "http"; }

			String uri =  api_protocol+"://"+apiConnection.get("api_url")+":"+apiConnection.get("api_port")+apiConnection.get("api_path")+"/login";
			Log.i("AbiquoAndroidClien","HTTP Request URI: "+uri);
			 
			AsyncHttpClient client = new AsyncHttpClient();
			 
			 /**
			  *  Allow self-signed certificates and untrusted sites <--- THIS DECREASE SECURITY
			  */
			 try {
			      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			      trustStore.load(null, null);
			      CustomSSLSocketFactory sf = new CustomSSLSocketFactory(trustStore);
			      sf.setHostnameVerifier(CustomSSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			      client.setSSLSocketFactory(sf);   
			    }
			 catch (Exception e) {  		    }
		 			 
			 client.addHeader("Accept", "application/vnd.abiquo.user+json; version=2.6;");
			 client.setBasicAuth(apiConnection.get("api_user"),apiConnection.get("api_password"));
			 client.get(uri,new AsyncHttpResponseHandler() {				 
				 
			     @Override
			     public void onSuccess(String response) {
			         Log.i("AbiquoAndroidClien","HTTP Response: "+response);
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
			    	 Log.e("AbiquoAndroidClien","HTTP checkCredentials failed - "+ e.toString() +" - "+ e.getCause());
	             }			     
			 });
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * apiConnectionDetails returns in a HashMap api URI, api PORT, api SSL enabled,api USER, api PASSWORD
	 * extracted from shared preferences
	 */
	private static HashMap<String, String> apiConnectionDetails(Context context) {
        SharedPreferences appPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        
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
	
	
}

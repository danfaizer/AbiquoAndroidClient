package com.abiquo.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class CustomAsyncTask extends AsyncTask<String, Void, String> {

	private ProgressDialog dialog;
	private CustomAsyncTaskListener callback;

	public CustomAsyncTask(ResourcesFragment resourcesFragment) {
		this.callback = (CustomAsyncTaskListener)resourcesFragment;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

	}

	@Override
	protected String doInBackground(String... params) {
		return "XXXXAAAAXXXX";
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
		callback.onTaskComplete(result);
	}

}

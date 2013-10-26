package com.abiquo.android;

import android.os.Bundle;

/*
 * Activities should extend from BaseActivity to allow use custom
 * methods (for example, GoogleAnalytincs, session management, etc.)
 */
public class MainActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    
		setContentView(R.layout.mainactivity);
	}

	@Override
	protected int getLayoutResourceId() {
		return R.layout.mainactivity;
	}
}

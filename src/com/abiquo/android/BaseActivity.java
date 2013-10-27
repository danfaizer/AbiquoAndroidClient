package com.abiquo.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	
	public static Context appContext;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        
        appContext = this;
    }

    protected abstract int getLayoutResourceId();
}
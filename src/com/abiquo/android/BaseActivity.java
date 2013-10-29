package com.abiquo.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
	
	public static Context appContext;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        
        appContext = this;
    }

    protected abstract int getLayoutResourceId();
}
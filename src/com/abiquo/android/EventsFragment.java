package com.abiquo.android;

import java.lang.ref.WeakReference;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class EventsFragment extends Fragment implements GenericAsyncTaskListener {
		
	@SuppressWarnings("unused")
	private WeakReference<GenericAsyncTask> asyncTaskWeakRef;
	private GenericAsyncTask asyncTask;
	private static boolean asynccallrunning = false;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    	
        return inflater.inflate(R.layout.eventsfragment, container, false);       
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.eventsrefresh:
                Log.d("Abiquo Viewer","Refresh events");
                AndroidUtils.enableProgressSpinner(this);
                clearText();
                startNewAsyncTask("events","application/vnd.abiquo.events+json");
                return true;    
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
		 super.onActivityCreated(savedInstanceState);
		 setRetainInstance(true);
		 // Once category is load button is disabled
		 AndroidUtils.enableProgressSpinner(this);
		 disableMenuButton();
		 startNewAsyncTask("events","application/vnd.abiquo.events+json");     
		 Log.v("Abiquo Viewer", "Loading latest events data");
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.eventsmenu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    
    @Override
    public void onDestroyView() {
     super.onDestroyView();
     enableMenuButton();
     asyncTask.cancel(true);
     asynccallrunning = false;
    }
    
    private void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.resourcesTextView);
        textView.setText(text);
    }
    private void clearText(){
        TextView textView = (TextView) getView().findViewById(R.id.resourcesTextView);
        textView.setText("");
    }
    
    private void startNewAsyncTask(String ... params) {
        if (!asynccallrunning) {
	        asyncTask = new GenericAsyncTask(this);
	        this.asyncTaskWeakRef = new WeakReference<GenericAsyncTask >(asyncTask);
	        asynccallrunning = true;
	        asyncTask.execute(params);
        }
    }
    
	private void disableMenuButton(){
        ImageButton resourcesButton = (ImageButton) getActivity().findViewById(R.id.eventsButton);
        resourcesButton.setClickable(false);
        resourcesButton.setBackgroundColor(getResources().getColor(R.color.barbuttonactivecategory));
    }
    
    private void enableMenuButton(){
        ImageButton resourcesButton = (ImageButton) getActivity().findViewById(R.id.eventsButton);
        resourcesButton.setClickable(true);
        resourcesButton.setBackgroundColor(getResources().getColor(R.color.barbuttoncategory));
    }

	@Override
	public void onTaskComplete(String result) {
		AndroidUtils.disableProgressSpinner(this);
		setText(result);
		asynccallrunning = false;
		Log.v("Android Viewer", "Async tasc executed");
	}

}
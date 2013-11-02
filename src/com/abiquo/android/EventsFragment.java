package com.abiquo.android;

import java.lang.ref.WeakReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ListView;

public class EventsFragment extends Fragment implements GenericAsyncTaskListener {

	private WeakReference<GenericAsyncTask> asyncTaskWeakRef;
	private GenericAsyncTask asyncTask;
	private static boolean asynccallrunning = false;
	private EventsDAO eventsdatasource;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {    	
		return inflater.inflate(R.layout.eventsfragment, container, false);       
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		eventsdatasource = new EventsDAO(AbiquoApplication.getAbiquoAppContext());
		eventsdatasource.open();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.eventsrefresh:
			Log.d("Abiquo Viewer","Refresh events view");;
			AndroidUtils.enableProgressSpinner(this);
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
		eventsdatasource.close();
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
		asynccallrunning = false;		
		if (result != null) {
			eventsdatasource.purge();
			try{
				JSONObject EventsJSONObject = new JSONObject(result);
				JSONArray EventsJSONArray = (JSONArray) EventsJSONObject.get("collection");				
				for(int i=0;i<EventsJSONArray.length();i++){
					JSONObject EventJSONObject = EventsJSONArray.getJSONObject(i);
					eventsdatasource.createEvent(EventJSONObject.getLong("id"),
							EventJSONObject.getString("actionPerformed"),
							EventJSONObject.getString("component"),
							EventJSONObject.getString("enterprise"),
							EventJSONObject.getString("performedBy"),
							EventJSONObject.getString("severity"),
							EventJSONObject.getString("stacktrace"),
							EventJSONObject.getString("timestamp"));
				}
				ListView lstTest= (ListView)getActivity().findViewById(R.id.lstText);
				EventsAdapter eventsAdapter = new EventsAdapter (eventsdatasource.getAllEvents());
				lstTest.setAdapter(eventsAdapter);

				Log.i("Abiquo Viewer", "Events database data refreshed");
			} catch(JSONException e){  
				Log.e("Abiquo Viewer", "Error parsing Events JSON Data "+e.toString());  
			}  
		} else {
			// TO-DO: Show screen indicating no data can be shown
		}
	}

}
package com.abiquo.android;

import java.lang.ref.WeakReference;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResourcesFragment extends Fragment implements CustomAsyncTaskListener {
	
	@SuppressWarnings("unused")
	private WeakReference<CustomAsyncTask> asyncTaskWeakRef;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        return inflater.inflate(R.layout.resourcesfragment, container, false);
       
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
     super.onActivityCreated(savedInstanceState);
     setRetainInstance(true);
     // Once category is load button is disabled
     disableResourcesButton();
     startNewAsyncTask();
     
     Log.v("DetailFragment", "onActivityCreated()");
    }
    
    @Override
    public void onDestroyView() {
     super.onDestroyView();
     enableResourcesButton();
    }
    
    private void startNewAsyncTask() {
        CustomAsyncTask asyncTask = new CustomAsyncTask(this);
        this.asyncTaskWeakRef = new WeakReference<CustomAsyncTask >(asyncTask);
        asyncTask.execute();
    }

    void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.resourcesTextView);
        textView.setText(text);
    }

	void disableResourcesButton(){
        ImageButton resourcesButton = (ImageButton) getActivity().findViewById(R.id.resourcesButton);
        resourcesButton.setClickable(false);
        resourcesButton.setBackgroundColor(getResources().getColor(R.color.barbuttonactivecategory));
    }
    
    void enableResourcesButton(){
        ImageButton resourcesButton = (ImageButton) getActivity().findViewById(R.id.resourcesButton);
        resourcesButton.setClickable(true);
        resourcesButton.setBackgroundColor(getResources().getColor(R.color.barbuttoncategory));
    }

	@Override
	public void onTaskComplete(String result) {
		setText(result);
	}

}
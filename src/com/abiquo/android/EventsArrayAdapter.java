package com.abiquo.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

class EventsAdapter extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private final JSONArray jsonArray;
    EventsAdapter (Activity activity, JSONArray jsonArray) {
        assert activity != null;
        assert jsonArray != null;

        this.jsonArray = jsonArray;
        this.activity = activity;
    }

    @Override public int getCount() {
        if(null==jsonArray) 
         return 0;
        else
        return jsonArray.length();
    }

    @Override public JSONObject getItem(int position) {
         if(null==jsonArray) return null;
         else
           return jsonArray.optJSONObject(position);
    }

    @Override public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);

        return jsonObject.optLong("id");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.eventlistitem, null);

        TextView enterpriseName =(TextView)convertView.findViewById(R.id.eventEnterpriseName);
        TextView eventTimestamp =(TextView)convertView.findViewById(R.id.eventTimestamp);
        TextView eventActionPerformed =(TextView)convertView.findViewById(R.id.eventActionPerformed);
        TextView eventSeverity =(TextView)convertView.findViewById(R.id.eventSeverity);
                    JSONObject json_data = getItem(position);  
                    if(null!=json_data ){
                    
					try {
						enterpriseName.setText(json_data.getString("enterprise"));
						eventTimestamp.setText(json_data.getString("timestamp"));
						eventActionPerformed.setText(json_data.getString("actionPerformed"));
						eventSeverity.setText(json_data.getString("severity"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                     
                   }

         return convertView;
    }
}
package edu.sjsu.pingpong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GcmHttpClient extends AsyncTask<Integer, Void, Void> {
	private static final String BASE_URL = "https://android.googleapis.com/gcm/send";
	private static String TAG = "CUSTOM_LOG";
	private HttpClient client;
	private HttpPost httpPost;
	private String regId;

	private GcmHttpClient() {}
	
	public GcmHttpClient(String id) {
		client = new DefaultHttpClient();
		httpPost = new HttpPost(BASE_URL);
		
		regId = id;
	}
	
	//doInBackground(x,y,xD,yD)
	@Override
	protected Void doInBackground(Integer ... values) {
		try {
			if(values.length < 4) return null;
			
			Map<String, Object> params = new HashMap<String, Object>();
			List<String> registration_ids = new ArrayList<String>();
			Map<String, Integer> data = new HashMap<String, Integer>();
			
			data.put("x", values[0]);
			data.put("y", values[1]);
			data.put("xV", values[2]);
			data.put("yV", values[3]);
			
			registration_ids.add(regId);
			params.put("registration_ids", registration_ids);
			params.put("data", data);

			JSONObject json = new JSONObject(params);
			httpPost.setEntity(new StringEntity(json.toString()));
			httpPost.setHeader("Authorization",
					"key=" + Main.API_KEY);
			httpPost.setHeader("Content-type", "application/json");
			
			Log.i(Main.TAG, "Sending: " + json.toString());
			client.execute(httpPost);
		} catch (Exception e) {
			Log.i(TAG, e.toString());
		}

		return null;
	}

}

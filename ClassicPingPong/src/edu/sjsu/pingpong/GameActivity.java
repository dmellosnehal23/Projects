package edu.sjsu.pingpong;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GameActivity extends Activity {

	private static GameView gameView;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.animated);
	        
	        Bundle extras = getIntent().getExtras();
	        
	        gameView = (GameView) findViewById(R.id.anim_view);
	        gameView.init(extras.getInt("side"));
	    }
	   
	   public static class GameBroadcastReceiver extends BroadcastReceiver {

			@Override
			public void onReceive(Context context, Intent intent) {
				Bundle extras = intent.getExtras();
				
		        gameView.setCoordinates(
		        		Integer.valueOf(extras.getString("x")), 
		        		Integer.valueOf(extras.getString("y")), 
		        		Integer.valueOf(extras.getString("xV")), 
		        		Integer.valueOf(extras.getString("yV")));				
			}
		}
}

package db.newthread.stores;

import db.newthread.login.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class ButtonVoiceActivity extends Activity{
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.voice_stores);
	      
	    }
	 
	 public void stores_back(View v) {     
	      	this.finish();
	      }  

}

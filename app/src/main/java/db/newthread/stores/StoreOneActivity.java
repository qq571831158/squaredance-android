package db.newthread.stores;

import db.newthread.login.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class StoreOneActivity  extends Activity{
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_stores_one);
	      
	    }
	 
	 public void Sone_back(View v) {     
	      	this.finish();
	      }  

}

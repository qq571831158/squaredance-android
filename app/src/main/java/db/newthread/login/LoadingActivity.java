package db.newthread.login;

import db.newthread.what.WhatsNewActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class LoadingActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_loading);
			
	new Handler().postDelayed(new Runnable(){
		@Override
		public void run(){
			Intent intent = new Intent (LoadingActivity.this,WhatsNewActivity.class);			
			startActivity(intent);			
			LoadingActivity.this.finish();
			Toast.makeText(getApplicationContext(), "正在登陆", Toast.LENGTH_SHORT).show();
		}
	}, 200);
   }

}

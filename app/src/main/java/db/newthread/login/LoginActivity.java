package db.newthread.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void welcome_login(View v) {  
      	Intent intent = new Intent();
		intent.setClass(LoginActivity.this,LoginOneActivity.class);
		startActivity(intent);
		//this.finish();
      }  
    public void welcome_register(View v) {  
      	Intent intent = new Intent();
		intent.setClass(LoginActivity.this,RegisterActivity.class);
		startActivity(intent);
		//this.finish();
      }  

}

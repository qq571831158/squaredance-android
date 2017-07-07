package db.newthread.login;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

@SuppressLint("NewApi")
public class RegisterActivity extends Activity{
	private EditText mUser;   //用户账号输入
	private EditText mUsername;   //用户昵称输入
	private EditText mPassword;    //用户密码输入
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
		  mUser = (EditText)findViewById(R.id.Registration_user);
		  mUsername = (EditText)findViewById(R.id.Registration_name);
	      mPassword = (EditText)findViewById(R.id.Registration_password);
	     
	}
	
	public void register_submit(View v) {  
		String userno = mUser.getText().toString();
		String username = mUsername.getText().toString();
		String pwd = mPassword.getText().toString();
		JSONObject jsonObj;
		try
		{
			jsonObj = query(userno, username, pwd);
			System.out.print(jsonObj.getString("code"));
			// 如果userId 大于0
			if (jsonObj.get("code").equals("S01"))
			{
				System.out.print("dsdsd");
				db.newthread.util.DialogUtil.showDialog(this
						, "注册成功", false);
				//return 1;
			}
		}
		catch (Exception e)
		{
			db.newthread.util.DialogUtil.showDialog(this
					, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}

		//return false;
	
	
	}  
	
	
	// 定义发送请求的方法
		private JSONObject query(String userno, String username, String password)
				throws Exception
		{
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("username", userno);
			jsonParam.put("uservitualname", username);
	        jsonParam.put("password", password);
			// 使用Map封装请求参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", userno);
			map.put("uservitualname", username);
			map.put("password", password);
			// 定义发送请求的URL
			String url = "http://182.254.152.99:8080/squaredance/user/regist";
			// 发送请求
			return new JSONObject(db.newthread.util.GetPostUtil.UrlPostJson(url, jsonParam));
			//return new JSONObject( new Thread(downloadRun).start());
		}
	    
	
	
	
	
	public void register_back(View v) {  
		this.finish();
      }  



}

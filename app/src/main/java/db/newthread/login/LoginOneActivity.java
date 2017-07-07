package db.newthread.login;


import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class LoginOneActivity extends Activity{
	
	private EditText mUser;   //用户账号输入
	private EditText mPassword;    //用户密码输入
	Button bnLogin;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_one);
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
        mUser = (EditText)findViewById(R.id.login_user_edit);
        mPassword = (EditText)findViewById(R.id.login_passwd_edit);
        bnLogin = (Button) findViewById(R.id.login_login_btn);
        bnLogin.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// 执行输入校验
				if (validate())  // ①
				{
					// 如果登录成功
					if (loginPro())  // ②
					{
						// 启动Main Activity
						Intent intent = new Intent(LoginOneActivity.this
								, LoadingActivity.class);
						startActivity(intent);
						// 结束该Activity
						finish();
					}
					else
					{
						db.newthread.util.DialogUtil.showDialog(LoginOneActivity.this
								, "用户名称或者密码错误，请重新输入！", false);
					}
				}
			}
		});
        
    }

   /* public void login_mainSquareDance(View v) {
    	
    	// 执行输入校验
		if (validate())  // ①
		{
			// 如果登录成功
			if (loginPro())  // ②
			{
				// 启动Main Activity
				Intent intent = new Intent(LoginOneActivity.this
						, MainSquareDanceActivity.class);
				startActivity(intent);
				// 结束该Activity
				finish();
			}
			else
			{
				db.newthread.util.DialogUtil.showDialog(LoginOneActivity.this
						, "用户名称或者密码错误，请重新输入！", false);
			}
		}
    	
    
    	
    	
    	if("liuzhen".equals(mUser.getText().toString()) && "123456".equals(mPassword.getText().toString()))   
        {
             Intent intent = new Intent();
             intent.setClass(LoginOneActivity.this,LoadingActivity.class);
             startActivity(intent);
             
          }
        else if("".equals(mUser.getText().toString()) || "".equals(mPassword.getText().toString()))   
        {
        	new AlertDialog.Builder(LoginOneActivity.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("错误！")
			.setMessage("用户名和密码为空\n请重新输入用户名和密码")
			.create().show();
         }
        else{
           
        	new AlertDialog.Builder(LoginOneActivity.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("错误！")
			.setMessage("密码错误\n请重新输入密码")
			.create().show();
        }
    	
    
    	
      	Intent intent = new Intent();
		intent.setClass(Login.this,Whatsnew.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "��¼�ɹ�", Toast.LENGTH_SHORT).show();
		this.finish();
      }  
    */
    private boolean loginPro() 
	{
		// 获取用户输入的用户名、密码
		String username = mUser.getText().toString();
		String pwd = mPassword.getText().toString();
		JSONObject jsonObj;
		//jsonObj = query(username, pwd);
		//System.out.print(jsonObj.get("code"));
		try
		{
			jsonObj = query(username, pwd);
			System.out.print(jsonObj.getString("code"));
			// 如果userId 大于0
			if (jsonObj.get("code").equals("S01"))
			{
				System.out.print("dsdsd");
				return true;
			}
		}
		catch (Exception e)
		{
			db.newthread.util.DialogUtil.showDialog(this
					, "服务器响应异常，请稍后再试！", false);
			e.printStackTrace();
		}

		return false;
	}

	// 对用户输入的用户名、密码进行校验
	private boolean validate()
	{
		String username = mUser.getText().toString().trim();
		if (username.equals(""))
		{
			db.newthread.util.DialogUtil.showDialog(this, "用户账户是必填项！", false);
			return false;
		}
		String pwd = mPassword.getText().toString().trim();
		if (pwd.equals(""))
		{
			db.newthread.util.DialogUtil.showDialog(this, "用户口令是必填项！", false);
			return false;
		}
		return true;
	}

	// 定义发送请求的方法
	private JSONObject query(String username, String password)
			throws Exception
	{
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("username", username);
        jsonParam.put("password", password);
		// 使用Map封装请求参数
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("userID", username);
		map.put("password", password);*/
		// 定义发送请求的URL
		String url = "http://182.254.152.99:8080/squaredance/user/login";
		// 发送请求
		return new JSONObject(db.newthread.util.GetPostUtil.UrlPostJson(url, jsonParam));
		//return new JSONObject( new Thread(downloadRun).start());
	}
    
    
    
    
    
    public void login_back(View v) {     
      	this.finish();
      }  
    public void login_pw(View v) {     
    	Uri uri = Uri.parse("http://182.254.152.99:8080/squaredance/squaredance/code/html/");
    	Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
    	startActivity(intent);
    	//Intent intent = new Intent();
    	//intent.setClass(Login.this,Whatsnew.class);
        //startActivity(intent);
      }  

}

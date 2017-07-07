package db.newthread.stores;

import db.newthread.login.R;
import db.newthread.main.MainSquareDanceActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoValueOneActivity extends Activity{
	
	private VideoView mVideoView01;
	   private String strVideoPath = "http://182.254.152.99:8080/squaredance/dancevideo/smallapple.mp4";//视频存放的路径
	   private String TAG = "HIPPO_VIDEOVIEW";
	   private boolean fullscreen = false;//全屏/窗口播放切换标志
	   
	   
	   /* 判断是否安装存储卡flagfalse */
	   @SuppressWarnings("unused")
	private boolean bIfSDExist = false;
	   
	 ////返回键按钮，返回上一级Activity
	 @Override
	 public void onBackPressed() {
	/*  // TODO Auto-generated method stub
	  super.onBackPressed();
	  Intent i =new Intent();
	  i.setClass(VideoValueOneActivity.this,MainSquareDanceActivity.class);
	  startActivity(i);
	  finish();*/
      this.finish();
	 }
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_video_valueone);  
	     /* 判断存储卡是否存在*/
	     if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
	     {
	       bIfSDExist = true;
	       
	     }
	     else
	     {
	       bIfSDExist = false;
	       mMakeTextToast
	       (
	         getResources().getText(R.string.str_err_nosd).toString(),
	         true
	       );
	     }
	     
	     //获取VideoView对象
	     mVideoView01 = (VideoView)findViewById(R.id.myVideoViewone); 
	     //设置视频播放器，准备函数
	     mVideoView01.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
	     {
	       @Override
	       public void onPrepared(MediaPlayer mp)
	       {
	         // TODO Auto-generated method stub
	       //  mTextView01.setText(strVideoPath);
	         setTitle(strVideoPath.toString());
	       }
	     });
	     //设置视频播放完毕动作，可以在内部设置是否重复播放
	     mVideoView01.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	     {
	       @Override
	       public void onCompletion(MediaPlayer arg0)
	       {
	         // TODO Auto-generated method stub
	         mMakeTextToast
	         (
	           getResources().getText(R.string.str_complete).toString(),
	           true
	         );
	       }
	     });
	     
	     playVideo(strVideoPath);
	   }
	 
	 //媒体播放动作函数
	   private void playVideo(String strPath)
	   {
	     if(strPath!="")
	     {      
	      /*调用VideoURI方法，指定解析路径 */
	      mVideoView01.setVideoURI(Uri.parse(strPath));
	       
	       /* 设置控制Bar，显示在Context中*/
	      mVideoView01.setMediaController(new MediaController(VideoValueOneActivity.this));
	      mVideoView01.requestFocus();
	       
	       /*调用VideoView.start()自动播放*/
	      mVideoView01.start();
	       if(mVideoView01.isPlaying())
	       {
	         /* start()后，仍需要preparing() */
	         //mTextView01.setText("Now Playing:"+strPath);
	        setTitle(strVideoPath.toString());
	         Log.i(TAG, strPath);
	       }
	     }
	   }
	   
	   /**媒体重新从头播放函数  */
	   @SuppressWarnings("unused")
	private void resetVideo()
	   {
	     if(mVideoView01!=null)
	     {
	       mVideoView01.seekTo(0);
	     }
	   }
	 
	   //提示消息Toast弹出消息函数
	   public void mMakeTextToast(String str, boolean isLong)
	   {
	     if(isLong==true)
	     {
	       Toast.makeText(VideoValueOneActivity.this, str, Toast.LENGTH_LONG).show();
	     }
	     else
	     {
	       Toast.makeText(VideoValueOneActivity.this, str, Toast.LENGTH_SHORT).show();
	     }
	   }
	   
	   //添加OptinMenu函数
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	  // TODO Auto-generated method stub
	  //参数说明：menu.add((int groupId, int itemId, int order, charsequence title).setIcon(drawable ID)
//	    1、组别，如果不分组的话就写Menu.NONE, 
//	       2、Id，这个很重要，Android根据这个Id来确定不同的菜单 
//	       3、顺序，哪个菜单项在前面由这个参数的大小决定 
//	       4、文本，菜单项的显示文本
	 
	  menu.add(0,0,1,"返回");//
	     menu.add(0,1,2,"重复播放"); 
	     menu.add(0,3,3,"全屏/窗口切换"); 
	     
	  return super.onCreateOptionsMenu(menu);
	  
	 }
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	  // TODO Auto-generated method stub
	  
	  switch(item.getItemId())//菜单序号
	     {
	      case 0://返回上级
	      {
	       ////返回上一级Activity
	       Intent i =new Intent();
	       i.setClass(VideoValueOneActivity.this,MainSquareDanceActivity.class);
	       startActivity(i);
	       finish();       
	      }
	   break;
	      case 1://播放视频
	       {
	        playVideo(strVideoPath);
	       }
	       break;
	      case 3://全屏/窗口切换
	      {
	       if(!fullscreen){//设置RelativeLayout的全屏模式
	        @SuppressWarnings("deprecation")
			RelativeLayout.LayoutParams layoutParams=
	               new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
	            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	            mVideoView01.setLayoutParams(layoutParams);
	            
	            fullscreen = true;//改变全屏/窗口的标记
	        }else{//设置RelativeLayout的窗口模式
	           RelativeLayout.LayoutParams lp=new  RelativeLayout.LayoutParams(320,240);
	           lp.addRule(RelativeLayout.CENTER_IN_PARENT);
	             mVideoView01.setLayoutParams(lp);
	             fullscreen = false;//改变全屏/窗口的标记
	        }    
	      }
	      break;
	     
	     }     
	 //    return true;
	    return super.onOptionsItemSelected(item); 
	 }

}

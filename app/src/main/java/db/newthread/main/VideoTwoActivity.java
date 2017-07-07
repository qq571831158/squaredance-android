package db.newthread.main;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import db.newthread.login.R;


public class VideoTwoActivity extends Activity{
	//VideoView videoView;
	//MediaController mController;
	@Override
	/*public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.video_two);
		// 获取界面上VideoView组件
		videoView = (VideoView) findViewById(R.id.video);
		// 创建MediaController对象
		mController = new MediaController(this);
		//File video = new File("/mnt/sdcard/DCIM/Camera/VID_20160425_182628.mp4");
		File video = new File("http://120.27.123.114:8080/squaredance/dancevideo/小苹果.mp4");
		if(video.exists())
		{
			videoView.setVideoPath(video.getAbsolutePath());  // ①
			// 设置videoView与mController建立关联
			videoView.setMediaController(mController);  // ②
			// 设置mController与videoView建立关联
			mController.setMediaPlayer(videoView);  // ③
			// 让VideoView获取焦点
			videoView.requestFocus();
		}
	}*/
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.video_two);

		Uri uri = Uri.parse("http://182.254.152.99:8080/squaredance/dancevideo/smallapple.mp4");
		VideoView videoView = (VideoView)this.findViewById(R.id.video);
		videoView.setMediaController(new MediaController(this));
		videoView.setVideoURI(uri);
		//videoView.start();
		videoView.requestFocus();
		}

}

package db.newthread.main;

import java.util.ArrayList;













import db.newthread.alarm.MainClockActivity;
import db.newthread.dancers.CompetitionOneActivity;
import db.newthread.dancers.CompetitionTwoActivity;
import db.newthread.dancers.MinDaGroupActivity;
import db.newthread.dancers.TeachOneActivity;
import db.newthread.login.R;
import db.newthread.map.MainMapActivity;
import db.newthread.stores.ButtonVoiceActivity;
import db.newthread.stores.StoreOneActivity;
import db.newthread.stores.VideoValueOneActivity;
import db.newthread.vodeos.VideoLatestMoreActivity;
import db.newthread.vodeos.VideoWeekHotActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;


public class MainSquareDanceActivity extends Activity {
	public static MainSquareDanceActivity instance = null;
	
	 
	private ViewPager mTabPager;	
	private ImageView mTabImg;
	private ImageView mTab1,mTab2,mTab3,mTab4;
	private int zero = 0;
	private int currIndex = 0;
	private int one;
	private int two;
	private int three;
	private LinearLayout mClose;
    private LinearLayout mCloseBtn;
    private View layout;	
	private boolean menu_display = false;
	private PopupWindow menuWindow;
	private LayoutInflater inflater;
	//private Button mRightBtn;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_squaredance);
      //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        instance = this;
        
        /*
        mRightBtn = (Button) findViewById(R.id.right_btn);
        mRightBtn.setOnClickListener(new Button.OnClickListener()
		{	@Override
			public void onClick(View v)
			{	showPopupWindow (MainWeixin.this,mRightBtn);
			}
		  });*/
        
        mTabPager = (ViewPager)findViewById(R.id.tabpager);
        mTabPager.setOnPageChangeListener(new MyOnPageChangeListener());
        
        mTab1 = (ImageView) findViewById(R.id.img_suquaredance);
        mTab2 = (ImageView) findViewById(R.id.img_address);
        mTab3 = (ImageView) findViewById(R.id.img_friends);
        mTab4 = (ImageView) findViewById(R.id.img_settings);
        mTabImg = (ImageView) findViewById(R.id.img_tab_now);
        mTab1.setOnClickListener(new MyOnClickListener(0));
        mTab2.setOnClickListener(new MyOnClickListener(1));
        mTab3.setOnClickListener(new MyOnClickListener(2));
        mTab4.setOnClickListener(new MyOnClickListener(3));
        Display currDisplay = getWindowManager().getDefaultDisplay();//能够取得屏幕的信息
        int displayWidth = currDisplay.getWidth();    //取得宽像素
        int displayHeight = currDisplay.getHeight();    //取得高像素
        one = displayWidth/4; 
        two = one*2;
        three = one*3;
       
        LayoutInflater mLi = LayoutInflater.from(this);
        View view1 = mLi.inflate(R.layout.activity_main_dancers, null);
        View view2 = mLi.inflate(R.layout.activity_main_video_one, null);
        View view3 = mLi.inflate(R.layout.activity_main_stores, null);
        View view4 = mLi.inflate(R.layout.activity_main_personal, null);
        
        
        final ArrayList<View> views = new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
     
        PagerAdapter mPagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager)container).removeView(views.get(position));
			}
			
			//@Override
			//public CharSequence getPageTitle(int position) {
				//return titles.get(position);
			//}
			
			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager)container).addView(views.get(position));
				return views.get(position);
			}
		};
		
		mTabPager.setAdapter(mPagerAdapter);
    }
   
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}
		@Override
		public void onClick(View v) {
			mTabPager.setCurrentItem(index);
		}
	};
    
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_squaredance_pressed));
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, 0, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, 0, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 1:
				mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_squaredance_normal));
				} else if (currIndex == 2) {
					animation = new TranslateAnimation(two, one, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, one, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 2:
				mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, two, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_squaredance_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, two, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 3) {
					animation = new TranslateAnimation(three, two, 0, 0);
					mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_normal));
				}
				break;
			case 3:
				mTab4.setImageDrawable(getResources().getDrawable(R.drawable.tab_settings_pressed));
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, three, 0, 0);
					mTab1.setImageDrawable(getResources().getDrawable(R.drawable.tab_squaredance_normal));
				} else if (currIndex == 1) {
					animation = new TranslateAnimation(one, three, 0, 0);
					mTab2.setImageDrawable(getResources().getDrawable(R.drawable.tab_address_normal));
				}
				else if (currIndex == 2) {
					animation = new TranslateAnimation(two, three, 0, 0);
					mTab3.setImageDrawable(getResources().getDrawable(R.drawable.tab_find_frd_normal));
				}
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(150);
			mTabImg.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
    		
        	if(menu_display){         
        		menuWindow.dismiss();
        		menu_display = false;
        		}
        	else {
        		Intent intent = new Intent();
            	intent.setClass(MainSquareDanceActivity.this,ExitActivity.class);
            	startActivity(intent);
        	}
    	}
    	
    	else if(keyCode == KeyEvent.KEYCODE_MENU){   		
			if(!menu_display){
				
				inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
				
				layout = inflater.inflate(R.layout.activity_main_menu, null);
				
				
				menuWindow = new PopupWindow(layout,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); 
				//menuWindow.showAsDropDown(layout); 
				//menuWindow.showAsDropDown(null, 0, layout.getHeight());
				menuWindow.showAtLocation(this.findViewById(R.id.mainsquaredance), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //����layout��PopupWindow����ʾ��λ��
				
				mClose = (LinearLayout)layout.findViewById(R.id.menu_close);
				mCloseBtn = (LinearLayout)layout.findViewById(R.id.menu_close_btn);
				
				
				
				mCloseBtn.setOnClickListener (new View.OnClickListener() {					
					@Override
					public void onClick(View arg0) {						
						
						Intent intent = new Intent();
			        	intent.setClass(MainSquareDanceActivity.this,ExitActivity.class);
			        	startActivity(intent);
			        	menuWindow.dismiss(); 
					}
				});				
				menu_display = true;				
			}else{
				
				menuWindow.dismiss();
				menu_display = false;
				}
			
			return false;
		}
    	return false;
    }
	public void btn_alarm(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MainClockActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_competition_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,CompetitionOneActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_competition_two(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,CompetitionTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_group_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MinDaGroupActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_search(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MainVideoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_search_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MainVideoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_search_latest(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoLatestMoreActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_search_weekhot(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoWeekHotActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_store_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,StoreOneActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_teach_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,TeachOneActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_one(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MainVideoActivity.class);			
		startActivity(intent);	
		
      }  	
	
	public void btn_video_two(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_three(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_four(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_five(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,MainVideoActivity.class);			
		startActivity(intent);	
		
      } 
	public void btn_video_six(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_seven(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_eight(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_video_nine(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoTwoActivity.class);			
		startActivity(intent);	
		
      }  	
	
	public void btn_video_valueone(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,VideoValueOneActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_voice(View v) {  
		Intent intent = new Intent (MainSquareDanceActivity.this,ButtonVoiceActivity.class);			
		startActivity(intent);	
		
      }  	
	public void btn_map(View v) {      
		Intent intent = new Intent (MainSquareDanceActivity.this,MainMapActivity.class);			
		startActivity(intent);	
		
      }  
	
	
	/*public void exit_settings(View v) {                          
		//Intent intent = new Intent (MainSquareDanceActivity.this,ExitFromSettings.class);			
	//	startActivity(intent);	
	 }
	public void btn_shake(View v) {                                  
		//Intent intent = new Intent (MainSquareDanceActivity.this,ShakeActivity.class);			
		//startActivity(intent);	
	}*/

}

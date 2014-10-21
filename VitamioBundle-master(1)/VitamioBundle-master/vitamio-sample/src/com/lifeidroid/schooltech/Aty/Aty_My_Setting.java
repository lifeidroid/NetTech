package com.lifeidroid.schooltech.Aty;

import java.io.File;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class Aty_My_Setting extends BaseActivity {
	private ImageView iv_back;
	private TextView tv_back;
	private RelativeLayout lay_clearCache;
	private RelativeLayout lay_pushMessage;
	private RelativeLayout lay_opinoin;
	private RelativeLayout lay_updata;
	private RelativeLayout lay_about;
	private Button btn_exit;
	private String cachePath;
	private String email;
	private String token;
	private File dirFile;
	private Intent intent;
	private static Toast mToast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_setting);
		initVaues();
		initViews();
		initListener();
	}
	private void initVaues(){
		intent = getIntent();
		cachePath = intent.getExtras().getString(Config.KEY_CACHEPATH);
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		token = intent.getExtras().getString(Config.KEY_TOKEN);
		dirFile = new File(cachePath);
	}
	private void initViews(){
		iv_back = (ImageView)findViewById(R.id.iv_my_setting_cancel_logo);
		tv_back = (TextView)findViewById(R.id.tv_my_setting_cancel_text);
		lay_clearCache = (RelativeLayout)findViewById(R.id.lay_aty_my_setting_clearCache);
		lay_pushMessage = (RelativeLayout)findViewById(R.id.lay_aty_my_setting_pushmessage);
		lay_opinoin = (RelativeLayout)findViewById(R.id.lay_aty_my_setting_opinion);
		lay_updata = (RelativeLayout)findViewById(R.id.lay_aty_my_setting_update);
		lay_about = (RelativeLayout)findViewById(R.id.lay_aty_my_setting_about);
		btn_exit = (Button)findViewById(R.id.btn_aty_my_setting_exit);
	}
	private void initListener(){
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		tv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		lay_clearCache.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				clearCache();
				showToast(Aty_My_Setting.this, "缓存清理完成！", Toast.LENGTH_SHORT);
				
			}
		});
		lay_pushMessage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Aty_My_Setting.this,Aty_My_Setting_PushMessage.class));
				
			}
		});
		lay_opinoin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				intent = new Intent(Aty_My_Setting.this,Aty_My_Setting_SendOpinion.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN,token);
				startActivity(intent);
				
			}
		});
		lay_updata.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showToast(Aty_My_Setting.this, "您已经是最新版本了哦！", Toast.LENGTH_SHORT);
			}
		});
		lay_about.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Aty_My_Setting.this,Aty_My_Setting_About.class));
				
			}
		});
		btn_exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Editor editor =  getSharedPreferences(Config.APPID, Context.MODE_PRIVATE).edit();
				editor.clear();
				editor.commit();
				exit();
				startActivity(new Intent(Aty_My_Setting.this,Aty_Login.class));
			}
		});
	}
	private void clearCache(){
		for (File file : dirFile.listFiles()) {
			file.delete();
		}
	}
	
	public static void showToast(Context context, String text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}

		mToast.show();
	}
}

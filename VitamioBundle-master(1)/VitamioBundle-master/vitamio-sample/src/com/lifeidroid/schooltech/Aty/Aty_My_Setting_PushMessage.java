package com.lifeidroid.schooltech.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class Aty_My_Setting_PushMessage extends BaseActivity {
	private ImageView iv_back;
	private TextView tv_back;
	private Switch sw_push;
	private boolean sw_recivePush;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_settiong_pusmessage);
		initVaues();
		initViews();
		initListener();
	}
	private void initVaues(){
		sw_recivePush = Config.getCacheWhetheRecivePush(Aty_My_Setting_PushMessage.this);
	}
	private void initViews(){
		iv_back = (ImageView) findViewById(R.id.iv_aty_pushmessage_cancel_logo);
		tv_back = (TextView) findViewById(R.id.tv_aty_pushmessage_cancel_text);
		sw_push = (Switch) findViewById(R.id.sw_aty_pushmessage);
		sw_push.setChecked(sw_recivePush);
	}
	private void initListener(){
		sw_push.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					JPushInterface.resumePush(getApplicationContext());
				}else {
					JPushInterface.stopPush(getApplicationContext());
				}
				Config.cacheWhetheRecivePush(Aty_My_Setting_PushMessage.this, arg1);
				
			}
		});
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
	}
}

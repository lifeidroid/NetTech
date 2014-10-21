package com.lifeidroid.schooltech.Aty;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.R;

public class Aty_My_Setting_About extends BaseActivity {
	private ImageView iv_back;
	private TextView tv_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_setting_about);
		iv_back = (ImageView)findViewById(R.id.iv_aty_about_cancel_logo);
		tv_back = (TextView) findViewById(R.id.tv_aty_about_cancel_text);
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

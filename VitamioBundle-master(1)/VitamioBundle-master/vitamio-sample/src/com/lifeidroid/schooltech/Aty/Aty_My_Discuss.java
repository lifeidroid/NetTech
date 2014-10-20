package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeidroid.schooltech.R;

public class Aty_My_Discuss extends Activity {
	private ImageView iv_back;
	private TextView tv_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_discuss);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		iv_back = (ImageView)findViewById(R.id.iv_my_discuss_cancel_logo);
		tv_back = (TextView)findViewById(R.id.tv_my_discuss_cancel_text);
		
	}
	private void initViews(){
		
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
	}
}

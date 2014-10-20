package com.lifeidroid.schooltech.Aty;

import com.lifeidroid.schooltech.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Aty_My_Note extends Activity {
	private ImageView iv_back;
	private TextView tv_back;
	private RadioGroup rg_select;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_note);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		
	}
	private void initViews(){
		iv_back = (ImageView)findViewById(R.id.iv_my_note_cancel_logo);
		tv_back =(TextView)findViewById(R.id.tv_my_note_cancel_text);
		rg_select = (RadioGroup)findViewById(R.id.rg_aty_my_note);
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
		rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}

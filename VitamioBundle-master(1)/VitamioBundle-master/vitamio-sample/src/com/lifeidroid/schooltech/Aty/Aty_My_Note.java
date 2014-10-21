package com.lifeidroid.schooltech.Aty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class Aty_My_Note extends BaseActivity {
	private ImageView iv_back;
	private TextView tv_back;
	private RadioGroup rg_select;
	private String email;
	private String token;
	private String headUrl;
	private String nike;
	private String cachePath;
	private Intent intent;
	private FragmentManager fManage;
	private FragmentTransaction fTransation;
	private Frg_My_Note_Self frg_note_self;
	private Frg_My_Note_Collected frg_note_Collected;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_note);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		intent = getIntent();
		cachePath = intent.getExtras().getString(Config.KEY_CACHEPATH);
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		token = intent.getExtras().getString(Config.KEY_TOKEN);
		headUrl = intent.getExtras().getString(Config.KEY_HEAD);
		nike = intent.getExtras().getString(Config.KEY_NIKENAME);
		fManage =getSupportFragmentManager();
	}
	private void initViews(){
		iv_back = (ImageView)findViewById(R.id.iv_my_note_cancel_logo);
		tv_back =(TextView)findViewById(R.id.tv_my_note_cancel_text);
		rg_select = (RadioGroup)findViewById(R.id.rg_aty_my_note);
		setMyNote();
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

				switch (arg1) {
				case R.id.rb_aty_my_note_self:
					setMyNote();
					break;
				case R.id.rb_aty_my_note_collect:
					setCollectedNote();
					break;
				default:
					break;
				}

				
			}
		});
	}
	private void setMyNote(){
		fTransation = fManage.beginTransaction();
		frg_note_self = new Frg_My_Note_Self();
		bundle = new Bundle();
		bundle.putString(Config.KEY_EMAILMD5, email);
		bundle.putString(Config.KEY_TOKEN,token);
		bundle.putString(Config.KEY_HEAD, headUrl);
		bundle.putString(Config.KEY_NIKENAME, nike);
		bundle.putString(Config.KEY_CACHEPATH, cachePath);
		frg_note_self.setArguments(bundle);
		fTransation.replace(R.id.lay_aty_my_note_container, frg_note_self);
		fTransation.commit();
	}
	private void setCollectedNote(){
		fTransation = fManage.beginTransaction();
		frg_note_Collected = new Frg_My_Note_Collected();
		bundle = new Bundle();
		bundle.putString(Config.KEY_EMAILMD5, email);
		bundle.putString(Config.KEY_TOKEN,token);
		bundle.putString(Config.KEY_CACHEPATH, cachePath);
		frg_note_Collected.setArguments(bundle);
		fTransation.replace(R.id.lay_aty_my_note_container, frg_note_Collected);
		fTransation.commit();
	}
}

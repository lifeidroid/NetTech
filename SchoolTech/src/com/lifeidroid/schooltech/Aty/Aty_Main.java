package com.lifeidroid.schooltech.Aty;

import java.io.File;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class Aty_Main extends Activity {
	private LinearLayout lay_course;
	private LinearLayout lay_my;
	private ImageView iv_course_logo;
	private ImageView iv_my_logo;
	private TextView tv_course_name;
	private TextView tv_my_name;
	private String cachePath;
	private String path;
	private File dirFile;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Bundle bundle;
	private String token;
	private String email;
	private String mottp;
	private String head;
	private String nikename;
	private Intent intent;
	private boolean Switch_Main;// 是否打开课程界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_main);
		initValues();
		initView();
		initListener();
	}

	private void initValues() {
		intent = getIntent();
		Switch_Main = true;
		token = intent.getExtras().getString(Config.KEY_TOKEN);
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		mottp = intent.getExtras().getString(Config.KEY_MOTTO);
		head = intent.getExtras().getString(Config.KEY_HEAD);
		nikename = intent.getExtras().getString(Config.KEY_NIKENAME);
		fManager = getFragmentManager();
		path = getSDPath() + "/iyunmiCache/";
		dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		cachePath = dirFile.toString();
	}

	private void initView() {
		lay_course = (LinearLayout) findViewById(R.id.lay_main_course);
		lay_my = (LinearLayout) findViewById(R.id.lay_main_my);
		iv_course_logo = (ImageView) findViewById(R.id.iv_main_course_logo);
		iv_my_logo = (ImageView) findViewById(R.id.iv_main_my_logo);
		tv_course_name = (TextView) findViewById(R.id.tv_main_course_name);
		tv_my_name = (TextView) findViewById(R.id.tv_main_my_name);
		iv_course_logo.setImageResource(R.drawable.img_course_on);
		tv_course_name.setTextColor(getResources().getColor(R.color.Bule));
		iv_my_logo.setImageResource(R.drawable.img_my_off);
		tv_my_name.setTextColor(getResources().getColor(R.color.light_Blank));

		fTransaction = fManager.beginTransaction();
		Frg_Course frg_Course = new Frg_Course();
		bundle = new Bundle();
		bundle.putString(Config.KEY_CACHEPATH, cachePath);
		bundle.putString(Config.KEY_EMAILMD5, email);
		bundle.putString(Config.KEY_TOKEN, token);
		frg_Course.setArguments(bundle);
		fTransaction.replace(R.id.lay_main, frg_Course);
		fTransaction.commit();
	}

	private void initListener() {
		lay_course.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				iv_course_logo.setImageResource(R.drawable.img_course_on);
				tv_course_name.setTextColor(getResources().getColor(
						R.color.Bule));
				iv_my_logo.setImageResource(R.drawable.img_my_off);
				tv_my_name.setTextColor(getResources().getColor(
						R.color.light_Blank));
				if (Switch_Main) {
					return;
				}
				Switch_Main = true;
				fTransaction = fManager.beginTransaction();
				Frg_Course frg_Course = new Frg_Course();
				bundle = new Bundle();
				bundle.putString(Config.KEY_CACHEPATH, cachePath);
				bundle.putString(Config.KEY_EMAILMD5, email);
				bundle.putString(Config.KEY_TOKEN, token);
				frg_Course.setArguments(bundle);
				fTransaction.replace(R.id.lay_main, frg_Course);
				fTransaction.commit();
				

			}
		});
		lay_my.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!Switch_Main) {
					return;
				}
				Switch_Main = false;
				iv_course_logo.setImageResource(R.drawable.img_course_off);
				tv_course_name.setTextColor(getResources().getColor(
						R.color.light_Blank));
				iv_my_logo.setImageResource(R.drawable.img_my_on);
				tv_my_name.setTextColor(getResources().getColor(R.color.Bule));
				fTransaction = fManager.beginTransaction();
				Frg_My frg_My = new Frg_My();
				fTransaction.replace(R.id.lay_main, frg_My);
				fTransaction.commit();

			}
		});
	}

	private String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.toString();
	}
}

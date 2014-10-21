package com.lifeidroid.schooltech.Aty;

import java.io.File;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;

public class Aty_Main extends android.support.v4.app.FragmentActivity {
	private LinearLayout lay_course;
	private LinearLayout lay_my;
	private ImageView iv_course_logo;
	private ImageView iv_my_logo;
	private TextView tv_course_name;
	private TextView tv_my_name;
	private android.support.v4.app.FragmentManager fManager;
	private android.support.v4.app.FragmentTransaction fTransaction;
	private Bundle bundle;
	private String token;
	private String email;
	private String motto;
	private String head;
	private String nikename;
	private Intent intent;
	private boolean Switch_Main;// 是否打开课程界面
	private Frg_My frg_My;
	private Frg_Course frg_Course;
	private AlertDialog aDialog;
	private TextView tv_exist;
	private TextView tv_cancel;
	private String cachePath;
	private String path;
	private File dirFile;

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
		motto = intent.getExtras().getString(Config.KEY_MOTTO);
		head = intent.getExtras().getString(Config.KEY_HEAD);
		nikename = intent.getExtras().getString(Config.KEY_NIKENAME);
		fManager = getSupportFragmentManager();
		

		path = getSDPath() + "/SchoolTech/";
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
		frg_Course = new Frg_Course();
		bundle = new Bundle();
		bundle.putString(Config.KEY_CACHEPATH, cachePath);
		bundle.putString(Config.KEY_EMAILMD5, email);
		bundle.putString(Config.KEY_TOKEN, token);
		frg_Course.setArguments(bundle);
		fTransaction.replace(R.id.lay_main, frg_Course);
		fTransaction.commit();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			final View DialogView = LayoutInflater.from(Aty_Main.this).inflate(
					R.layout.dlg_exist, null);
			final Dialog dialog = new Dialog(Aty_Main.this,
					R.style.transparentFrameWindowStyle);
			dialog.setContentView(DialogView, new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			dialog.setCanceledOnTouchOutside(true);

			tv_exist = (TextView) DialogView.findViewById(R.id.tv_dlg_exit);
			tv_cancel = (TextView) DialogView.findViewById(R.id.tv_dlg_cancel);
			tv_exist.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					finish();

				}
			});
			tv_cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					dialog.dismiss();

				}
			});
			dialog.show();
			break;
		}
		return super.onKeyDown(keyCode, event);
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
				frg_Course = new Frg_Course();
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
				frg_My = new Frg_My();
				bundle = new Bundle();
				bundle.putString(Config.KEY_CACHEPATH, cachePath);
				bundle.putString(Config.KEY_EMAILMD5, email);
				bundle.putString(Config.KEY_TOKEN, token);
				bundle.putString(Config.KEY_NIKENAME, nikename);
				bundle.putString(Config.KEY_MOTTO, motto);
				bundle.putString(Config.KEY_HEAD, head);
				bundle.putString(Config.KEY_CACHEPATH, cachePath);
				frg_My.setArguments(bundle);
				fTransaction.replace(R.id.lay_main, frg_My);
				fTransaction.commit();

			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
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

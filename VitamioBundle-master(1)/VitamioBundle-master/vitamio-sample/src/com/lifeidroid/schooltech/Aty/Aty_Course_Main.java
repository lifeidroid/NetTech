package com.lifeidroid.schooltech.Aty;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.NetGetImage;

public class Aty_Course_Main extends FragmentActivity {
	private Bundle mBundle;
	private Intent mIntent;
	private String email;
	private String token;
	private String courseLogo;
	private String courseName;
	private String cachePath;
	private String techName;
	private int deptId;
	private int schoolId;
	private int courseId;
	private String studentNum;
	private float grade;

	private ImageView iv_coursemain_hart;
	private LinearLayout lay_back;
	private TextView tv_course_title;
	private RadioGroup rg_select;
	private ImageView iv_course_logo;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Frg_Course_Info frg_Course_Info;
	private Frg_Course_Discuss frg_Course_Discuss;
	private Frg_Course_Note frg_Course_Note;
	private Frg_Course_List frg_Course_List;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_course_main);
		initValues();
		initViews();
		initListener();
	}

	private void initValues() {
		mIntent = getIntent();
		email = mIntent.getExtras().getString(Config.KEY_EMAILMD5);
		token = mIntent.getExtras().getString(Config.KEY_TOKEN);
		courseName = mIntent.getExtras().getString(Config.KEY_COURSENAME);
		courseLogo = mIntent.getExtras().getString(Config.KEY_COURSELOGO);
		techName = mIntent.getExtras().getString(Config.KEY_TECHNAME);
		schoolId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_SCHOOLID);
		deptId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_DEPTID);
		courseId = mIntent.getExtras().getInt(Config.KEY_COURSEID);
		cachePath = mIntent.getExtras().getString(Config.KEY_CACHEPATH);
		grade = mIntent.getExtras().getFloat(Config.KEY_GRADE);
		studentNum = mIntent.getExtras().getString(Config.KEY_STUDENTNUM);
		fManager = getSupportFragmentManager();
	}

	private void initViews() {
		iv_coursemain_hart = (ImageView) findViewById(R.id.iv_coursemain_hart);
		lay_back = (LinearLayout) findViewById(R.id.lay_coursemain_cancel);
		tv_course_title = (TextView) findViewById(R.id.tv_coursemain_title);
		rg_select = (RadioGroup) findViewById(R.id.rg_coursemain_select);
		iv_course_logo = (ImageView) findViewById(R.id.iv_coursemain_logo);
		System.out.println("---->courseName" + courseName);
		tv_course_title.setText(courseName);
		System.out.println("------->courselogoselect" + courseLogo);
		AsyncImageLoad(iv_course_logo, courseLogo);
		fTransaction = fManager.beginTransaction();

		mBundle = new Bundle();
		frg_Course_Info = new Frg_Course_Info();
		mBundle.putString(Config.KEY_EMAILMD5, email);
		mBundle.putString(Config.KEY_TOKEN, token);
		mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
		mBundle.putInt(Config.KEY_DEPTID, deptId);
		mBundle.putInt(Config.KEY_COURSEID, courseId);
		mBundle.putString(Config.KEY_TECHNAME, techName);
		mBundle.putString(Config.KEY_COURSENAME, courseName);
		mBundle.putString(Config.KEY_CACHEPATH, cachePath);
		mBundle.putString(Config.KEY_STUDENTNUM, studentNum);
		mBundle.putFloat(Config.KEY_GRADE, grade);
		frg_Course_Info.setArguments(mBundle);
		fTransaction.replace(R.id.lay_coursemain_container, frg_Course_Info);
		fTransaction.commit();

	}

	private void initListener() {
		lay_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		rg_select
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						fTransaction = fManager.beginTransaction();
						switch (arg1) {
						case R.id.rb_courseinfo:
							frg_Course_Info = new Frg_Course_Info();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_TECHNAME, techName);
							mBundle.putString(Config.KEY_COURSENAME, courseName);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							mBundle.putString(Config.KEY_STUDENTNUM, studentNum);
							mBundle.putFloat(Config.KEY_GRADE, grade);
							frg_Course_Info.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container, frg_Course_Info);
							break;
						case R.id.rb_coursediscuss:
							frg_Course_Discuss = new Frg_Course_Discuss();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_Discuss.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container, frg_Course_Discuss);
							break;
						case R.id.rb_coursenote:
							frg_Course_Note = new Frg_Course_Note();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_Note.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container, frg_Course_Note);
							break;
						case R.id.rb_courselist:
							frg_Course_List = new Frg_Course_List();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_List.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container, frg_Course_List);
							break;

						default:
							break;
						}
						fTransaction.commit();
					}
				});
	}

	private void AsyncImageLoad(ImageView ivHead, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(ivHead);
		asyncImageTask.execute(path);
	}

	private class AsyncImageTask extends AsyncTask<String, Integer, Uri> {
		private ImageView imageView;

		public AsyncImageTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Uri doInBackground(String... arg0) { // 运行在子线程中
			Uri uri = NetGetImage.getImage(arg0[0], cachePath);
			return uri;
		}

		@Override
		protected void onPostExecute(Uri result) { // 运行在主线程中
			if (result != null && imageView != null) {
				imageView.setImageURI(result);
			}
		}

	}

}

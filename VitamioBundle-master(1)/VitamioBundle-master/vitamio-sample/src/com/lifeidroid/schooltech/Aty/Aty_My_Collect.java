package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_Course;
import com.lifeidroid.schooltech.Net.Net_GetCollectedCourse;
import com.lifeidroid.schooltech.Tools.ListViewFrame;

public class Aty_My_Collect extends BaseActivity implements
		ListViewFrame.IXListViewListener {
	private ProgressBar pb_content;
	private ListViewFrame lv_course;
	private Intent intent;
	private int courseID;
	private String courseLogo;
	private String courseName;
	private String techName;
	private String studentNum;
	private float garde;
	private String cachePath;
	private String email;
	private String token;
	private Adp_Course adapter;
	private Mdl_Course mdl_Course;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_collect);
		initValues();
		initViews();
		initListener();
	}

	private void initValues() {
		intent = getIntent();
		cachePath = intent.getExtras().getString(Config.KEY_CACHEPATH);
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		token = intent.getExtras().getString(Config.KEY_TOKEN);
		adapter = new Adp_Course(Aty_My_Collect.this, cachePath);
	};

	private void initViews() {
		pb_content = (ProgressBar) findViewById(R.id.pb_aty_my_collected_course);
		lv_course = (ListViewFrame) findViewById(R.id.lv_aty_my_collected_course);
		lv_course.setPullLoadEnable(true);
		lv_course.setPullRefreshEnable(true);
		lv_course.setXListViewListener(this);
		lv_course.setAdapter(adapter);
		pb_content.setVisibility(View.VISIBLE);
		loadData(Config.REFRESH);
	};

	private void initListener() {
		lv_course.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				mdl_Course = (Mdl_Course) adapter.getItem(position-1);
				courseID = mdl_Course.getCourseId();
				courseLogo = mdl_Course.getCourseLogo();
				courseName = mdl_Course.getCourseName();
				techName = mdl_Course.getTechName();
				studentNum = mdl_Course.getStudentNum();
				garde = mdl_Course.getGarde();
				intent = new Intent(Aty_My_Collect.this, Aty_Course_Main.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_COURSEID, courseID);
				intent.putExtra(Config.KEY_COURSELOGO, courseLogo);
				intent.putExtra(Config.KEY_CACHEPATH, cachePath);
				intent.putExtra(Config.KEY_COURSENAME, courseName);
				intent.putExtra(Config.KEY_TECHNAME, techName);
				intent.putExtra(Config.KEY_GRADE, garde);
				intent.putExtra(Config.KEY_STUDENTNUM, studentNum);
				startActivity(intent);
				
			}
		});
	}

	private void loadData(final int actioin) {
		new Net_GetCollectedCourse(Config.ACTION_GETCOLLECTEDCOURSE, email,
				token, 1, 20, new Net_GetCollectedCourse.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_Course> list) {
						if (actioin == Config.REFRESH);
						{
							adapter.clear();
						}
						lv_course.stopLoadMore();
						lv_course.stopRefresh();
						pb_content.setVisibility(View.GONE);
						adapter.addAll(list);
						if (0 == adapter.getCount()) {
							Toast.makeText(Aty_My_Collect.this, "收藏列表为空！",
									Toast.LENGTH_SHORT).show();	
						}

					}
				}, new Net_GetCollectedCourse.FailCallback() {

					@Override
					public void onFail(int error) {
						Toast.makeText(Aty_My_Collect.this, "获取收藏列表失败！",
								Toast.LENGTH_SHORT).show();
						lv_course.stopLoadMore();
						lv_course.stopRefresh();
					}
				});
	}

	@Override
	public void onRefresh() {
		loadData(Config.REFRESH);

	}

	@Override
	public void onLoadMore() {
		loadData(Config.LOADMORE);

	};

}

package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.R.interpolator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_Course;
import com.lifeidroid.schooltech.Net.Net_GetCourse;
import com.lifeidroid.schooltech.Tools.ListViewFrame;

public class Frg_Course_New extends Fragment implements
		ListViewFrame.IXListViewListener {
	private View view;
	private String cachePath;
	private int default_schoolId;
	private int default_deptId;
	private String email;
	private String token;
	private Bundle bundle;
	private Adp_Course adp_Course;
	private ListViewFrame lv_newcourse;
	private int action;
	private Mdl_Course mdl_Course;
	private int courseID;
	private String courseLogo;
	private String courseName;
	private String techName;
	private String studentNum;
	private float garde;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_new, null);
		initViews();
		initListener();
		return view;
	}

	private void initValues() {
		bundle = getArguments();
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		email = bundle.getString(Config.KEY_EMAILMD5);
		token = bundle.getString(Config.KEY_TOKEN);
		default_deptId = bundle.getInt(Config.KEY_DEFAULT_DEPTID);
		default_schoolId = bundle.getInt(Config.KEY_DEFAULT_SCHOOLID);
		adp_Course = new Adp_Course(getActivity(), cachePath);
		action = Config.REFRESH;
	}

	/**
	 * 
	 */
	private void initViews() {
		lv_newcourse = (ListViewFrame) view.findViewById(R.id.lv_course);
		lv_newcourse.setPullLoadEnable(true);
		lv_newcourse.setPullRefreshEnable(true);
		lv_newcourse.setXListViewListener(this);
		lv_newcourse.setAdapter(adp_Course);
		loadMessage(Config.REFRESH);
	}

	private void initListener() {

		lv_newcourse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mdl_Course = (Mdl_Course) adp_Course.getItem(position-1);
				courseID = mdl_Course.getCourseId();
				courseLogo = mdl_Course.getCourseLogo();
				courseName = mdl_Course.getCourseName();
				techName = mdl_Course.getTechName();
				studentNum = mdl_Course.getStudentNum();
				garde = mdl_Course.getGarde();
				intent = new Intent(getActivity(), Aty_Course_Main.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_DEFAULT_SCHOOLID, default_deptId);
				intent.putExtra(Config.KEY_DEFAULT_DEPTID, default_deptId);
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

	@Override
	public void onRefresh() {
		loadMessage(Config.REFRESH);

	}

	@Override
	public void onLoadMore() {
		loadMessage(Config.LOADMORE);

	}

	private void loadMessage(final int aciton) {
		new Net_GetCourse(Config.ACTION_GETNEWCOURSE, email, token,
				default_schoolId, default_deptId, 1, 20,
				new Net_GetCourse.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_Course> list) {
						if (Config.REFRESH == aciton) {
							adp_Course.clear();
						}
						adp_Course.addAll(list);
						lv_newcourse.stopLoadMore();
						lv_newcourse.stopRefresh();
					}
				}, new Net_GetCourse.FailCallback() {

					@Override
					public void onFail(int error) {
						lv_newcourse.stopLoadMore();
						lv_newcourse.stopRefresh();
						Toast.makeText(getActivity(), "加载失败！",
								Toast.LENGTH_SHORT).show();

					}
				});
	}
}

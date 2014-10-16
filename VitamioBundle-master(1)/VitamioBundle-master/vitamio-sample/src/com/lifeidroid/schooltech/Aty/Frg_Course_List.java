package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseList;
import com.lifeidroid.schooltech.Net.Net_GetCourseList;

public class Frg_Course_List extends Fragment{
	private View view;
	private ListView lv_content;
	private String email;
	private String token;
	private int schoolId;
	private int deptId;
	private int courseId;
	private String cachePath;
	private Bundle bundle;
	private Adp_CourseList aList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_list, null);
		initViews();
		return view;
	}

	private void initValues() {
		bundle = getArguments();
		email = bundle.getString(Config.KEY_EMAILMD5);
		token = bundle.getString(Config.KEY_TOKEN);
		schoolId = bundle.getInt(Config.KEY_SCHOOLID);
		deptId = bundle.getInt(Config.KEY_DEPTID);
		courseId = bundle.getInt(Config.KEY_COURSEID);
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		aList = new Adp_CourseList(getActivity());
	}

	private void initViews() {
		lv_content = (ListView) view.findViewById(R.id.lv_courselist);
		lv_content.setAdapter(aList);
		loaddata();
	}

	private void loaddata() {
		new Net_GetCourseList(email, token, schoolId, deptId, courseId,
				new Net_GetCourseList.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_CourseList> list) {

						aList.clear();
						aList.addAll(list);

					}
				}, new Net_GetCourseList.FailCallback() {

					@Override
					public void onFail(int error) {

						Toast.makeText(getActivity(), "获取笔记列表失败！",
								Toast.LENGTH_SHORT).show();

					}
				});
	}

}

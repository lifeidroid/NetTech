package com.lifeidroid.schooltech.Aty;

import java.util.List;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_Course;
import com.lifeidroid.schooltech.Net.Net_GetCourse;
import com.lifeidroid.schooltech.Tools.RTPullListView;
import com.lifeidroid.schooltech.Tools.RTPullListView.OnRefreshListener;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Frg_Course_Hot extends Fragment{
	private View view;
	private String cachePath;
	private int default_schoolId;
	private int default_deptId;
	private String email;
	private String token;
	private Bundle bundle;
	private Adp_Course adp_Course;
	private RTPullListView lv_newcourse;
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
	private void initValues(){
		bundle = getArguments();
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		email = bundle.getString(Config.KEY_EMAILMD5);
		token = bundle.getString(Config.KEY_TOKEN);
		default_deptId = bundle.getInt(Config.KEY_DEFAULT_DEPTID);
		default_schoolId = bundle.getInt(Config.KEY_DEFAULT_SCHOOLID);
		adp_Course = new Adp_Course(getActivity(), cachePath);
	}
	private void initViews(){
		lv_newcourse = (RTPullListView) view.findViewById(R.id.lv_course);
		lv_newcourse.setAdapter(adp_Course);
		new Net_GetCourse(Config.ACTION_GETHOTCOURSE,email, token, default_schoolId, default_deptId, 1, 20, new Net_GetCourse.SuccessCallback() {
			
			@Override
			public void onSuccess(List<Mdl_Course> list) {
				adp_Course.clear();
				adp_Course.addAll(list);
				
			}
		}, new Net_GetCourse.FailCallback() {
			
			@Override
			public void onFail(int error) {
				Toast.makeText(getActivity(), "获取课程列表失败！", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	private void initListener(){
		lv_newcourse.setonRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				
			}
		});
		lv_newcourse.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				lv_newcourse.onRefreshComplete();
				
			}
		});
	}
}

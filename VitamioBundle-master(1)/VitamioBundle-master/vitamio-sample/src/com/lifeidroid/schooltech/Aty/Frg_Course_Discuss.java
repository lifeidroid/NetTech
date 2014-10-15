package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseDiscuss;
import com.lifeidroid.schooltech.Net.Net_GetCourseDiscuss;
import com.lifeidroid.schooltech.Tools.ListViewFrame;

public class Frg_Course_Discuss extends Fragment implements
		ListViewFrame.IXListViewListener {
	private View view;
	private ListViewFrame lv_content;
	private String email;
	private String token;
	private int schoolId;
	private int deptId;
	private int courseId;
	private String cachePath;
	private Bundle bundle;
	private Adp_CourseDiscuss aDiscuss;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_discuss, null);
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
		aDiscuss = new Adp_CourseDiscuss(getActivity(), cachePath);
	}

	private void initViews() {
		lv_content = (ListViewFrame) view
				.findViewById(R.id.lv_coursediscuss_list);
		lv_content.setAdapter(aDiscuss);
		lv_content.setPullLoadEnable(true);
		lv_content.setPullRefreshEnable(true);
		lv_content.setXListViewListener(this);
		loaddata(Config.REFRESH);
	}

	private void loaddata(final int aciton) {
		new Net_GetCourseDiscuss(email, token, schoolId, deptId, courseId, 1,
				20, new Net_GetCourseDiscuss.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_CourseDiscuss> list) {
						if (aciton == Config.REFRESH) {

							aDiscuss.clear();
						}
						aDiscuss.addAll(list,
								Config.getCacheEmail(getActivity()));
						lv_content.stopLoadMore();
						lv_content.stopRefresh();

					}
				}, new Net_GetCourseDiscuss.FailCallback() {

					@Override
					public void onFail(int error) {
						lv_content.stopLoadMore();
						lv_content.stopRefresh();
						Toast.makeText(getActivity(), "获取评论列表失败！",
								Toast.LENGTH_SHORT).show();

					}
				});
	}

	@Override
	public void onRefresh() {
		loaddata(Config.REFRESH);

	}

	@Override
	public void onLoadMore() {
		loaddata(Config.LOADMORE);

	}
}
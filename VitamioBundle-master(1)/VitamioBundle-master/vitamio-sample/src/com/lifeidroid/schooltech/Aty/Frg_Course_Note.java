package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseDiscuss;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseNote;
import com.lifeidroid.schooltech.Net.Net_GetCourseDiscuss;
import com.lifeidroid.schooltech.Net.Net_GetCourseNote;
import com.lifeidroid.schooltech.Tools.ListViewFrame;

public class Frg_Course_Note extends Fragment implements
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
	private Adp_CourseNote aNote;
	private Mdl_CourseNote mdl_CourseNote;
	private int whetherCollected;
	private LinearLayout lay_content;
	private ImageView iv_content;
	private TextView tv_content;
	private ProgressBar pb_contentBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_note, null);
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
		aNote = new Adp_CourseNote(getActivity(), cachePath);
	}

	private void initViews() {
		lv_content = (ListViewFrame) view
				.findViewById(R.id.lv_frg_course_note_list);
		pb_contentBar = (ProgressBar)view.findViewById(R.id.pb_frg_course_note_content);
		pb_contentBar.setVisibility(View.GONE);
		lay_content = (LinearLayout)view.findViewById(R.id.lay_frg_courset_note_content);
		lay_content.setVisibility(View.GONE);
		iv_content = (ImageView)view.findViewById(R.id.iv_frg_course_note_content);
		tv_content = (TextView)view.findViewById(R.id.tv_frg_course_note_content);
		lv_content.setAdapter(aNote);
		lv_content.setPullLoadEnable(true);
		lv_content.setPullRefreshEnable(true);
		lv_content.setXListViewListener(this);
		pb_contentBar.setVisibility(View.VISIBLE);
		loaddata(Config.REFRESH);
	}
	private void initListener(){
		lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mdl_CourseNote = (Mdl_CourseNote) aNote.getItem(arg2-1);
				whetherCollected = mdl_CourseNote.getWhetherCollected();
			}
		});
	}

	private void loaddata(final int aciton) {

		new Net_GetCourseNote(email, token, schoolId, deptId, courseId, 1, 20,
				new Net_GetCourseNote.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_CourseNote> list) {
						pb_contentBar.setVisibility(View.GONE);
						if (aciton == Config.REFRESH) {

							aNote.clear();
						}
						aNote.addAll(list, email,token,schoolId,deptId,courseId);
						lv_content.stopLoadMore();
						lv_content.stopRefresh();
						if (0 == aNote.getCount()) {
							lv_content.setVisibility(View.VISIBLE);
							tv_content.setText(R.string.note_list_is_empoty);
							iv_content.setImageResource(R.drawable.img_note_bg);
						}

					}
				}, new Net_GetCourseNote.FailCallback() {

					@Override
					public void onFail(int error) {
						pb_contentBar.setVisibility(View.GONE);
						lv_content.stopLoadMore();
						lv_content.stopRefresh();
						lay_content.setVisibility(View.VISIBLE);
						iv_content.setImageResource(R.drawable.img_fail_bg);
						tv_content.setText(R.string.fail_load_note);
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

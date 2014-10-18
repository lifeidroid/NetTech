package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseList;
import com.lifeidroid.schooltech.Net.Net_GetCourseList;

public class Frg_Course_Chapter extends Fragment {
	private View view;
	private ListView lv_content;
	private String email;
	private String token;
	private int schoolId;
	private int deptId;
	private int courseId;
	private String cachePath;
	private Bundle bundle;
	private Adp_CourseChapter aList;
	private Intent intent;
	private String chapterUrl;
	private Mdl_CourseList mdl_CourseList;
	private LinearLayout lay_content;
	private ProgressBar pb_content;
	private ImageView iv_content;
	private TextView tv_content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_chapter, null);
		initViews();
		initListener();
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
		aList = new Adp_CourseChapter(getActivity());
	}

	private void initViews() {
		lv_content = (ListView) view.findViewById(R.id.lv_frg_course_chapter_list);
		lay_content = (LinearLayout)view.findViewById(R.id.lay_frg_courset_chapter_content);
		iv_content = (ImageView)view.findViewById(R.id.iv_frg_course_chapter_content);
		tv_content = (TextView)view.findViewById(R.id.tv_frg_course_chapter_content);
		pb_content = (ProgressBar)view.findViewById(R.id.pb_frg_course_chapter_content);
		lv_content.setAdapter(aList);
		loaddata();
	}

	private void initListener() {
		lv_content
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						mdl_CourseList = (Mdl_CourseList) aList.getItem(arg2);
						chapterUrl = mdl_CourseList.getChapterUrl();
						intent = new Intent(getActivity(), VideoViewDemo.class);
						intent.putExtra(Config.KEY_CHAPTER_URL, chapterUrl);
						startActivity(intent);

					}
				});
	}

	private void loaddata() {
		pb_content.setVisibility(View.VISIBLE);
		new Net_GetCourseList(email, token, schoolId, deptId, courseId,
				new Net_GetCourseList.SuccessCallback() {

					@Override
					public void onSuccess(List<Mdl_CourseList> list) {
						pb_content.setVisibility(View.GONE);
						aList.clear();
						aList.addAll(list);

					}
				}, new Net_GetCourseList.FailCallback() {

					@Override
					public void onFail(int error) {
						pb_content.setVisibility(View.GONE);
						iv_content.setImageResource(R.drawable.img_fail_bg);
						tv_content.setText(R.string.fail_get_chapter_list);
					}
				});
	}

}

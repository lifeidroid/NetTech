package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_School;
import com.lifeidroid.schooltech.Net.Net_GetSchool;

public class Frg_Course extends Fragment {
	private View view;
	private Bundle bundle;
	private String email;
	private String token;
	private String cachePath;
	private String default_schoolName;
	private int default_schoolID;
	private int default_deptID;
	private boolean switch_School;
	private Adp_School adp_School;
	private Mdl_School mdl_School;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Frg_Course_New frg_Course_New;
	private Frg_Course_Hot frg_Course_Hot ;
	private Frg_Course_Recommend fCourse_Recommend;

	private LinearLayout lay_shool_select;
	private ImageView iv_arrow;
	private TextView tv_schoolName;
	private ImageView iv_search;
	private ImageView iv_menu;
	private LinearLayout lay_course_main;
	private ListView lv_school;
	private RadioGroup rg_selectCouse;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course, null);
		initValues();
		initView();
		initListener();
		return view;
	}

	private void initValues() {
		bundle = getArguments();
		switch_School = false;
		email = bundle.getString(Config.KEY_EMAILMD5);
		token = bundle.getString(Config.KEY_TOKEN);
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		default_deptID = Config.getCacheDefaultDeptId(getActivity());
		default_schoolID = Config.getCacheDefaultSchoolId(getActivity());
		default_schoolName = Config.getCacheDefaultSchoolName(getActivity());
		fManager = getActivity().getFragmentManager();

		adp_School = new Adp_School(getActivity(), cachePath);
	}

	private void initView() {
		lay_shool_select = (LinearLayout) view
				.findViewById(R.id.lay_select_school);
		tv_schoolName = (TextView) view.findViewById(R.id.tv_course_schoolname);
		iv_arrow = (ImageView) view.findViewById(R.id.iv_course_arrow);
		iv_menu = (ImageView) view.findViewById(R.id.iv_course_menu);
		iv_search = (ImageView) view.findViewById(R.id.iv_course_search);
		rg_selectCouse = (RadioGroup) view.findViewById(R.id.rg_course);
		lay_course_main = (LinearLayout) view
				.findViewById(R.id.lay_course_main);
		lv_school = (ListView) view.findViewById(R.id.lv_course_school);
		lv_school.setAdapter(adp_School);
		iv_arrow.setImageResource(R.drawable.img_arrow_down);
		tv_schoolName.setText(default_schoolName);
		lv_school.setVisibility(View.GONE);

		fTransaction = fManager.beginTransaction();
		frg_Course_New = new Frg_Course_New();
		bundle = new Bundle();
		bundle.putString(Config.KEY_EMAILMD5, email);
		bundle.putString(Config.KEY_TOKEN, token);
		bundle.putString(Config.KEY_CACHEPATH, cachePath);
		bundle.putInt(Config.KEY_DEFAULT_DEPTID, default_deptID);
		bundle.putInt(Config.KEY_DEFAULT_SCHOOLID, default_schoolID);
		frg_Course_New.setArguments(bundle);
		fTransaction.replace(R.id.lay_course_container, frg_Course_New);
		fTransaction.commit();
	}

	private void initListener() {
		lay_shool_select.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (switch_School) {// 如果学校列表已经展开
					lv_school.setVisibility(View.GONE);
					switch_School = false;
					iv_arrow.setImageResource(R.drawable.img_arrow_down);
					lay_course_main.setVisibility(View.VISIBLE);
				} else {
					lv_school.setVisibility(View.VISIBLE);
					lay_course_main.setVisibility(View.GONE);
					switch_School = true;
					iv_arrow.setImageResource(R.drawable.img_arrow_up);
					new Net_GetSchool(email, token,
							new Net_GetSchool.SuccessCallback() {

								@Override
								public void onSuccess(List<Mdl_School> list) {
									adp_School.clear();
									adp_School.addAll(list, default_schoolID);

								}
							}, new Net_GetSchool.FailCallback() {

								@Override
								public void onFail(int error) {
									Toast.makeText(
											getActivity(),
											getString(R.string.get_school_list_fail),
											Toast.LENGTH_SHORT);
								}
							});
				}

			}
		});
		lv_school.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mdl_School = (Mdl_School) adp_School.getItem(position);
				default_schoolID = mdl_School.getSchoolId();
				default_schoolName = mdl_School.getSchoolName();
				tv_schoolName.setText(default_schoolName);
				Config.cacheDefaultSchoolId(getActivity(), default_schoolID);
				Config.cacheDefaultSchoolName(getActivity(), default_schoolName);
				lv_school.setVisibility(View.GONE);
				switch_School = false;
				iv_arrow.setImageResource(R.drawable.img_arrow_down);
				lay_course_main.setVisibility(View.VISIBLE);

			}
		});
		rg_selectCouse
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						fTransaction = fManager.beginTransaction();
						switch (arg1) {
						case R.id.rb_coursenew:
							frg_Course_New = new Frg_Course_New();
							bundle = new Bundle();
							bundle.putString(Config.KEY_EMAILMD5, email);
							bundle.putString(Config.KEY_TOKEN, token);
							bundle.putString(Config.KEY_CACHEPATH, cachePath);
							bundle.putInt(Config.KEY_DEFAULT_DEPTID, default_deptID);
							bundle.putInt(Config.KEY_DEFAULT_SCHOOLID, default_schoolID);
							frg_Course_New.setArguments(bundle);
							fTransaction.replace(R.id.lay_course_container,
									frg_Course_New);
							break;
						case R.id.rb_coursehot:
							frg_Course_Hot = new Frg_Course_Hot();
							bundle = new Bundle();
							bundle.putString(Config.KEY_EMAILMD5, email);
							bundle.putString(Config.KEY_TOKEN, token);
							bundle.putString(Config.KEY_CACHEPATH, cachePath);
							bundle.putInt(Config.KEY_DEFAULT_DEPTID, default_deptID);
							bundle.putInt(Config.KEY_DEFAULT_SCHOOLID, default_schoolID);
							frg_Course_Hot.setArguments(bundle);
							fTransaction.replace(R.id.lay_course_container,
									frg_Course_Hot);
							break;
						case R.id.rb_courserecommend:
							fCourse_Recommend = new Frg_Course_Recommend();
							bundle = new Bundle();
							bundle.putString(Config.KEY_EMAILMD5, email);
							bundle.putString(Config.KEY_TOKEN, token);
							bundle.putString(Config.KEY_CACHEPATH, cachePath);
							bundle.putInt(Config.KEY_DEFAULT_DEPTID, default_deptID);
							bundle.putInt(Config.KEY_DEFAULT_SCHOOLID, default_schoolID);
							fCourse_Recommend.setArguments(bundle);
							fTransaction.replace(R.id.lay_course_container,
									fCourse_Recommend);
							break;
						}
						fTransaction.commit();

					}
				});
	}
}

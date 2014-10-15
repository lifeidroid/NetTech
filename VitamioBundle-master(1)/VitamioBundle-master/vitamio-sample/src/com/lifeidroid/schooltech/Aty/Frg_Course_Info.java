package com.lifeidroid.schooltech.Aty;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Net.Net_GetCourseInfo;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Frg_Course_Info extends Fragment {
	private View view;
	private TextView tv_courseName;
	private RatingBar rb_grade;
	private TextView tv_techName;
	private TextView tv_studentNum;
	private TextView tv_techInfo;
	private CircleImageView iv_techHead;
	private TextView tv_courseInfo;
	private String email;
	private String token;
	private int schoolId;
	private int deptId;
	private int courseId;
	private String cachePath;
	private Bundle bundle;
	private String courseName;
	private String techName;
	private String studentNum;
	private float grade;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_info, null);
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
		techName = bundle.getString(Config.KEY_TECHNAME);
		courseName = bundle.getString(Config.KEY_COURSENAME);
		grade = bundle.getFloat(Config.KEY_GRADE);
		studentNum = bundle.getString(Config.KEY_STUDENTNUM);
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
	}

	private void initViews() {
		tv_courseName = (TextView) view
				.findViewById(R.id.tv_courseinfo_cousename);
		rb_grade = (RatingBar) view.findViewById(R.id.rb_courseinfo_grade);
		tv_techName = (TextView) view.findViewById(R.id.tv_courseinfo_techname);
		tv_studentNum = (TextView) view
				.findViewById(R.id.tv_courseinfo_studentnum);
		tv_techInfo = (TextView) view.findViewById(R.id.tv_courseinfo_techinfo);
		iv_techHead = (CircleImageView) view
				.findViewById(R.id.iv_courseinfo_techhead);
		tv_courseInfo = (TextView) view
				.findViewById(R.id.tv_courseinfo_courseinfo);

		tv_courseName.setText(courseName);
		tv_techName.setText(techName);
		rb_grade.setRating(grade);
		tv_studentNum.setText(studentNum);
		System.out.println("-------grade"+grade);
		System.out.println("-------studentNum"+studentNum);
		new Net_GetCourseInfo(email, token, schoolId, deptId, courseId,
				new Net_GetCourseInfo.SuccessCallback() {

					@Override
					public void onSuccess(String courseInfo, String techInfo,
							String techHead) {
						tv_techInfo.setText(techInfo);
						tv_courseInfo.setText(courseInfo);
						AsyncImageLoad(iv_techHead, techHead);

					}
				}, new Net_GetCourseInfo.FailCallback() {

					@Override
					public void onFail(int error) {
						Toast.makeText(getActivity(), "获取课程详细信息失败！",
								Toast.LENGTH_SHORT);

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

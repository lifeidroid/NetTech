package com.lifeidroid.schooltech.Net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseList;

public class Net_GetCourseList {
	private List<Mdl_CourseList> list = new ArrayList<Mdl_CourseList>();

	public Net_GetCourseList(String email, String token, int schoolId,
			int deptId, int courseId, final SuccessCallback successCallback,
			final FailCallback failCallback) {
		new NetConnection(
				Config.URL,
				HttpMethod.POST,
				new NetConnection.SuccessCallback() {

					@Override
					public void onSuccess(String result) {
						if (result != null) {
							try {
								JSONObject jobg = new JSONObject(result);
								switch (jobg.getInt(Config.KEY_STATUS)) {
								case Config.SUCCESS:
									JSONArray jsonArray = jobg
											.getJSONArray(Config.KEY_ITEMS);
									JSONObject jsonObject;
									for (int i = 0; i < jsonArray.length()-1; i++) {
										jsonObject = jsonArray.getJSONObject(i);
										list.add(new Mdl_CourseList(
												jsonObject
														.getInt(Config.KEY_CHAPTERID),
												jsonObject
														.getString(Config.KEY_CHAPTERNAME),
												jsonObject
														.getString(Config.KEY_CHAPTERTIME)));
									}
									if (successCallback != null) {
										successCallback.onSuccess(list);
									}
									break;
								case Config.ERROR0:
									if (failCallback != null) {
										failCallback.onFail(Config.ERROR0);
									}
									break;
								case Config.ERROR1:
									if (failCallback != null) {
										failCallback.onFail(Config.ERROR1);
									}
									break;
								case Config.ERROR2:
									if (failCallback != null) {
										failCallback.onFail(Config.ERROR2);
									}
									break;

								default:
									break;
								}
							} catch (JSONException e) {
								if (failCallback != null) {
									failCallback.onFail(Config.ERROR0);
								}
								e.printStackTrace();
							}
						} else {
							if (failCallback != null) {
								failCallback.onFail(Config.ERROR0);
							}
						}

					}
				}, new NetConnection.FailCallback() {

					@Override
					public void onFail() {
						if (failCallback != null) {
							failCallback.onFail(Config.ERROR0);
						}

					}
				}, Config.KEY_ACTION, Config.ACTION_GETCOURSECHAPTER,
				Config.KEY_EMAILMD5, email, Config.KEY_TOKEN, token,
				Config.KEY_SCHOOLID, schoolId + "", Config.KEY_DEPTID, deptId
						+ "", Config.KEY_COURSEID, courseId + "");
	}

	public static interface SuccessCallback {
		void onSuccess(List<Mdl_CourseList> list);
	}

	public static interface FailCallback {
		void onFail(int error);
	}
}

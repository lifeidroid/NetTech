package com.lifeidroid.schooltech.Net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.Mdl.Mdl_Course;

public class Net_GetCollectedCourse {
	private List<Mdl_Course> list = new ArrayList<Mdl_Course>();

	public Net_GetCollectedCourse(String action,String email, String token,
			int page, int perpage, final SuccessCallback successCallback,
			final FailCallback failCallback) {
		new NetConnection(Config.URL, HttpMethod.POST,
				new NetConnection.SuccessCallback() {

					@Override
					public void onSuccess(String result) {
						if (result != null) {
							try {
								JSONObject jobg = new JSONObject(result);
								switch (jobg.getInt(Config.KEY_STATUS)) {
								case Config.SUCCESS:
									JSONArray jsonArray = jobg.getJSONArray(Config.KEY_ITEMS);
									JSONObject jsonObject;
									for (int i = 0; i < jsonArray.length()-1; i++) {
										jsonObject = jsonArray.getJSONObject(i);
										list.add(new Mdl_Course(jsonObject.getInt(Config.KEY_COURSEID)
												, jsonObject.getString(Config.KEY_COURSENAME)
												, jsonObject.getString(Config.KEY_TECHNAME)
												, jsonObject.getString(Config.KEY_COURSELOGO)
												,Float.parseFloat(jsonObject.getString(Config.KEY_GRADE)) 
												, jsonObject.getString(Config.KEY_STUDENTNUM)));
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
				}, Config.KEY_ACTION, action,
				Config.KEY_EMAILMD5, email, Config.KEY_TOKEN, token, Config.KEY_PAGE, page + "", Config.KEY_PERPAGE,
				perpage + "");
	}

	public static interface SuccessCallback {
		void onSuccess(List<Mdl_Course> list);
	}

	public static interface FailCallback {
		void onFail(int error);
	}

}


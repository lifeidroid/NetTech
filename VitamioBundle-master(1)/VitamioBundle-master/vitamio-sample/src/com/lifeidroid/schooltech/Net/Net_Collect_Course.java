package com.lifeidroid.schooltech.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;

public class Net_Collect_Course {
	public Net_Collect_Course(String email, String token, int schoolId,
			int deptId, int courseId, final SuccessCallback successCallback ,final FailCallback failCallback) {
		new NetConnection(Config.URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					try {
						JSONObject jobg = new JSONObject(result);
						switch (jobg.getInt(Config.KEY_STATUS)) {
						case Config.SUCCESS:
							if (successCallback != null) {
								successCallback.onSuccess();
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
		},  Config.KEY_ACTION, Config.ACTION_COLLECT_COURSE,
		Config.KEY_TOKEN, token, Config.KEY_EMAILMD5, email,
		Config.KEY_SCHOOLID, schoolId + "", Config.KEY_DEPTID, deptId
				+ "", Config.KEY_COURSEID, courseId + "");
	}
public static interface SuccessCallback{
	void onSuccess();
}
public static interface FailCallback{
	void onFail(int error);
}
}

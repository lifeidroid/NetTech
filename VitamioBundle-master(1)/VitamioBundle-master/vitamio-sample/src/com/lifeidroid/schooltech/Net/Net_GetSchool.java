package com.lifeidroid.schooltech.Net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.Mdl.Mdl_School;

public class Net_GetSchool {
	private List<Mdl_School> list = new ArrayList<Mdl_School>();

	public Net_GetSchool(String email, String token,
			final SuccessCallback successCallback,
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
									JSONObject jObject;
									for (int i = 0; i < jsonArray.length()-1; i++) {
										jObject = jsonArray.getJSONObject(i);
										list.add(new Mdl_School(jObject.getString(Config.KEY_SCHOOLNAME)
												,jObject.getInt(Config.KEY_SCHOOLID)
												,jObject.getString(Config.KEY_SCHOOLLOGO)
												,jObject.getInt(Config.KEY_COURSENUM)));
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
				}, Config.KEY_ACTION, Config.ACTION_GETSCHOOL,
				Config.KEY_EMAILMD5, email, Config.KEY_TOKEN, token);
	}

	public static interface SuccessCallback {
		void onSuccess(List<Mdl_School> list);
	}

	public static interface FailCallback {
		void onFail(int error);
	}
}

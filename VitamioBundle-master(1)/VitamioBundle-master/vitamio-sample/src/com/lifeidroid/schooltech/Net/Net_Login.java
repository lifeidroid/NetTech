package com.lifeidroid.schooltech.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;

public class Net_Login {
	public Net_Login(String email, String password,
			final SuccessCallback successCallback,
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
									if (successCallback != null) {
										successCallback.onSuccess(
												jobg.getString(Config.KEY_NIKENAME),
												jobg.getString(Config.KEY_HEAD),
												jobg.getString(Config.KEY_TOKEN),
												jobg.getString(Config.KEY_MOTTO));
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

						}

					}
				}, new NetConnection.FailCallback() {

					@Override
					public void onFail() {
						if (failCallback != null) {
							failCallback.onFail(Config.ERROR0);
						}

					}
				}, Config.KEY_ACTION,Config.ACTION_LOGIN,Config.KEY_EMAILMD5,
				email,Config.KEY_PASSWORD,password);

	}

	public static interface SuccessCallback {
		void onSuccess(String nikename, String head, String token, String motto);
	}

	public static interface FailCallback {
		void onFail(int error);
	}
}

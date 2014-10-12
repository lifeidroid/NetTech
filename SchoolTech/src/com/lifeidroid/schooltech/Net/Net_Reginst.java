package com.lifeidroid.schooltech.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;

public class Net_Reginst {
	public Net_Reginst(String email, String password,
			final SuccessCallback successCallback,
			final FailCallbackc failCallbackc) {
		new NetConnection(Config.URL, HttpMethod.POST,
				new NetConnection.SuccessCallback() {

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
									if (failCallbackc != null) {
										failCallbackc.onFail(Config.ERROR0);
									}
									break;
								default:
									break;
								}
							} catch (JSONException e) {
								if (failCallbackc != null) {
									failCallbackc.onFail(Config.ERROR0);
								}
								e.printStackTrace();
							}
						} else {
							if (failCallbackc != null) {
								failCallbackc.onFail(Config.ERROR0);
							}
						}

					}
				}, new NetConnection.FailCallback() {

					@Override
					public void onFail() {
						if (failCallbackc != null) {
							failCallbackc.onFail(Config.ERROR0);
						}

					}
				}, Config.KEY_ACTION, Config.ACTION_REGISTER,
				Config.KEY_EMAILMD5, email, Config.KEY_PASSWORD, password);
	}

	public static interface SuccessCallback {
		void onSuccess();
	}

	public static interface FailCallbackc {
		void onFail(int error);
	}

}

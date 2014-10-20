package com.lifeidroid.schooltech.Net;

import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;

public class Net_Change_Info {
	public Net_Change_Info(String email,String token,String nikename,String motto,int sex,String school,final SuccessCallback successCallback,final FailCallback failCallback) {
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
		}, Config.KEY_ACTION,Config.ACTION_MODIFICATION,
		Config.KEY_EMAILMD5,email,
		Config.KEY_TOKEN,token,
		Config.KEY_NIKENAME,nikename,
		Config.KEY_MOTTO,motto,
		Config.KEY_SEX,sex+"",
		Config.KEY_SCHOOLNAME,school);
	}
	public static interface SuccessCallback {
		void onSuccess();
	}

	public static interface FailCallback {
		void onFail(int error);
	}
}

package com.lifeidroid.schooltech.Net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseNote;

public class Net_GetCollectedNote {
	private List<Mdl_CourseNote> list = new ArrayList<Mdl_CourseNote>();
	public Net_GetCollectedNote(String email,String token,int page,int prepage, final SuccessCallback successCallback,final FailCallback failCallback) {
		new NetConnection(Config.URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					try {
						JSONObject jobg = new JSONObject(result);
						switch (jobg.getInt(Config.KEY_STATUS)) {
						case Config.SUCCESS:
							JSONArray jsonArray = jobg
									.getJSONArray(Config.KEY_ITEMS);
							JSONObject jObject;
							for (int i = 0; i < jsonArray.length() - 1; i++) {
								jObject = jsonArray.getJSONObject(i);
								list.add(new Mdl_CourseNote(
										jObject.getInt(Config.KEY_NOTEID),
										jObject.getString(Config.KEY_STUDENTEMAIL),
										jObject.getString(Config.KEY_STUDNETNIKE),
										jObject.getString(Config.KEY_STUDENTHEAD),
										jObject.getString(Config.KEY_NOTECONTENT),
										jObject.getString(Config.KEY_NOTETIME),
										jObject.getInt(Config.KEY_COLLECTNUM),
										1));
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
		}, Config.KEY_ACTION,Config.ACTION_GETCOLLECTEDNOTE,
		Config.KEY_EMAILMD5,email,
		Config.KEY_TOKEN,token,
		Config.KEY_PAGE,page+"",
		Config.KEY_PERPAGE,prepage+"");
	}
	public static interface SuccessCallback{
		void onSuccess(List<Mdl_CourseNote> list);
	}
	public static interface FailCallback{
		void onFail(int error);
	}
}

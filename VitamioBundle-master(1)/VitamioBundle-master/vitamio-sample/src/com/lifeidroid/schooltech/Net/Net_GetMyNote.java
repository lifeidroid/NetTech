package com.lifeidroid.schooltech.Net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.Mdl.Mdl_MyNote;

public class Net_GetMyNote {
	private List<Mdl_MyNote> list = new ArrayList<Mdl_MyNote>();
	public Net_GetMyNote(String email,String token,int page,int perpage,final SuccessCallback successCallback ,final FailCallback failCallback) {
		new NetConnection(Config.URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				if (result != null) {
					try {
						JSONObject jobj = new JSONObject(result);
						switch (jobj.getInt(Config.KEY_STATUS)) {
						case Config.SUCCESS:
							JSONArray jsonArray = jobj.getJSONArray(Config.KEY_ITEMS);
							JSONObject jObject;
							for (int i = 0; i < jsonArray.length()-1; i++) {
								jObject = jsonArray.getJSONObject(i);
								list.add(new Mdl_MyNote(jObject.getInt(Config.KEY_NOTEID), 
										jObject.getString(Config.KEY_NOTECONTENT), 
										jObject.getString(Config.KEY_NOTETIME), 
										jObject.getInt(Config.KEY_COLLECTNUM)));
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
				}else {
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
		}, Config.KEY_ACTION,Config.ACTION_GETMYNOTE,
		Config.KEY_TOKEN,token,
		Config.KEY_EMAILMD5,email,
		Config.KEY_PAGE,page+"",
		Config.KEY_PERPAGE,perpage+"");
	}
	public static interface SuccessCallback{
		void onSuccess(List<Mdl_MyNote> list);
	}
	public static interface FailCallback{
		void onFail(int error);
	}
}

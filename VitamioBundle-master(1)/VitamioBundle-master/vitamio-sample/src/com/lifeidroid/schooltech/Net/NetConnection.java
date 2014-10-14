package com.lifeidroid.schooltech.Net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.os.AsyncTask;

import com.lifeidroid.schooltech.Config;

public class NetConnection{
	public NetConnection(final String url,final HttpMethod method,final SuccessCallback successCallback,final FailCallback failCallback,final String ...kvs){
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... arg0) {
				StringBuffer paramsStr = new StringBuffer();
				for (int i = 0; i < kvs.length; i+= 2) {
					try {
						paramsStr.append(kvs[i]).append("=").append(URLEncoder.encode(kvs[i+1],"UTF-8")).append("&");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}					
				}
								
				try {
					URLConnection uc;
					switch (method) {
					case POST:
						uc = new URL(url).openConnection();
						uc.setDoOutput(true);
						BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream()));
						bW.write(paramsStr.toString());
						bW.flush();
						break;
					default:						
						uc = new URL(url + "?" +paramsStr.toString()).openConnection();
						break;
					}
					System.out.println("Request URL:" + uc.getURL());
					System.out.println("Request data:" + paramsStr.toString());
					BufferedReader bR = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
					String line = null;
					StringBuffer result = new StringBuffer();
					while((line = bR.readLine())!= null){
						result.append(line);
					}
					System.out.println("Request :" + result.toString());
					return result.toString();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(String result) {
				if(result != null){
					if(successCallback != null){
						successCallback.onSuccess(result);
					}
				}else {
					if(failCallback != null){
						failCallback.onFail();
					}
				}
				super.onPostExecute(result);
			}
		}.execute();
	}	
	public static interface SuccessCallback{
		void onSuccess(String result);
	}
	public static interface FailCallback{
		void onFail();
	}

}
package com.lifeidroid.schooltech.Aty;

import java.io.File;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import cn.jpush.android.api.JPushInterface;

public class App_Main extends Application {
	private String cachePath;
	private String path;
	private File dirFile;
	private Intent intent;

	@Override
	public void onCreate() {
		super.onCreate();

		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);


		path = getSDPath() + "/SchoolTech/";
		dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		cachePath = dirFile.toString();
	}

	public String getCachePath() {
		return cachePath;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		for (File file : dirFile.listFiles()) {
			file.delete();
		}
		dirFile.delete();
	}

	private String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
		}
		return sdDir.toString();
	}

}

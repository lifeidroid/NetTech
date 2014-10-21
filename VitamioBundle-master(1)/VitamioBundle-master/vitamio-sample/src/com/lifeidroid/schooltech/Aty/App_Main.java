package com.lifeidroid.schooltech.Aty;

import java.io.File;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import cn.jpush.android.api.JPushInterface;

public class App_Main extends Application {
	private Intent intent;

	@Override
	public void onCreate() {
		super.onCreate();

		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);


	}


/*	@Override
	public void onTerminate() {
		super.onTerminate();
		for (File file : dirFile.listFiles()) {
			file.delete();
		}
		dirFile.delete();
	}*/



}

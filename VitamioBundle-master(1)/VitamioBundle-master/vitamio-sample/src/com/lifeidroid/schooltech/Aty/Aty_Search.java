package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.os.Bundle;
import cn.jpush.android.api.JPushInterface;

import com.lifeidroid.schooltech.R;

public class Aty_Search extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_search);
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
	}
}

package com.lifeidroid.schooltech.Rec;

import com.lifeidroid.schooltech.Aty.App_Main;
import com.lifeidroid.schooltech.Aty.Aty_Login;
import com.lifeidroid.schooltech.Aty.Aty_Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.jpush.android.api.JPushInterface;

public class Rec_Jpush extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		Bundle bundle = intent.getExtras();
		String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
		;
		if (JPushInterface.ACTION_NOTIFICATION_OPENED
				.equals(intent.getAction())) {

			System.out.println("------>[MyReceiver] 用户点击打开了通知");

			// 打开自定义的Activity
			Intent i = new Intent(context, Aty_Login.class);
			// i.putExtras(bundle);

			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

			context.startActivity(i);

			// Activity 被打开，上报服务器统计。
			JPushInterface.reportNotificationOpened(context,
					bundle.getString(JPushInterface.EXTRA_MSG_ID));

		}
	}

}

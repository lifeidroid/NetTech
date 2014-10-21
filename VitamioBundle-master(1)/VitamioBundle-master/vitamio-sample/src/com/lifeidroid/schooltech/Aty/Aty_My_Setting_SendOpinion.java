package com.lifeidroid.schooltech.Aty;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.Net_SendOpinion;

public class Aty_My_Setting_SendOpinion extends BaseActivity {
	private String email;
	private String token;
	private ImageView iv_back;
	private TextView tv_back;
	private TextView tv_send;
	private EditText et_content;
	private static Toast mToast;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_setting_opinion);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		intent = getIntent();
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		token = intent.getExtras().getString(Config.KEY_TOKEN);
	}
	private void initViews(){
		iv_back = (ImageView) findViewById(R.id.iv_aty_opinion_cancel_logo);
		tv_back = (TextView) findViewById(R.id.tv_aty_opinion_cancel_text);
		tv_send = (TextView) findViewById(R.id.tv_aty_opinion_send);
		et_content = (EditText) findViewById(R.id.et_aty_opinion_content);
		
	}
	private void initListeners(){
		tv_send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_content.getText())) {
					showToast(Aty_My_Setting_SendOpinion.this, "内容不能为空！", Toast.LENGTH_SHORT);
				}
				final ProgressDialog pg = new ProgressDialog(Aty_My_Setting_SendOpinion.this).show(Aty_My_Setting_SendOpinion.this, null, "正在提交...");
				new Net_SendOpinion(email, token, et_content.getText().toString(), new Net_SendOpinion.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						pg.dismiss();
						showToast(Aty_My_Setting_SendOpinion.this, "提交成功，谢谢您的意见！", Toast.LENGTH_SHORT);
						finish();
						
					}
				}, new Net_SendOpinion.FailCallback() {
					
					@Override
					public void onFail(int error) {
						pg.dismiss();
						showToast(Aty_My_Setting_SendOpinion.this, "提交失败，请重试！", Toast.LENGTH_SHORT);
						
					}
				});
				
			}
		});
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		tv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		
	}
	public static void showToast(Context context, String text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}

		mToast.show();
	}
}

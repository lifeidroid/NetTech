package com.lifeidroid.schooltech.Aty;

import com.lifeidroid.schooltech.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Aty_SendOpinion extends Activity {
	private String email;
	private String token;
	private ImageView iv_back;
	private TextView tv_back;
	private TextView tv_send;
	private EditText et_content;
	private static Toast mToast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_setting_opinion);
		initValues();
		initViews();
		initListeners();
	}
	private void initValues(){
		
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
					showToast(Aty_SendOpinion.this, "内容不能为空！", Toast.LENGTH_SHORT);
				}
				
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

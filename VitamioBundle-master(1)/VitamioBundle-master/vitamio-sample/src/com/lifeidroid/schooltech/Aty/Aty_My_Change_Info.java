package com.lifeidroid.schooltech.Aty;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.BaseActivity;
import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.Net_Change_Info;

public class Aty_My_Change_Info extends BaseActivity {
	private String email;
	private String token;
	private String nikename;
	private int sex = 0;
	private String motto;
	private ImageView iv_back;
	private TextView tv_back;
	private EditText et_nike;
	private EditText et_motto;
	private RadioGroup rg_sex;
	private Spinner sp_school;
	private Button btn_send;
	private static Toast mToast;
	private Intent intent;
	private ArrayAdapter<String> adapter;
	//private List<String> list = new ArrayList<String>();
	private String[] ary=new String[]{"石家庄学院","唐山学院","邢台学院","衡水学院","保定学院","石家庄经济学院","石家庄铁路学院","河北经贸"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_my_change_info);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ary);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		intent = getIntent();
		email = intent.getExtras().getString(Config.KEY_EMAILMD5);
		token = intent.getExtras().getString(Config.KEY_TOKEN);
		nikename = intent.getExtras().getString(Config.KEY_NIKENAME);
		motto = intent.getExtras().getString(Config.KEY_MOTTO);
	}
	private void initViews(){
		iv_back = (ImageView)findViewById(R.id.iv_aty_my_change_info_cancel_logo);
		tv_back = (TextView)findViewById(R.id.tv_aty_my_change_info_cancel_text);
		et_nike = (EditText)findViewById(R.id.et_aty_my_change_info_nikename);
		et_motto = (EditText)findViewById(R.id.et_aty_my_change_info_motto);
		sp_school = (Spinner)findViewById(R.id.sp_aty_my_change_info_school);
		sp_school.setAdapter(adapter);
		rg_sex = (RadioGroup)findViewById(R.id.rg_aty_my_change_info_sex);
		btn_send = (Button)findViewById(R.id.btn_aty_my_change_info_send);
		et_nike.setText(nikename);
		et_motto.setText(motto);
	}
	private void initListener(){
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
		rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				switch (arg1) {
				case R.id.rb_aty_my_change_info_boy:
					sex = 0;
					break;
				case R.id.rb_aty_my_change_info_girl:
					sex = 1;
					break;
				default:
					break;
				}
				
			}
		});
		btn_send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_nike.getText())) {
					return;
				}
				if (TextUtils.isEmpty(et_motto.getText())) {
					return;
				}
				System.out.println("---->"+sp_school.getSelectedItem().toString());
				new Net_Change_Info(email, token, nikename, motto, sex, sp_school.getSelectedItem().toString(), new Net_Change_Info.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						showToast(Aty_My_Change_Info.this, "修改成功,下次登陆将刷新！", Toast.LENGTH_SHORT);
						finish();
					}
				}, new Net_Change_Info.FailCallback() {
					
					@Override
					public void onFail(int error) {
						showToast(Aty_My_Change_Info.this, "修改失败！", Toast.LENGTH_SHORT);
						
					}
				});
				
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

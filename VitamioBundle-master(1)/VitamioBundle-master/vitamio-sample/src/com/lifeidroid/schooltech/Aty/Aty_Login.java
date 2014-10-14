package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.Net_Login;

public class Aty_Login extends Activity {
	private Button btn_riginst;
	private Button btn_login;
	private EditText et_email;
	private EditText et_password;
	private static Toast mToast;
	private String email;
	private String password;
	private String Motto;
	private String Head;
	private String NikeName;
	private String Token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_login);
		initValues();
		initView();
		initListener();
	}

	private void initValues() {
		email = Config.getCacheEmail(Aty_Login.this);
		password = Config.getCachePassword(Aty_Login.this);
	}

	private void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_riginst = (Button) findViewById(R.id.btn_reginst);
		et_email = (EditText) findViewById(R.id.et_login_email);
		et_password = (EditText) findViewById(R.id.et_login_password);
		et_email.setText(email);
		et_password.setText(password);

	};

	private void initListener() {
		btn_riginst.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Aty_Login.this, Aty_Riginst.class));

			}
		});
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_email.getText())) {
					System.out.println("----->email");
					showToast(Aty_Login.this,
							getString(R.string.email_can_not_empoty),
							Toast.LENGTH_SHORT);
					return;
				}
				if (TextUtils.isEmpty(et_password.getText())) {
					showToast(Aty_Login.this,
							getString(R.string.password_can_not_empty),
							Toast.LENGTH_SHORT);
					return;
				}
				final ProgressDialog pg = new ProgressDialog(Aty_Login.this).show(
						Aty_Login.this, null, "正在登录...");
				new Net_Login(et_email.getText().toString(), et_password
						.getText().toString(), new Net_Login.SuccessCallback() {

					@Override
					public void onSuccess(String nikename, String head,
							String token, String motto) {
						pg.dismiss();
						Config.cacheEmail(Aty_Login.this, et_email.getText()
								.toString());
						Config.cachePassword(Aty_Login.this, et_password
								.getText().toString());
						Intent intent = new Intent(Aty_Login.this,
								Aty_Main.class);
						intent.putExtra(Config.KEY_NIKENAME, nikename);
						intent.putExtra(Config.KEY_HEAD, head);
						intent.putExtra(Config.KEY_TOKEN, token);
						intent.putExtra(Config.KEY_MOTTO, motto);
						intent.putExtra(Config.KEY_EMAILMD5, et_email.getText().toString());
						startActivity(intent);

					}
				}, new Net_Login.FailCallback() {

					@Override
					public void onFail(int error) {
						pg.dismiss();
						switch (error) {
						case Config.ERROR0:
							showToast(Aty_Login.this,
									getString(R.string.login_fail),
									Toast.LENGTH_SHORT);
							break;
						case Config.ERROR1:
							showToast(Aty_Login.this,
									getString(R.string.user_do_not_exist),
									Toast.LENGTH_SHORT);
							et_email.setText("");
							et_password.setText("");
							break;
						case Config.ERROR2:
							showToast(Aty_Login.this,
									getString(R.string.password_not_right),
									Toast.LENGTH_SHORT);
							et_password.setText("");
							break;

						default:
							break;
						}

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

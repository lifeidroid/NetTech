package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.Net_Reginst;

public class Aty_Riginst extends Activity {
	private EditText et_email;
	private EditText et_passoword;
	private EditText et_repassword;
	private Button btn_reginst;
	private static Toast mToast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_reginst);
		initValues();
		initView();
		initListener();
	}

	private void initValues() {

	}

	private void initView() {
		et_email = (EditText) findViewById(R.id.et_reginst_email);
		et_passoword = (EditText) findViewById(R.id.et_reginst_password);
		et_repassword = (EditText) findViewById(R.id.et_reginst_repassword);
		btn_reginst = (Button) findViewById(R.id.btn_reginst_reginst);

	}

	private void initListener() {
		final ProgressDialog pg = new ProgressDialog(Aty_Riginst.this).show(
				Aty_Riginst.this, null, "正在注册..");
		btn_reginst.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_email.getText())) {
					showToast(Aty_Riginst.this,
							getString(R.string.email_can_not_empoty),
							Toast.LENGTH_SHORT);
					return;
				}
				if (TextUtils.isEmpty(et_passoword.getText())) {
					showToast(Aty_Riginst.this,
							getString(R.string.password_can_not_empty),
							Toast.LENGTH_SHORT);
					return;
				}
				if (TextUtils.isEmpty(et_repassword.getText())) {
					showToast(Aty_Riginst.this,
							getString(R.string.please_input_password_agin),
							Toast.LENGTH_SHORT);
					return;
				}
				if (!et_passoword.getText().toString()
						.equals(et_repassword.getText().toString())) {
					showToast(
							Aty_Riginst.this,
							getString(R.string.two_times_input_password_not_same),
							Toast.LENGTH_SHORT);
					return;
				}
				new Net_Reginst(et_email.getText().toString(), et_passoword
						.getText().toString(),
						new Net_Reginst.SuccessCallback() {

							@Override
							public void onSuccess() {
								pg.dismiss();
								showToast(Aty_Riginst.this,
										getString(R.string.register_success),
										Toast.LENGTH_SHORT);
								finish();
							}
						}, new Net_Reginst.FailCallbackc() {

							@Override
							public void onFail(int error) {
								pg.dismiss();
								showToast(Aty_Riginst.this,
										getString(R.string.register_fail),
										Toast.LENGTH_SHORT);

							}
						});

			}
		});

		findViewById(R.id.lay_cancel).setOnClickListener(
				new View.OnClickListener() {

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

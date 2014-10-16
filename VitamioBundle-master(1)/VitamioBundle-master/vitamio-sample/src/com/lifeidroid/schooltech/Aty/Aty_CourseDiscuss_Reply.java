package com.lifeidroid.schooltech.Aty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Net.Net_Send_Discuss;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Aty_CourseDiscuss_Reply extends Activity {
	private Intent mIntent;
	private String email;
	private String token;
	private int schoolId;
	private int deptId;
	private int courseId;
	private int discussId;
	private String cachePath;
	private String studentHead;
	private String studentNike;
	private String discussTime;
	private String discussContent;
	private CircleImageView iv_head;
	private TextView tv_nike;
	private TextView tv_time;
	private TextView tv_content;
	private EditText et_content;
	private ImageView iv_back;
	private TextView tv_back;
	private TextView tv_send;
	private static Toast mToast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_coursediscuss_reply);
		initValues();
		initViews();
		initListener();
	}
	private void initValues(){
		mIntent = getIntent();
		email = mIntent.getExtras().getString(Config.KEY_EMAILMD5);
		token = mIntent.getExtras().getString(Config.KEY_TOKEN);
		schoolId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_SCHOOLID);
		deptId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_DEPTID);
		courseId = mIntent.getExtras().getInt(Config.KEY_COURSEID);
		discussId = mIntent.getExtras().getInt(Config.KEY_DISUCUSSID);
		cachePath =mIntent.getExtras().getString(Config.KEY_CACHEPATH);
		studentHead = mIntent.getExtras().getString(Config.KEY_STUDENTHEAD);
		studentNike = mIntent.getExtras().getString(Config.KEY_STUDNETNIKE);
		discussContent = mIntent.getExtras().getString(Config.KEY_DISCUSSCONTENT);
		discussTime = mIntent.getExtras().getString(Config.KEY_DISCUSSTIME);
	}
	private void initViews(){
		iv_head = (CircleImageView)findViewById(R.id.iv_coursediscuss_reply_head);
		tv_nike = (TextView)findViewById(R.id.tv_coursediscuss_reply_nike);
		tv_time = (TextView)findViewById(R.id.tv_coursediscuss_reply_date);
		tv_content = (TextView)findViewById(R.id.tv_coursediscuss_reply_content);
		et_content = (EditText)findViewById(R.id.et_coursediscuss_reply_content);
		iv_back = (ImageView)findViewById(R.id.iv_coursediscuss_reply_backimg);
		tv_back = (TextView)findViewById(R.id.tv_coursediscuss_reply_back);
		tv_send = (TextView)findViewById(R.id.tv_coursediscuss_reply_send);
		AsyncImageLoad(iv_head, studentHead);
		tv_nike.setText(studentNike);
		tv_time.setText(discussTime);
		tv_content.setText(discussContent);
	}
	private void initListener(){
		tv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		iv_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
				
			}
		});
		tv_send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (TextUtils.isEmpty(et_content.getText())) {
					showToast(Aty_CourseDiscuss_Reply.this, getString(R.string.reply_can_not_empty),  Toast.LENGTH_SHORT);
					return;

				}
				final ProgressDialog pg = new ProgressDialog(Aty_CourseDiscuss_Reply.this).show(Aty_CourseDiscuss_Reply.this, null, "发送中...");
				new Net_Send_Discuss(email, token, schoolId, deptId, courseId, discussId, et_content.getText().toString(), new Net_Send_Discuss.SuccessCallback() {
					
					@Override
					public void onSucess() {
						setResult(Config.RESOULT_NEED_REFRESH);
						pg.dismiss();
						showToast(Aty_CourseDiscuss_Reply.this, getString(R.string.reply_success), Toast.LENGTH_SHORT);
						finish();
					}
				}, new Net_Send_Discuss.FailCallback() {
					
					@Override
					public void onFail(int error) {
						pg.dismiss();
						showToast(Aty_CourseDiscuss_Reply.this,getString(R.string.reply_send_fail), Toast.LENGTH_SHORT);
						
					}
				});
				
			}
		});
	}
	
	
	private void AsyncImageLoad(CircleImageView ivHead, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(ivHead);
		asyncImageTask.execute(path);
	}
	private class AsyncImageTask extends AsyncTask<String, Integer, Uri>{
		private ImageView imageView;
		public AsyncImageTask(ImageView imageView) {
			this.imageView = imageView;
		}
		@Override
		protected Uri doInBackground(String... arg0) {	//运行在子线程中
			Uri uri = NetGetImage.getImage(arg0[0],cachePath);
			return uri;
		}
		@Override
		protected void onPostExecute(Uri result) {		//运行在主线程中
			if (result != null && imageView != null) {
				imageView.setImageURI(result);
			}
		}
		
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

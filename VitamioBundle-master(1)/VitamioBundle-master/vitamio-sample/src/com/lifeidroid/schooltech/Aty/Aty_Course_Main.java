package com.lifeidroid.schooltech.Aty;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Net.Net_Collect_Course;
import com.lifeidroid.schooltech.Net.Net_Send_Discuss_For_Course;
import com.lifeidroid.schooltech.Net.Net_Send_Note_For_Course;

public class Aty_Course_Main extends FragmentActivity {
	private Bundle mBundle;
	private Intent mIntent;
	private String email;
	private String token;
	private String courseLogo;
	private String courseName;
	private String cachePath;
	private String techName;
	private int deptId;
	private int schoolId;
	private int courseId;
	private String studentNum;
	private float grade;
	private static Toast mToast;

	private ImageView iv_coursemain_menu;
	private LinearLayout lay_back;
	private TextView tv_course_title;
	private RadioGroup rg_select;
	private ImageView iv_course_logo;
	private FragmentManager fManager;
	private FragmentTransaction fTransaction;
	private Frg_Course_Info frg_Course_Info;
	private Frg_Course_Discuss frg_Course_Discuss;
	private Frg_Course_Note frg_Course_Note;
	private Frg_Course_Chapter frg_Course_List;
	private View view;
	private Button btn_send_discuss;
	private Button btn_send_note;
	private Button btn_collect_course;
	private Button btn_share_course;
	private Button btn_cancel;
	private Dialog dlg_menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_course_main);
		initValues();
		initViews();
		initListener();
	}

	private void initValues() {
		mIntent = getIntent();
		email = mIntent.getExtras().getString(Config.KEY_EMAILMD5);
		token = mIntent.getExtras().getString(Config.KEY_TOKEN);
		schoolId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_SCHOOLID);
		deptId = mIntent.getExtras().getInt(Config.KEY_DEFAULT_DEPTID);
		courseId = mIntent.getExtras().getInt(Config.KEY_COURSEID);
		courseName = mIntent.getExtras().getString(Config.KEY_COURSENAME);
		courseLogo = mIntent.getExtras().getString(Config.KEY_COURSELOGO);
		techName = mIntent.getExtras().getString(Config.KEY_TECHNAME);
		cachePath = mIntent.getExtras().getString(Config.KEY_CACHEPATH);
		grade = mIntent.getExtras().getFloat(Config.KEY_GRADE);
		studentNum = mIntent.getExtras().getString(Config.KEY_STUDENTNUM);
		fManager = getSupportFragmentManager();
	}

	private void initViews() {
		iv_coursemain_menu = (ImageView) findViewById(R.id.iv_coursemain_menu);
		lay_back = (LinearLayout) findViewById(R.id.lay_coursemain_cancel);
		tv_course_title = (TextView) findViewById(R.id.tv_coursemain_title);
		rg_select = (RadioGroup) findViewById(R.id.rg_coursemain_select);
		iv_course_logo = (ImageView) findViewById(R.id.iv_coursemain_logo);

		System.out.println("---->courseName" + courseName);
		tv_course_title.setText(courseName);
		System.out.println("------->courselogoselect" + courseLogo);
		AsyncImageLoad(iv_course_logo, courseLogo);
		fTransaction = fManager.beginTransaction();

		mBundle = new Bundle();
		frg_Course_Info = new Frg_Course_Info();
		mBundle.putString(Config.KEY_EMAILMD5, email);
		mBundle.putString(Config.KEY_TOKEN, token);
		mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
		mBundle.putInt(Config.KEY_DEPTID, deptId);
		mBundle.putInt(Config.KEY_COURSEID, courseId);
		mBundle.putString(Config.KEY_TECHNAME, techName);
		mBundle.putString(Config.KEY_COURSENAME, courseName);
		mBundle.putString(Config.KEY_CACHEPATH, cachePath);
		mBundle.putString(Config.KEY_STUDENTNUM, studentNum);
		mBundle.putFloat(Config.KEY_GRADE, grade);
		frg_Course_Info.setArguments(mBundle);
		fTransaction.replace(R.id.lay_coursemain_container, frg_Course_Info);
		fTransaction.commit();

	}

	private void initListener() {
		lay_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
		iv_coursemain_menu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog();

			}
		});
		rg_select
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup arg0, int arg1) {
						fTransaction = fManager.beginTransaction();
						switch (arg1) {
						case R.id.rb_courseinfo:
							frg_Course_Info = new Frg_Course_Info();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_TECHNAME, techName);
							mBundle.putString(Config.KEY_COURSENAME, courseName);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							mBundle.putString(Config.KEY_STUDENTNUM, studentNum);
							mBundle.putFloat(Config.KEY_GRADE, grade);
							frg_Course_Info.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container,
									frg_Course_Info);
							break;
						case R.id.rb_coursediscuss:
							frg_Course_Discuss = new Frg_Course_Discuss();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_Discuss.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container,
									frg_Course_Discuss);
							break;
						case R.id.rb_coursenote:
							frg_Course_Note = new Frg_Course_Note();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_Note.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container,
									frg_Course_Note);
							break;
						case R.id.rb_courselist:
							frg_Course_List = new Frg_Course_Chapter();
							mBundle.putString(Config.KEY_EMAILMD5, email);
							mBundle.putString(Config.KEY_TOKEN, token);
							mBundle.putInt(Config.KEY_SCHOOLID, schoolId);
							mBundle.putInt(Config.KEY_DEPTID, deptId);
							mBundle.putInt(Config.KEY_COURSEID, courseId);
							mBundle.putString(Config.KEY_CACHEPATH, cachePath);
							frg_Course_List.setArguments(mBundle);
							fTransaction.replace(R.id.lay_coursemain_container,
									frg_Course_List);
							break;

						default:
							break;
						}
						fTransaction.commit();
					}
				});

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			showDialog();
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void showDialog() {
		view = getLayoutInflater().inflate(R.layout.dlg_course_menu, null);
		dlg_menu = new Dialog(this, R.style.transparentFrameWindowStyle);
		dlg_menu.setContentView(view, new LayoutParams(
		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		Window window = dlg_menu.getWindow();// 设置显示动画
		window.setWindowAnimations(R.style.main_menu_animstyle);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = getWindowManager().getDefaultDisplay().getHeight();// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		dlg_menu.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		dlg_menu.setCanceledOnTouchOutside(true);
		btn_send_discuss = (Button) view
				.findViewById(R.id.btn_dlg_course_menu_send_discuss);
		btn_send_note = (Button) view
				.findViewById(R.id.btn_dlg_course_menu_send_note);
		btn_collect_course = (Button) view
				.findViewById(R.id.btn_dlg_course_menu_collect_course);
		btn_share_course = (Button) view
				.findViewById(R.id.btn_dlg_course_menu_share_course);
		btn_cancel = (Button) view
				.findViewById(R.id.btn_dlg_course_menu_cancel);
		initDialogListener();
		dlg_menu.show();
	}

	private void initDialogListener() {
		btn_send_discuss.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dlg_menu.dismiss();
				View view_send_discuss = getLayoutInflater().inflate(
						R.layout.dlg_send_course_discuss, null);
				final Dialog dlg_sendDiscuss = new Dialog(Aty_Course_Main.this,
						R.style.transparentFrameWindowStyle);
				dlg_sendDiscuss.setContentView(view_send_discuss,
						new LayoutParams(LayoutParams.FILL_PARENT,
								LayoutParams.WRAP_CONTENT));
				dlg_sendDiscuss.setCanceledOnTouchOutside(true);
				TextView tv_send = (TextView) view_send_discuss
						.findViewById(R.id.tv_dlg_send_course_discuss_send);
				TextView tv_cancel = (TextView) view_send_discuss
						.findViewById(R.id.tv_dlg_send_course_discuss_cancel);
				final RatingBar rb_grade = (RatingBar) view_send_discuss
						.findViewById(R.id.rb_dlg_send_course_discuss_grade);
				final EditText et_content = (EditText) view_send_discuss
						.findViewById(R.id.et_dlg_send_course_discuss_content);
				tv_cancel.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dlg_sendDiscuss.dismiss();

					}
				});
				tv_send.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (TextUtils.isEmpty(et_content.getText())) {
							showToast(Aty_Course_Main.this, "讨论内容不能为空！",
									Toast.LENGTH_SHORT);
							return;
						}
						final ProgressDialog pd = new ProgressDialog(
								Aty_Course_Main.this).show(
								Aty_Course_Main.this, null, "正在发送...");
						new Net_Send_Discuss_For_Course(
								email,
								token,
								schoolId,
								deptId,
								courseId,
								et_content.getText().toString(),
								String.valueOf(rb_grade.getRating()),
								new Net_Send_Discuss_For_Course.SuccessCallback() {

									@Override
									public void onSuccess() {
										pd.dismiss();
										dlg_sendDiscuss.dismiss();
										showToast(Aty_Course_Main.this,
												"发送成功！", Toast.LENGTH_SHORT);

									}
								},
								new Net_Send_Discuss_For_Course.FailCallback() {

									@Override
									public void onFail(int error) {
										pd.dismiss();
										showToast(Aty_Course_Main.this,
												"发送失败！", Toast.LENGTH_SHORT);

									}
								});

					}
				});
				dlg_sendDiscuss.show();

			}
		});
		btn_send_note.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dlg_menu.dismiss();
				View view_send_note = getLayoutInflater().inflate(
						R.layout.dlg_send_course_note, null);
				final Dialog dlg_sendnote = new Dialog(Aty_Course_Main.this,
						R.style.transparentFrameWindowStyle);
				dlg_sendnote.setContentView(view_send_note, new LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
				dlg_sendnote.setCanceledOnTouchOutside(true);
				TextView tv_send = (TextView) view_send_note
						.findViewById(R.id.tv_dlg_send_course_note_send);
				TextView tv_cancel = (TextView) view_send_note
						.findViewById(R.id.tv_dlg_send_course_note_cancel);
				final EditText et_content = (EditText) view_send_note
						.findViewById(R.id.et_dlg_send_course_note_content);
				tv_send.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						if (TextUtils.isEmpty(et_content.getText())) {
							showToast(Aty_Course_Main.this, "笔记内容不能为空！",
									Toast.LENGTH_SHORT);
							return;
						}
						final ProgressDialog pd = new ProgressDialog(Aty_Course_Main.this).show(Aty_Course_Main.this, null, "正在发送...");
						new Net_Send_Note_For_Course(email, token, schoolId,deptId, courseId, et_content.getText().toString(),
								new Net_Send_Note_For_Course.SuccessCallback() {

									@Override
									public void onSuccess() {
										pd.dismiss();
										dlg_sendnote.dismiss();
										showToast(Aty_Course_Main.this,
												"发送成功！", Toast.LENGTH_SHORT);

									}
								}, new Net_Send_Note_For_Course.FailCallback() {

									@Override
									public void onFail(int error) {
										pd.dismiss();
										showToast(Aty_Course_Main.this,
												"发送失败！", Toast.LENGTH_SHORT);

									}
								});

					}
				});
				tv_cancel.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dlg_sendnote.dismiss();

					}
				});
				dlg_sendnote.show();

			}
		});
		btn_collect_course.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final ProgressDialog pd = new ProgressDialog(Aty_Course_Main.this).show(Aty_Course_Main.this, null, "拼命收藏...");
				new Net_Collect_Course(email, token, schoolId, deptId, courseId, new Net_Collect_Course.SuccessCallback() {
					
					@Override
					public void onSuccess() {
						showToast(Aty_Course_Main.this, "收藏成功！", Toast.LENGTH_SHORT);
						dlg_menu.dismiss();
						pd.dismiss();
						
					}
				}, new Net_Collect_Course.FailCallback() {
					
					@Override
					public void onFail(int error) {
						showToast(Aty_Course_Main.this, "收藏失败！", Toast.LENGTH_SHORT);
						pd.dismiss();
					}
				});

			}
		});
		btn_share_course.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		//菜单取消按钮
		btn_cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dlg_menu.dismiss();

			}
		});
	}
	

	

	private void AsyncImageLoad(ImageView ivHead, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(ivHead);
		asyncImageTask.execute(path);
	}

	private class AsyncImageTask extends AsyncTask<String, Integer, Uri> {
		private ImageView imageView;

		public AsyncImageTask(ImageView imageView) {
			this.imageView = imageView;
		}

		@Override
		protected Uri doInBackground(String... arg0) { // 运行在子线程中
			Uri uri = NetGetImage.getImage(arg0[0], cachePath);
			return uri;
		}

		@Override
		protected void onPostExecute(Uri result) { // 运行在主线程中
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

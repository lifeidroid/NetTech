package com.lifeidroid.schooltech.Aty;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Frg_My extends Fragment {
	private View view;
	private Bundle bundle;
	private String cachePath;
	private String token;
	private String email;
	private String motto;
	private String head;
	private String nikename;
	private Intent intent;
	private CircleImageView iv_userHead;
	private TextView tv_userNike;
	private TextView tv_userMotto;
	private RelativeLayout lay_discuss;
	private RelativeLayout lay_note;
	private RelativeLayout lay_collect;
	private RelativeLayout lay_plan;
	private RelativeLayout lay_set;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initValues();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_my, null);
		initViews();
		initListener();
		return view;
	}

	private void initValues() {
		bundle = getArguments();
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		token = bundle.getString(Config.KEY_TOKEN);
		email = bundle.getString(Config.KEY_EMAILMD5);
		nikename = bundle.getString(Config.KEY_NIKENAME);
		head = bundle.getString(Config.KEY_HEAD);
		motto = bundle.getString(Config.KEY_MOTTO);
	}

	private void initViews() {
		iv_userHead = (CircleImageView)view.findViewById(R.id.iv_fry_my_ursrhead);
		tv_userNike = (TextView)view.findViewById(R.id.tv_frg_my_username);
		tv_userMotto = (TextView)view.findViewById(R.id.tv_frg_my_motto);
		lay_discuss = (RelativeLayout)view.findViewById(R.id.lay_frg_my_discuss);
		lay_note = (RelativeLayout)view.findViewById(R.id.lay_frg_my_note);
		lay_collect = (RelativeLayout)view.findViewById(R.id.lay_frg_my_collect);
		lay_plan = (RelativeLayout)view.findViewById(R.id.lay_frg_my_plan);
		lay_set = (RelativeLayout)view.findViewById(R.id.lay_frg_my_set);
		AsyncImageLoad(iv_userHead, head);
		tv_userNike.setText(nikename);
		tv_userMotto.setText(motto);

	}

	private void initListener() {
		iv_userHead.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				intent = new Intent(getActivity(),Aty_My_Change_Info.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_NIKENAME, nikename);
				intent.putExtra(Config.KEY_MOTTO, motto);
				startActivity(intent);
				
			}
		});
		lay_discuss.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getActivity(),Aty_My_Discuss.class));
				
			}
		});
		lay_note.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),Aty_My_Note.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_NIKENAME, nikename);
				intent.putExtra(Config.KEY_HEAD	, head);
				intent.putExtra(Config.KEY_CACHEPATH, cachePath);
				startActivity(intent);
			}
		});
		lay_collect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),Aty_My_Collect.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_CACHEPATH, cachePath);
				startActivity(intent);
			}
		});
		lay_plan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getActivity(),Aty_My_plane.class));
				
			}
		});
		lay_set.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent = new Intent(getActivity(),Aty_My_Setting.class);
				intent.putExtra(Config.KEY_EMAILMD5, email);
				intent.putExtra(Config.KEY_TOKEN, token);
				intent.putExtra(Config.KEY_CACHEPATH, cachePath);
				startActivity(intent);
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
	
}

package com.lifeidroid.schooltech.Aty;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lifeidroid.schooltech.Config;
import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_MyNote;
import com.lifeidroid.schooltech.Net.Net_GetMyNote;
import com.lifeidroid.schooltech.Tools.ListViewFrame;

public class Frg_My_Note_Self extends Fragment implements ListViewFrame.IXListViewListener{
	private ListViewFrame lv_list;
	private View view;
	private Adp_MyNote adpter;
	private ProgressBar pBar;
	private LinearLayout lay_content;
	private ImageView iv_content;
	private TextView tv_content;
	private String cachePath;
	private String token;
	private String email;
	private String headUrl;
	private String nike;
	
	private Bundle bundle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		initValues();
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.frg_course_note, null);
		initViews();
		initListener();
		return view;
	}
	private void initValues(){
		bundle = getArguments();
		cachePath = bundle.getString(Config.KEY_CACHEPATH);
		headUrl = bundle.getString(Config.KEY_HEAD);
		nike = bundle.getString(Config.KEY_NIKENAME);
		email = bundle.getString(Config.KEY_EMAILMD5);
		token = bundle.getString(Config.KEY_TOKEN);
		adpter = new Adp_MyNote(getActivity(), cachePath);
	}
	private void initViews(){
		lv_list = (ListViewFrame)view.findViewById(R.id.lv_frg_course_note_list);
		lv_list.setAdapter(adpter);
		lay_content = (LinearLayout)view.findViewById(R.id.lay_frg_courset_note_content);
		iv_content = (ImageView)view.findViewById(R.id.iv_frg_course_note_content);
		pBar = (ProgressBar)view.findViewById(R.id.pb_frg_course_note_content);
		tv_content = (TextView)view.findViewById(R.id.tv_frg_course_note_content);
		pBar.setVisibility(View.VISIBLE);
		lay_content.setVisibility(View.GONE);
		loadData(Config.REFRESH);
	}
	private void initListener(){
		lv_list.setPullLoadEnable(true);
		lv_list.setPullRefreshEnable(true);
		lv_list.setXListViewListener(this);
	}
	private void loadData(final int action){
		new Net_GetMyNote(email, token, 1, 20, new Net_GetMyNote.SuccessCallback() {
			
			@Override
			public void onSuccess(List<Mdl_MyNote> list) {
				if (action == Config.REFRESH) {
					adpter.clear();
					pBar.setVisibility(View.GONE);
				}
				adpter.addAll(list, headUrl, nike);
				lv_list.stopLoadMore();
				lv_list.stopRefresh();
				if (0 == adpter.getCount()) {
					lay_content.setVisibility(View.VISIBLE);
					tv_content.setText("原创笔记列表为空！");
					iv_content.setImageResource(R.drawable.img_note_bg);
				}
			}
		}, new Net_GetMyNote.FailCallback() {
			
			@Override
			public void onFail(int error) {
				lay_content.setVisibility(View.VISIBLE);
				tv_content.setText("获取笔记列表失败！");
				pBar.setVisibility(View.GONE);
				iv_content.setImageResource(R.drawable.img_fail_bg);
				lv_list.stopLoadMore();
				lv_list.stopRefresh();
			}
		});
	}
	@Override
	public void onRefresh() {
		loadData(Config.REFRESH);
		
	}
	@Override
	public void onLoadMore() {
		loadData(Config.LOADMORE);
		
	}
}

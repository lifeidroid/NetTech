package com.lifeidroid.schooltech.Aty;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_School;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Tools.CircleImageView;


public class Adp_School extends BaseAdapter {
	private List<Mdl_School> list= new ArrayList<Mdl_School>();
	private Context context;
	private String cachePath;
	private int selectedSchoolId;

	public Adp_School(Context context, String cachePath) {
		this.cachePath = cachePath;
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public String getCachePath() {
		return cachePath;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View layout, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (layout == null) {
			layout = LayoutInflater.from(getContext()).inflate(
					R.layout.cell_school, null);
			viewHolder = new ViewHolder((CircleImageView)layout.findViewById(R.id.iv_cellschool_logo),
					(TextView)layout.findViewById(R.id.tv_cellschool_name),
					(TextView)layout.findViewById(R.id.tv_cellschool_coursenum)
					, (ImageView)layout.findViewById(R.id.iv_cellschool_selected));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)layout.getTag();
		viewHolder.getTv_schoolName().setText(list.get(position).getSchoolName());
		viewHolder.getTv_courseNum().setText("课程："+list.get(position).getCourseNum());
		if(selectedSchoolId == list.get(position).getSchoolId()){
			viewHolder.getIv_selected().setImageResource(R.drawable.img_school_selected);
		}else {
			viewHolder.getIv_selected().setImageDrawable(null);
		}
		if (!TextUtils.isEmpty(list.get(position).getSchoollogo())) {
			AsyncImageLoad(viewHolder.getIv_schoolLogo(), list.get(position).getSchoollogo());
		}else {
			viewHolder.getIv_schoolLogo().setImageResource(R.drawable.img_school_logo);
		}
		return layout;
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
			Uri uri = NetGetImage.getImage(arg0[0],getCachePath());
			return uri;
		}
		@Override
		protected void onPostExecute(Uri result) {		//运行在主线程中
			if (result != null && imageView != null) {
				imageView.setImageURI(result);
			}
	}
		
	}
	

	private class ViewHolder {
		private CircleImageView iv_schoolLogo;
		private TextView tv_schoolName;
		private TextView tv_courseNum;
		private ImageView iv_selected;

		public ViewHolder(CircleImageView iv_schoolLogo, TextView tv_schoolName,
				TextView tv_courseNum, ImageView iv_selected) {
			super();
			this.iv_schoolLogo = iv_schoolLogo;
			this.tv_schoolName = tv_schoolName;
			this.tv_courseNum = tv_courseNum;
			this.iv_selected = iv_selected;
		}

		public CircleImageView getIv_schoolLogo() {
			return iv_schoolLogo;
		}

		public TextView getTv_schoolName() {
			return tv_schoolName;
		}

		public TextView getTv_courseNum() {
			return tv_courseNum;
		}

		public ImageView getIv_selected() {
			return iv_selected;
		}
	}

	public void addAll(List<Mdl_School> list, int selectedSchoolId) {
		this.selectedSchoolId = selectedSchoolId;
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}
}

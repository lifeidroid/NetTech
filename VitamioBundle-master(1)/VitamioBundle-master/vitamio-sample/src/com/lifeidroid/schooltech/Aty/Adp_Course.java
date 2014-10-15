package com.lifeidroid.schooltech.Aty;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_Course;
import com.lifeidroid.schooltech.Net.NetGetImage;

public class Adp_Course extends BaseAdapter {
	private List<Mdl_Course> list = new ArrayList<Mdl_Course>();
	private String cachePath;
	private Context context;
	public Adp_Course(Context context,String cachePath) {
		this.context = context;
		this.cachePath = cachePath;
	}
	public String getCachePath() {
		return cachePath;
	}
	public Context getContext() {
		return context;
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
			layout = LayoutInflater.from(getContext()).inflate(R.layout.cell_course, null);
			viewHolder = new ViewHolder(
					(ImageView)layout.findViewById(R.id.iv_cell_courselogo)
					, (TextView)layout.findViewById(R.id.tv_cellcourse_name)
					, (TextView)layout.findViewById(R.id.tv_cellcourse_techname)
					, (RatingBar)layout.findViewById(R.id.rb_cellcourse_grade)
					, (TextView)layout.findViewById(R.id.tv_cellcourse_studnum));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)layout.getTag();
		viewHolder.getTv_courseName().setText(list.get(position).getCourseName());
		viewHolder.getTv_techName().setText(list.get(position).getTechName());
		viewHolder.getRb_grade().setRating(list.get(position).getGarde());
		viewHolder.getTv_studentNum().setText(list.get(position).getStudentNum());
		if (list.get(position).getCourseLogo() != null) {
			AsyncImageLoad(viewHolder.getIv_courseLogo(), list.get(position).getCourseLogo());
		}else {
			viewHolder.getIv_courseLogo().setImageDrawable(null);
		}
		return layout;
	}
	
	private void AsyncImageLoad(ImageView ivHead, String path) {
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
	
	
	private class ViewHolder{
		private ImageView iv_courseLogo;
		private TextView tv_courseName;
		private TextView tv_techName;
		private RatingBar rb_grade;
		private TextView tv_studentNum;
		public ViewHolder(ImageView iv_courseLogo, TextView tv_courseName,
				TextView tv_techName, RatingBar rb_grade, TextView tv_studentNum) {
			super();
			this.iv_courseLogo = iv_courseLogo;
			this.tv_courseName = tv_courseName;
			this.tv_techName = tv_techName;
			this.rb_grade = rb_grade;
			this.tv_studentNum = tv_studentNum;
		}
		public ImageView getIv_courseLogo() {
			return iv_courseLogo;
		}
		public void setIv_courseLogo(ImageView iv_courseLogo) {
			this.iv_courseLogo = iv_courseLogo;
		}
		public TextView getTv_courseName() {
			return tv_courseName;
		}
		public void setTv_courseName(TextView tv_courseName) {
			this.tv_courseName = tv_courseName;
		}
		public TextView getTv_techName() {
			return tv_techName;
		}
		public void setTv_techName(TextView tv_techName) {
			this.tv_techName = tv_techName;
		}
		public RatingBar getRb_grade() {
			return rb_grade;
		}
		public void setRb_grade(RatingBar rb_grade) {
			this.rb_grade = rb_grade;
		}
		public TextView getTv_studentNum() {
			return tv_studentNum;
		}
		public void setTv_studentNum(TextView tv_studentNum) {
			this.tv_studentNum = tv_studentNum;
		}
		
	}
	public void addAll(List<Mdl_Course> list){
		this.list.addAll(list);
		notifyDataSetChanged();
	}
	public void clear(){
		list.clear();
		notifyDataSetChanged();
	}

}

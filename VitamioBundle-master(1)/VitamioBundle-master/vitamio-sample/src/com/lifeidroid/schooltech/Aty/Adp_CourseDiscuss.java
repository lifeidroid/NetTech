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
import android.widget.TextView;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseDiscuss;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Adp_CourseDiscuss extends BaseAdapter {
	private List<Mdl_CourseDiscuss> list = new ArrayList<Mdl_CourseDiscuss>();
	private String cachePath;
	private Context context;
	private String myEmial;

	public Adp_CourseDiscuss(Context context, String cachePath) {
		this.context = context;
		this.cachePath = cachePath;
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
					R.layout.cell_course_discuss, null);
			viewHolder = new ViewHolder(
					(CircleImageView) layout
							.findViewById(R.id.iv_cell_coursediscuss_head),
					(TextView) layout
							.findViewById(R.id.tv_cell_coursediscuss_nike),
					(TextView) layout
							.findViewById(R.id.tv_cell_coursediscuss_date),
					(TextView) layout
							.findViewById(R.id.tv_cell_coursediscuss_content),
					(TextView) layout
							.findViewById(R.id.tv_cell_coursediscuss_num));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) layout.getTag();
		viewHolder.getTv_Nike().setText(list.get(position).getStudentNike());
		viewHolder.getTv_Time().setText(list.get(position).getDiscussTime());
		viewHolder.getTv_content().setText(list.get(position).getContent());
		viewHolder.getTv_replayNum().setText(list.get(position).getReplyNum() + "");
		if (!list.get(position).getStudentHead().isEmpty()) {
			AsyncImageLoad(viewHolder.getIv_Head(), list.get(position).getStudentHead());
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
	
	
	private class ViewHolder {
		private CircleImageView iv_Head;
		private TextView tv_Nike;
		private TextView tv_Time;
		private TextView tv_content;
		private TextView tv_replayNum;

		public ViewHolder(CircleImageView iv_Head, TextView tv_Nike,
				TextView tv_Time, TextView tv_content, TextView tv_replayNum) {
			super();
			this.iv_Head = iv_Head;
			this.tv_Nike = tv_Nike;
			this.tv_Time = tv_Time;
			this.tv_content = tv_content;
			this.tv_replayNum = tv_replayNum;
		}

		public CircleImageView getIv_Head() {
			return iv_Head;
		}

		public TextView getTv_content() {
			return tv_content;
		}

		public TextView getTv_Nike() {
			return tv_Nike;
		}

		public TextView getTv_replayNum() {
			return tv_replayNum;
		}

		public TextView getTv_Time() {
			return tv_Time;
		}

	}

	public void addAll(List<Mdl_CourseDiscuss> list, String myEmail) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void clear() {
		this.list.clear();
		notifyDataSetChanged();
	}

}

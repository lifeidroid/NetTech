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
import com.lifeidroid.schooltech.Mdl.Mdl_CourseNote;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Adp_CollectedNote extends BaseAdapter {
	private String cachePath;
	private Context context;
	private ViewHolder viewHolder;
	private List<Mdl_CourseNote> list = new ArrayList<Mdl_CourseNote>();

	public Adp_CollectedNote( Context context,String cachePath) {
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
	public View getView(final int positon, View layout, ViewGroup arg2) {
		if (layout == null) {
			layout = LayoutInflater.from(getContext()).inflate(R.layout.cell_course_note, null);
			viewHolder = new ViewHolder((CircleImageView) layout.findViewById(R.id.iv_cell_coursenote_head)
					, (TextView)layout.findViewById(R.id.tv_cell_coursenote_nike)
							, (TextView)layout.findViewById(R.id.tv_cell_coursenote_date)
							, (TextView)layout.findViewById(R.id.tv_cell_coursenote_content)
							, (ImageView)layout.findViewById(R.id.iv_cell_coursenotecellct_img)
							, (TextView)layout.findViewById(R.id.tv_cell_courscenotellect_num));
		layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)layout.getTag();
		viewHolder.getTv_time().setText(list.get(positon).getNoteTime());
		viewHolder.getTv_content().setText(list.get(positon).getContent());
		viewHolder.getTv_cellectNum().setText(list.get(positon).getCollectNum()+"");
		viewHolder.getTv_nike().setText(list.get(positon).getStudentNike());
		viewHolder.getIv_cellect().setImageResource(R.drawable.img_hart_on);
		if (!list.get(positon).getStudentHead().isEmpty()) {
		AsyncImageLoad(viewHolder.getIv_head(), list.get(positon).getStudentHead());
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
		private CircleImageView iv_head;
		private TextView tv_nike;
		private TextView tv_time;
		private TextView tv_content;
		private ImageView iv_cellect;
		private TextView tv_cellectNum;

		public ViewHolder(CircleImageView iv_head, TextView tv_nike,
				TextView tv_time, TextView tv_content, ImageView iv_cellect,
				TextView tv_cellectNum) {
			super();
			this.iv_head = iv_head;
			this.tv_nike = tv_nike;
			this.tv_time = tv_time;
			this.tv_content = tv_content;
			this.iv_cellect = iv_cellect;
			this.tv_cellectNum = tv_cellectNum;
		}

		public CircleImageView getIv_head() {
			return iv_head;
		}

		public ImageView getIv_cellect() {
			return iv_cellect;
		}

		public TextView getTv_cellectNum() {
			return tv_cellectNum;
		}

		public TextView getTv_content() {
			return tv_content;
		}

		public TextView getTv_nike() {
			return tv_nike;
		}

		public TextView getTv_time() {
			return tv_time;
		}
	}

	public void addAll(List<Mdl_CourseNote> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void clear() {
		this.list.clear();
		notifyDataSetChanged();
	}

}

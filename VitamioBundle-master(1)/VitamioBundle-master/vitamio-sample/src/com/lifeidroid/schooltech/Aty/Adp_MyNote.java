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
import com.lifeidroid.schooltech.Mdl.Mdl_MyNote;
import com.lifeidroid.schooltech.Net.NetGetImage;
import com.lifeidroid.schooltech.Tools.CircleImageView;

public class Adp_MyNote extends BaseAdapter {
	private List<Mdl_MyNote> list = new ArrayList<Mdl_MyNote>();
	private String cachePath;
	private Context context;
	private String headUrl;
	private String nike;
	public Adp_MyNote(Context context,String cachePath) {
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
			layout = LayoutInflater.from(getContext()).inflate(R.layout.cell_course_note, null);
			viewHolder = new ViewHolder((CircleImageView)layout.findViewById(R.id.iv_cell_coursenote_head)
					, (TextView)layout.findViewById(R.id.tv_cell_coursenote_nike),
					(TextView)layout.findViewById(R.id.tv_cell_coursenote_date)
					, (TextView)layout.findViewById(R.id.tv_cell_coursenote_content)
					, (TextView)layout.findViewById(R.id.tv_cell_courscenotellect_num)
					, (ImageView)layout.findViewById(R.id.iv_cell_coursenotecellct_img));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)layout.getTag();
		viewHolder.getTvNike().setText(nike);
		viewHolder.getTvTime().setText(list.get(position).getNoteTime());
		viewHolder.getTv_Content().setText(list.get(position).getContent());
		viewHolder.getTv_CollectedNum().setText(list.get(position).getCollectedNum()+"");
		viewHolder.getIv_Collected().setImageResource(R.drawable.img_hart_on);
		AsyncImageLoad(viewHolder.getIvHead(), headUrl);
		return layout;
	}
	private class ViewHolder{
		private CircleImageView ivHead;
		private TextView tvNike;
		private TextView tvTime;
		private TextView tv_Content;
		private TextView tv_CollectedNum;
		private ImageView iv_Collected;
		public ViewHolder(CircleImageView ivHead, TextView tvNike, TextView tvTime,
				TextView tv_Content, TextView tv_CollectedNum,
				ImageView iv_Collected) {
			super();
			this.ivHead = ivHead;
			this.tvNike = tvNike;
			this.tvTime = tvTime;
			this.tv_Content = tv_Content;
			this.tv_CollectedNum = tv_CollectedNum;
			this.iv_Collected = iv_Collected;
		}
		public ImageView getIv_Collected() {
			return iv_Collected;
		}
		public CircleImageView getIvHead() {
			return ivHead;
		}
		public TextView getTv_CollectedNum() {
			return tv_CollectedNum;
		}
		public TextView getTv_Content() {
			return tv_Content;
		}
		public TextView getTvNike() {
			return tvNike;
		}
		public TextView getTvTime() {
			return tvTime;
		}
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
	
	
	public void addAll(List<Mdl_MyNote> list,String headUrl,String nike){
		this.list.addAll(list);
		this.headUrl = headUrl;
		this.nike = nike;
		notifyDataSetChanged();
	}
	public void clear(){
		this.list.clear();
		notifyDataSetChanged();
	}

}

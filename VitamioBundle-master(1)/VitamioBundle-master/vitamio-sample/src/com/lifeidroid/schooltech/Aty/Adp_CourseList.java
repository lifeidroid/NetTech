package com.lifeidroid.schooltech.Aty;

import java.util.ArrayList;
import java.util.List;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_CourseList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adp_CourseList extends BaseAdapter {
	private List<Mdl_CourseList> list = new ArrayList<Mdl_CourseList>();
	private Context context;

	public Adp_CourseList(Context context) {
		this.context = context;
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
			layout = LayoutInflater.from(getContext()).inflate(
					R.layout.cell_courselist, null);
			viewHolder = new ViewHolder(
					(TextView) layout
							.findViewById(R.id.tv_cell_courselist_title),
					(TextView) layout
							.findViewById(R.id.tv_cell_courselist_time));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder) layout.getTag();
		viewHolder.getTv_title().setText(list.get(position).getChapterName());
		viewHolder.getTv_time().setText(list.get(position).getChaptertime());
		return layout;
	}

	private class ViewHolder {
		private TextView tv_title;
		private TextView tv_time;

		public ViewHolder(TextView tv_title, TextView tv_time) {
			super();
			this.tv_title = tv_title;
			this.tv_time = tv_time;
		}

		public TextView getTv_time() {
			return tv_time;
		}

		public TextView getTv_title() {
			return tv_title;
		}
	}

	public void addAll(List<Mdl_CourseList> list) {
		this.list.addAll(list);
		notifyDataSetChanged();
	}

	public void clear() {
		list.clear();
		notifyDataSetChanged();
	}
}

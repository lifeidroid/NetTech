package com.lifeidroid.schooltech.Aty;

import java.util.ArrayList;
import java.util.List;

import com.lifeidroid.schooltech.R;
import com.lifeidroid.schooltech.Mdl.Mdl_Dept;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class Adp_Dept extends BaseAdapter {
	private List<Mdl_Dept> list = new ArrayList<Mdl_Dept>();
	private Context context;
	private int selectedDeptId;
	public Adp_Dept(Context context) {
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
			layout = LayoutInflater.from(getContext()).inflate(R.layout.cell_dept, null);
			viewHolder = new ViewHolder((TextView)layout.findViewById(R.id.tv_celldept_name)
					, (TextView)layout.findViewById(R.id.tv_celldept_coursenum)
					, (ImageView)layout.findViewById(R.id.iv_celldept_selected));
			layout.setTag(viewHolder);
		}
		viewHolder = (ViewHolder)layout.getTag();
		viewHolder.getTv_deptName().setText(list.get(position).getDeptname());
		viewHolder.getTv_courseNum().setText("课程："+list.get(position).getCoursenum());
		if (selectedDeptId == list.get(position).getDeptId()) {
			viewHolder.getIv_selected().setImageResource(R.drawable.img_dept_selected);
		}else {
			viewHolder.getIv_selected().setImageDrawable(null);
		}
		return layout;
	}
	private class ViewHolder{
		private TextView tv_deptName;
		private TextView tv_courseNum;
		private ImageView iv_selected;
		public ViewHolder(TextView tv_deptName, TextView tv_courseNum,
				ImageView iv_selected) {
			super();
			this.tv_deptName = tv_deptName;
			this.tv_courseNum = tv_courseNum;
			this.iv_selected = iv_selected;
		}
		public ImageView getIv_selected() {
			return iv_selected;
		}
		public TextView getTv_courseNum() {
			return tv_courseNum;
		}
		public TextView getTv_deptName() {
			return tv_deptName;
		}
	}
	public void addAll(List<Mdl_Dept> list,int selectedDeptId){
		this.list.addAll(list);
		this.selectedDeptId = selectedDeptId;
		notifyDataSetChanged();
	}
	public void clear(){
		this.list.clear();
		notifyDataSetChanged();
	}

}

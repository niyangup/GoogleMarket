package com.niyang.googlemarket.adapter;

import java.util.ArrayList;

import com.niyang.googlemarket.holder.BaseHolder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**对Adapter的封装
 * @author niyang
 *
 */
public  abstract class MyBaseAdapter<T> extends BaseAdapter{
	
	private ArrayList<T> data;

	public MyBaseAdapter(ArrayList<T> data) {
		this.data=data;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder;
		if (convertView==null) {
			//1.加载布局
			//2.初始化控件findviewbyid
			//3.打标记
			holder=getHolder();
		}else {
			holder = (BaseHolder) convertView.getTag();
		}
		
		//4.刷新数据
		holder.setData(getItem(position));
		
		return holder.getmRootView();
	}
	
	//返回当前页面的holder对象,必须子类实现
	public abstract BaseHolder<T> getHolder();

}

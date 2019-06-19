package com.niyang.googlemarket.fragment;

import java.util.ArrayList;

import com.niyang.googlemarket.R;
import com.niyang.googlemarket.adapter.MyBaseAdapter;
import com.niyang.googlemarket.holder.BaseHolder;
import com.niyang.googlemarket.holder.HomeHolder;
import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 首页
 * 
 * @author niyang
 *
 */
public class HomeFragment extends BaseFragment {

	private ArrayList<String> data;

	// 如果加载成功,就回调此方法,在主线程运行
	@Override
	public View onCreateSuccessView() {
		// TextView view = new TextView(UIUtils.getContext());
		// view.setText(getClass().getSimpleName());

		ListView view = new ListView(UIUtils.getContext());
		view.setAdapter(new HomeAdapter(data));
		return view;
	}

	// 运行在子线程,可以直接执行耗时网络操作
	@Override
	public ResultState onLoad() {
		data = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			data.add("测试数据:" + i);
		}

		return ResultState.STATE_SUCESS;
	}

	class HomeAdapter extends MyBaseAdapter<String> {

		public HomeAdapter(ArrayList<String> data) {
			super(data);
		}

		@Override
		public BaseHolder<String> getHolder() {
			return new HomeHolder();
		}

//		@Override
//		public View getView(int position, View convertView, ViewGroup parent) {
//			ViewHolder viewHolder = null;
//			if (convertView == null) {
//				viewHolder = new ViewHolder();
//
//				convertView = UIUtils.inflate(R.layout.list_item_home);
//				viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
//				convertView.setTag(viewHolder);
//			} else {
//				viewHolder = (ViewHolder) convertView.getTag();
//			}
//
//			String content = getItem(position);
//			viewHolder.content.setText(content);
//			return convertView;
//		}

	}

	static class ViewHolder {
		public TextView content;
	}
}

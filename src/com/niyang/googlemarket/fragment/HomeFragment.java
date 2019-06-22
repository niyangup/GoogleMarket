package com.niyang.googlemarket.fragment;

import java.util.ArrayList;

import com.niyang.googlemarket.adapter.MyBaseAdapter;
import com.niyang.googlemarket.domain.AppInfo;
import com.niyang.googlemarket.holder.BaseHolder;
import com.niyang.googlemarket.holder.HomeHolder;
import com.niyang.googlemarket.protocol.HomeProtocol;
import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.os.SystemClock;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 首页
 * 
 * @author niyang
 *
 */
public class HomeFragment extends BaseFragment {

	private ArrayList<AppInfo> data;

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
//		data = new ArrayList<String>();
//		for (int i = 0; i < 20; i++) {
//			data.add("测试数据:" + i);
//		}
		
		HomeProtocol protocol=new HomeProtocol();
		data = protocol.getData(0);

		return check(data);
	}

	class HomeAdapter extends MyBaseAdapter<AppInfo> {

		public HomeAdapter(ArrayList<AppInfo> data) {
			super(data);
		}
		

		@Override
		public BaseHolder<AppInfo> getHolder() {
			return new HomeHolder();
		}

		//此方法在子线程调用
		@Override
		public ArrayList<AppInfo> onLoadMore() {
//			ArrayList<AppInfo> moreData=new ArrayList<AppInfo>();
//			for (int i = 0; i < 10; i++) {
//				moreData.add("测试更多数据:"+i);
//			}
//			SystemClock.sleep(2000);
			
			HomeProtocol protocol = new HomeProtocol();
			ArrayList<AppInfo>moreData=protocol.getData(getListSize());
			return moreData;
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

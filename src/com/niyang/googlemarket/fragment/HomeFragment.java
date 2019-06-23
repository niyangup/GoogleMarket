package com.niyang.googlemarket.fragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.niyang.googlemarket.adapter.MyBaseAdapter;
import com.niyang.googlemarket.domain.AppInfo;
import com.niyang.googlemarket.domain.HomeData;
import com.niyang.googlemarket.holder.BaseHolder;
import com.niyang.googlemarket.holder.HomeHolder;
import com.niyang.googlemarket.protocol.HomeProtocol;
import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.MyListView;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

		MyListView view = new MyListView(UIUtils.getContext());
		view.setAdapter(new HomeAdapter(data));

		return view;
	}

	// 运行在子线程,可以直接执行耗时网络操作
	@Override
	public ResultState onLoad() {
		data = getDataFromFile();
//		假数据
//		data = new ArrayList<AppInfo>();
//		for (int i = 0; i < 20; i++) {
//			AppInfo appInfo = new AppInfo();
//			appInfo.name = "测试数据:" + i;
//			appInfo.size = new Random().nextInt(3876203);
//			appInfo.stars = new Random().nextInt(6);
//			appInfo.des = "就算生活再苦逼，我们仍需小快乐！笑霸来了，给你不一样的快乐。独乐乐，不如众乐乐。";
//
//			data.add(appInfo);
//		}

		// HomeProtocol protocol=new HomeProtocol();
		// data = protocol.getData(0);

		return check(data);
	}

	private ArrayList getDataFromFile() {
		try {
			InputStream in = getActivity().getAssets().open("homelist0");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = in.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			String content = new String(baos.toByteArray(), "UTF-8");
			in.close();
			Log.v("TAG", content);
			
			Gson gson = new Gson();
			HomeData homeData = gson.fromJson(content, HomeData.class);
			Log.v("TAG", "这是解析完毕后的数据appinfo:"+homeData.list.get(0).des);
			return homeData.list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	class HomeAdapter extends MyBaseAdapter<AppInfo> {

		public HomeAdapter(ArrayList<AppInfo> data) {
			super(data);
		}

		@Override
		public BaseHolder<AppInfo> getHolder() {
			return new HomeHolder();
		}

		// 此方法在子线程调用
		@Override
		public ArrayList<AppInfo> onLoadMore() {
			// ArrayList<AppInfo> moreData=new ArrayList<AppInfo>();
			// for (int i = 0; i < 10; i++) {
			// moreData.add("测试更多数据:"+i);
			// }
			// SystemClock.sleep(2000);

			HomeProtocol protocol = new HomeProtocol();
			ArrayList<AppInfo> moreData = protocol.getData(getListSize());
			return moreData;
		}

		// @Override
		// public View getView(int position, View convertView, ViewGroup parent) {
		// ViewHolder viewHolder = null;
		// if (convertView == null) {
		// viewHolder = new ViewHolder();
		//
		// convertView = UIUtils.inflate(R.layout.list_item_home);
		// viewHolder.content = (TextView) convertView.findViewById(R.id.tv_content);
		// convertView.setTag(viewHolder);
		// } else {
		// viewHolder = (ViewHolder) convertView.getTag();
		// }
		//
		// String content = getItem(position);
		// viewHolder.content.setText(content);
		// return convertView;
		// }

	}

	static class ViewHolder {
		public TextView content;
	}

}

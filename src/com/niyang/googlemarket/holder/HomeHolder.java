package com.niyang.googlemarket.holder;

import com.niyang.googlemarket.R;
import com.niyang.googlemarket.domain.AppInfo;
import com.niyang.googlemarket.utils.UIUtils;

import android.view.View;
import android.widget.TextView;

/**
 * 首页holder
 * 
 * @author niyang
 *
 */
public class HomeHolder extends BaseHolder<AppInfo> {

	private TextView mTvContent;

	@Override
	public View initView() {
		// 1.加载布局
		View view = UIUtils.inflate(R.layout.list_item_home);
		mTvContent = (TextView) view.findViewById(R.id.tv_content);
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		mTvContent.setText(data.name);
	}

}

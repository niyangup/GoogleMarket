package com.niyang.googlemarket.holder;

import com.niyang.googlemarket.R;
import com.niyang.googlemarket.utils.UIUtils;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoreHolder extends BaseHolder<Integer> {

	// 加载更多的集中状态
	// 1.可以加载更多
	// 2.加载更多失败
	// 3.没有更多数据

	public static final int STATE_MORE_MORE = 1;
	public static final int STATE_MORE_ERROR = 2;
	public static final int STATE_MORE_NONE = 3;
	private TextView mTvLoadError;
	private LinearLayout mLlLoadMore;

	public MoreHolder(boolean hasMore) {
		// 如果有更多数据,状态为STATE_MORE_MORE ,将此状态传递给父类的data,父类会同时刷新
		setData(hasMore ? STATE_MORE_MORE : STATE_MORE_NONE);
	}

	@Override
	public View initView() {
		View view = UIUtils.inflate(R.layout.list_item_more);
		
		mLlLoadMore = (LinearLayout) view.findViewById(R.id.ll_load_more);
		mTvLoadError = (TextView) view.findViewById(R.id.tv_load_error);
		
		return view;
	}

	@Override
	public void refreshView(Integer data) {
		switch (data) {
		case STATE_MORE_MORE:
			// 显示加载更多
			mLlLoadMore.setVisibility(View.VISIBLE);
			mTvLoadError.setVisibility(View.GONE);
			break;

		case STATE_MORE_NONE:
			// 隐藏加载更多
			mLlLoadMore.setVisibility(View.GONE);
			mTvLoadError.setVisibility(View.GONE);
			break;

		case STATE_MORE_ERROR:
			//加载失败
			mLlLoadMore.setVisibility(View.GONE);
			mTvLoadError.setVisibility(View.VISIBLE);
			break;
		}
	}

}

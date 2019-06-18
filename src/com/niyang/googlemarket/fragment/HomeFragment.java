package com.niyang.googlemarket.fragment;

import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.view.View;
import android.widget.TextView;

/**首页
 * @author niyang
 *
 */
public class HomeFragment extends BaseFragment{

	//如果加载成功,就回调此方法,在主线程运行
	@Override
	public View onCreateSuccessView() {
		TextView view = new TextView(UIUtils.getContext());
		view.setText(getClass().getSimpleName());
		return view;
	}

	//运行在子线程,可以直接执行耗时网络操作
	@Override
	public ResultState onLoad() {
		return ResultState.STATE_SUCESS;
	}

}

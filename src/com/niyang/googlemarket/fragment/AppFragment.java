package com.niyang.googlemarket.fragment;

import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.view.View;
import android.widget.TextView;

/**应用fragment
 * @author niyang
 *
 */
public class AppFragment extends BaseFragment{

	@Override
	public View onCreateSuccessView() {
		TextView textView = new TextView(UIUtils.getContext());
		textView.setText(getClass().getSimpleName());
		return textView;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_SUCESS;
	}

}

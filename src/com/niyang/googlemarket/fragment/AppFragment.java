package com.niyang.googlemarket.fragment;

import java.util.ArrayList;

import com.niyang.googlemarket.domain.AppInfo;
import com.niyang.googlemarket.fragment.HomeFragment.HomeAdapter;
import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.MyListView;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.view.View;
import android.widget.TextView;

/**
 * 应用fragment
 * 
 * @author niyang
 *
 */
public class AppFragment extends BaseFragment {
	private ArrayList<AppInfo> data;
	
	@Override
	public View onCreateSuccessView() {
		MyListView view = new MyListView(UIUtils.getContext());
		//TODO
		//view.setAdapter(new HomeAdapter(data));
		return view;
	}

	@Override
	public ResultState onLoad() {
		return ResultState.STATE_SUCESS;
	}
	
	

}

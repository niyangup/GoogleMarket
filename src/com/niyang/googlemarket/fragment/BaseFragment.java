package com.niyang.googlemarket.fragment;

import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.LoadingPage;
import com.niyang.googlemarket.view.LoadingPage.ResultState;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class BaseFragment extends Fragment {
	
	
	private LoadingPage mLoadingPage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		TextView view = new TextView(UIUtils.getContext());
//		view.setText(getClass().getSimpleName());

		mLoadingPage = new LoadingPage(UIUtils.getContext()) {
			
			
			@Override
			public ResultState onLoad() {
				return BaseFragment.this.onLoad();
			}
			
			@Override
			public View onCreateSuccessView() {
				return BaseFragment.this.onCreateSuccessView();
			}
		};
		return mLoadingPage;
	}

	public abstract View onCreateSuccessView();
	
	public abstract ResultState onLoad();
	
	public void loadData() {
		if (mLoadingPage!=null) {
			mLoadingPage.loadData();
		}
	}
		
	
}

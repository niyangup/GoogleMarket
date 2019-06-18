package com.niyang.googlemarket.view;

import com.niyang.googlemarket.R;
import com.niyang.googlemarket.utils.UIUtils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 根据当前状态来显示不同页面的自定义控件 -未加载 -加载中 -加载失败 -数据为空 -加载成功
 * 
 * @author niyang
 *
 */
public abstract class LoadingPage extends FrameLayout {

	private static final int STATE_LOAD_UNDO = 1;// 未加载
	private static final int STATE_LOAD_LOADING = 2;// 记载中
	private static final int STATE_LOAD_ERROR = 3;// 加载失败
	private static final int STATE_LOAD_EMPTY = 4;// 数据为空
	private static final int STATE_LOAD_SUCESS = 5;// 加载成功

	private int mCurrentState = STATE_LOAD_UNDO;// 当前的状态
	private View mLoadingPage;
	private View mErrorPage;
	private View mEmptyPage;
	private View mSuccessPage;

	public LoadingPage(Context context) {
		super(context);
		initView();
	}

	public LoadingPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	private void initView() {
		// 初始化加载中的布局
		if (mLoadingPage == null) {
			mLoadingPage = UIUtils.inflate(R.layout.page_loading);
			addView(mLoadingPage);// 将当前的布局添加给帧布局
		}

		// 初始化加载失败的布局
		if (mErrorPage == null) {
			mErrorPage = UIUtils.inflate(R.layout.page_error);
			addView(mErrorPage);
		}

		// 初始化数据为空的布局
		if (mEmptyPage == null) {
			mEmptyPage = UIUtils.inflate(R.layout.page_empty);
			addView(mEmptyPage);
		}

		showRightPage();

	}

	// 加载合适的布局
	private void showRightPage() {
		mLoadingPage.setVisibility(
				(mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LOADING) ? View.VISIBLE : View.GONE);

		mErrorPage.setVisibility(mCurrentState == STATE_LOAD_ERROR ? View.VISIBLE : View.GONE);

		mEmptyPage.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);

		if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCESS) {
			mSuccessPage = onCreateSuccessView();
			if (mSuccessPage != null) {
				addView(mSuccessPage);
			}
		}
		if (mSuccessPage != null) {
			mSuccessPage.setVisibility(mCurrentState == STATE_LOAD_SUCESS ? View.VISIBLE : View.GONE);
		}
	}
	
	public void loadData() {
		if (mCurrentState!=STATE_LOAD_LOADING) {
			mCurrentState=STATE_LOAD_LOADING;
			
			new Thread() {
				public void run() {
					final ResultState resultState = onLoad();
					
					UIUtils.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							if (resultState!=null) {
								mCurrentState=resultState.getState();
								showRightPage();
							}
						}
					});
				}; 
			}.start();
		}
	}

	public abstract View onCreateSuccessView();
	
	public abstract ResultState onLoad();
	
	public enum ResultState{
		STATE_SUCESS(STATE_LOAD_SUCESS),STATE_EMPTY(STATE_LOAD_EMPTY),STATE_ERROR(STATE_LOAD_ERROR);
		
		private int state;
		
		private ResultState(int state) {
			this.state=state;
		}
		
		public int getState() {
			return state;
		}
		
		public void setState(int state) {
			this.state = state;
		}
	}

}

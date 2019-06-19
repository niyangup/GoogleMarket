package com.niyang.googlemarket.holder;

import android.view.View;

/**getView的封装
 * @author niyang
 *
 * @param <T>
 */
public abstract class BaseHolder<T> {
	private View mRootView;
	private T data;

	//1.加载布局
	//2.初始化控件findviewbyid
	public abstract View initView();
	
	//当new这个对象,就会加载布局,初始化控件,设置tag
	public BaseHolder() {
		mRootView = initView();
		//3.打标记
		mRootView.setTag(this);
	}
	
	//返回item的布局对象
	public View getmRootView() {
		return mRootView;
	}
	
	//设置当前Item的数据
	public void setData(T data) {
		this.data = data;
		refreshView(data);
	}
	
	//获取当前item的数据
	public T getData() {
		return data;
	}
	
	//4.根据数据刷新界面
	public abstract void refreshView(T data);
}

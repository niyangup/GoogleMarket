package com.niyang.googlemarket.adapter;

import java.util.ArrayList;

import com.niyang.googlemarket.holder.BaseHolder;
import com.niyang.googlemarket.holder.MoreHolder;
import com.niyang.googlemarket.utils.UIUtils;

import android.R.integer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

/**
 * 对Adapter的封装
 * 
 * @author niyang
 *
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

	private ArrayList<T> data;

	private static final int TYPE_NORMAL = 0;// 正常布局类型
	private static final int TYPE_MORE = 1;// 加载更多类型

	public MyBaseAdapter(ArrayList<T> data) {
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size() + 1;// 增加加载更多布局
	}

	@Override
	public T getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 返回布局类型个数
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	// 返回当前位置应该展示哪种布局类型
	@Override
	public int getItemViewType(int position) {
		if (position == getCount() - 1) {
			// 最后一个
			return TYPE_MORE;
		} else {
			return getInnerType();
		}
	}

	public int getInnerType() {
		return TYPE_NORMAL;// 默认是普通类型
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BaseHolder holder = null;
		if (convertView == null) {
			// 1.加载布局
			// 2.初始化控件findviewbyid
			// 3.打标记
			// 判断是否加载更多类型
			if (getItemViewType(position) == TYPE_MORE) {
				// 加载更多
				holder = new MoreHolder(hasMore());
			} else {
				holder = getHolder();
			}

		} else {
			holder = (BaseHolder) convertView.getTag();
		}

		// 4.刷新数据
		if (getItemViewType(position) != TYPE_MORE) {
			holder.setData(getItem(position));
		} else {
			// 加载更多类型
			//只有在有更多数据的状态下才加载更多
			MoreHolder moreHolder=(MoreHolder) holder;
			
			if (moreHolder.getData()==MoreHolder.STATE_MORE_MORE) {
				loadMore(moreHolder);
			}
		}

		return holder.getmRootView();
	}

	private boolean isLoadMore = false;// 标记是否加载更多

	private void loadMore(final MoreHolder holder) {
		if (!isLoadMore) {
			isLoadMore = true;
			new Thread() {
				@Override
				public void run() {
					final ArrayList<T> moreData = onLoadMore();
					UIUtils.runOnUiThread(new Runnable() {

						@Override
						public void run() {
							if (moreData != null) {
								// 每一页有20条数据,如果返回的数据小于20,则认为到了最后一页了
								if (moreData.size() < 20) {
									holder.setData(MoreHolder.STATE_MORE_NONE);
									Toast.makeText(UIUtils.getContext(), "没有更多数据了", 1).show();
								} else {
									// 还有更多数据
									holder.setData(MoreHolder.STATE_MORE_MORE);
								}
								
								//将更多数据追加到当前集合中
								data.addAll(moreData);
								//刷新界面
								MyBaseAdapter.this.notifyDataSetChanged();
								
							} else {
								// 加载更多失败
								holder.setData(MoreHolder.STATE_MORE_ERROR);
							}
							
							isLoadMore = false;
						}
					});

				}
			}.start();
		}
	}

	// 加载更多数据,必须由子类实现
	public abstract ArrayList<T> onLoadMore();

	// 返回当前页面的holder对象,必须子类实现
	public abstract BaseHolder<T> getHolder();

	// 子类可以重写此方法来决定是否加载更多
	public boolean hasMore() {
		return true;
	}

}

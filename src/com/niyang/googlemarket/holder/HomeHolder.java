package com.niyang.googlemarket.holder;

import com.lidroid.xutils.BitmapUtils;
import com.niyang.googlemarket.R;
import com.niyang.googlemarket.domain.AppInfo;
import com.niyang.googlemarket.http.HttpHelper;
import com.niyang.googlemarket.utils.BitmapHelper;
import com.niyang.googlemarket.utils.UIUtils;

import android.graphics.BitmapFactory;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 首页holder
 * 
 * @author niyang
 *
 */
public class HomeHolder extends BaseHolder<AppInfo> {

	private TextView mTvName;
	private TextView mTvSize;
	private TextView mTvDes;
	private ImageView mIvIcon;
	private RatingBar mRbStar;
	private BitmapUtils mBitmapUtils;

	@Override
	public View initView() {
		// 1.加载布局
		View view = UIUtils.inflate(R.layout.list_item_home);
		mTvName = (TextView) view.findViewById(R.id.tv_name);
		mTvSize = (TextView) view.findViewById(R.id.tv_size);
		mTvDes = (TextView) view.findViewById(R.id.tv_des);
		mIvIcon = (ImageView) view.findViewById(R.id.iv_icon);
		mRbStar = (RatingBar) view.findViewById(R.id.rb_star);

		mBitmapUtils = BitmapHelper.getBitmapUtils();
		return view;
	}

	@Override
	public void refreshView(AppInfo data) {
		mTvName.setText(data.name);
		mTvSize.setText(Formatter.formatFileSize(UIUtils.getContext(), data.size));
		mTvDes.setText(data.des);
		mRbStar.setRating(data.stars);
		
//		mBitmapUtils.display(mIvIcon, HttpHelper.URL + "image?name=" + data.iconUrl);

		mIvIcon.setImageResource(R.drawable.icon);
	}

}

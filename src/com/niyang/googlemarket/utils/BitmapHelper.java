package com.niyang.googlemarket.utils;

import com.lidroid.xutils.BitmapUtils;

public class BitmapHelper {
	
	private BitmapHelper() {}
	
	private static BitmapUtils mBitmapUtils=null;
	
	//单例模式,懒汉模式
	public static BitmapUtils getBitmapUtils() {
		if (mBitmapUtils==null) {
			synchronized (BitmapHelper.class) {
				if (mBitmapUtils==null) {
					mBitmapUtils=new BitmapUtils(UIUtils.getContext());
				}
			}
		}
		
		return mBitmapUtils;
	}
	
}

package com.niyang.googlemarket.utils;

import com.niyang.googlemarket.global.GooglePlayApplicaction;

import android.R.drawable;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Process;
import android.view.View;

public class UIUtils {

	public static Context getContext() {
		return GooglePlayApplicaction.getContext();
	}

	public static Handler getHandler() {
		return GooglePlayApplicaction.getHandler();
	}

	public static int getMainThreadId() {
		return GooglePlayApplicaction.getMainThreadId();
	}

	/////////////// 加载资源文件////////////////////////

	// 获取xmll里values下string的字符串属性
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	// 获取图片
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	// 获取颜色
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	//根据id获取颜色的状态选择器
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	// 获取尺寸
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);// 返回具体的像素
	}

	/////////////// dp和px转换////////////////////////

	public static int dip2px(float dip) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (dip * density + 0.5f);
	}

	public static float px2dip(float px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	/////////////// 加载布局文件 ////////////////////////

	public static View inflate(int id) {
		return View.inflate(getContext(), id, null);
	}

	/////////////// 判断是否运行在主线程 ////////////////////////

	public static boolean isRunOnUiThread() {
		// 获取当前线程id,如果当前Id与主线程相同,那么当前就是主线程
		int myTid = Process.myTid();
		if (myTid == getMainThreadId()) {
			return true;
		}

		return false;
	}

	public static void runOnUiThread(Runnable r) {
		if (isRunOnUiThread()) {
			// 已经是主线程,直接运行
			r.run();
		} else {
			// 如果是子线程,借助handler的post后,运行在主线程
			getHandler().post(r);
		}
	}

}

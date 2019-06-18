package com.niyang.googlemarket;

import com.niyang.googlemarket.fragment.BaseFragment;
import com.niyang.googlemarket.fragment.FragmentFactory;
import com.niyang.googlemarket.utils.UIUtils;
import com.niyang.googlemarket.view.PagerTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends BaseActivity {

	private PagerTab mPagerTab;
	private ViewPager mViewPager;
	private MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		mAdapter = new MyAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);

		mPagerTab.setViewPager(mViewPager);// 将指针和viewpager绑定在一起

		mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				BaseFragment fragment = FragmentFactory.createFragment(position);
				fragment.loadData();
			}
			
			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int position) {
				
			}
		});
	}

	class MyAdapter extends FragmentPagerAdapter {
		private String[] mTabNames;

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// 加载页面标题数组
			mTabNames = UIUtils.getStringArray(R.array.tab_names);
		}

		// 返回页签标题
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// 返回position位置的fragment对象
		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		// fragment的数量
		@Override
		public int getCount() {
			return mTabNames.length;
		}
		
	}
}

package com.niyang.googlemarket.fragment;

import java.util.HashMap;

/**
 * 生成Fragment的工厂
 * 
 * @author niyang
 *
 */
public class FragmentFactory {
	private static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();

	public static BaseFragment getFragment(int pos) {
		// 先从集合中取,如果没有才创建对象,提高性能
		BaseFragment fragment = mFragmentMap.get(pos);

		if (fragment == null) {

			switch (pos) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new AppFragment();
				break;
			case 2:
				fragment = new GameFragment();
				break;
			case 3:
				fragment = new SubjectFragment();
				break;
			case 4:
				fragment = new RecommendFragment();
				break;
			case 5:
				fragment = new CategoryFragment();
				break;
			case 6:
				fragment = new HotFragment();
				break;
			}

			mFragmentMap.put(pos, fragment);
		}
		return fragment;
	}
}

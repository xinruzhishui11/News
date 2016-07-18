package com.xinruzhishui.news.fragment;

import java.util.HashMap;

/**
 * 生产fragment工厂
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class FragmentFactory {

	private static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();

	public static BaseFragment createFragment(int pos) {
		// 先从集合中取, 如果没有,才创建对象, 提高性能
		BaseFragment fragment = mFragmentMap.get(pos);

		if (fragment == null) {
			switch (pos) {
			case 0:
				fragment = new TopFragment();
				break;
			case 1:
				fragment = new SocialFragment();
				break;
			case 2:
				fragment = new InlandFragment();
				break;
			case 3:
				fragment = new InternationFragment();
				break;
			case 4:
				fragment = new EntertainmentFragment();
				break;
			case 5:
				fragment = new SportFragment();
				break;
			case 6:
				fragment = new MilitaryFragment();
				break;
			case 7:
				fragment = new ScienceFragment();
				break;
			case 8:
				fragment = new FinanceFragment();
				break;
			case 9:
				fragment = new FashionFragment();
				break;

			default:
				break;
			}

			mFragmentMap.put(pos, fragment);// 将fragment保存在集合中
		}

		return fragment;
	}
}

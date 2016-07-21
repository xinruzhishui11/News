package com.xinruzhishui.news.activity;

import java.util.ArrayList;

import com.xinruzhishui.news.ui.LoadingPage.ResultState;

import android.support.v7.app.ActionBarActivity;
/**
 * android-support-v7-appcompat
 * 
 * 支持ActionBar效果, 可以兼容2.x版本, 让2.x也能够展示Actionbar效果
 * 
 * 1. 引入appcompat
 * 2. 解决support-v4冲突问题
 * 3. 继承ActionBarActivity
 * 
 * @author Kevin
 * @date 2015-10-27
 */
public class BaseActivity extends ActionBarActivity {

	
	// 对网络返回数据的合法性进行校验
		public ResultState check(Object obj) {
			if (obj != null) {
				if (obj instanceof ArrayList) {// 判断是否是集合
					ArrayList list = (ArrayList) obj;

					if (list.isEmpty()) {
						return ResultState.STATE_EMPTY;
					} else {
						return ResultState.STATE_SUCCESS;
					}
				}
			}

			return ResultState.STATE_ERROR;
		}
}

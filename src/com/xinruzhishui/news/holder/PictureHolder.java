package com.xinruzhishui.news.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.xinruzhishui.news.R;
import com.xinruzhishui.news.entity.News;
import com.xinruzhishui.news.entity.Picture;
import com.xinruzhishui.news.utils.BitmapHelper;
import com.xinruzhishui.news.utils.UIUtils;

public class PictureHolder extends BaseHolder<Picture> {

	private BitmapUtils mBitmapUtils;
	ViewHolder holder;

	@Override
	public View initView() {
		//初始化化控件
		View view = UIUtils.inflate(R.layout.item_pic);
		
		if(holder == null){
			holder = new ViewHolder();
			holder.ivPic = (ImageView) view.findViewById(R.id.iv_pic);
			view.setTag(holder);
		}
		holder = (ViewHolder) view.getTag();
		
		mBitmapUtils = BitmapHelper.getBitmapUtils();
		mBitmapUtils.configDefaultLoadingImage(R.drawable.ic_default);
		return view;
	}

	@Override
	public void refreshView(Picture picture) {
		//刷新界面
		mBitmapUtils.display(holder.ivPic,picture.getUrl());
	}
	
	class ViewHolder {
		ImageView ivPic;
	}
}

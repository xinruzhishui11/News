package com.xinruzhishui.news;

import java.util.ArrayList;

import com.xinruzhishui.news.activity.BaseActivity;
import com.xinruzhishui.news.adapter.MyBaseAdapter;
import com.xinruzhishui.news.entity.Picture;
import com.xinruzhishui.news.holder.BaseHolder;
import com.xinruzhishui.news.holder.PictureHolder;
import com.xinruzhishui.news.http.protocol.PictureProtocol;
import com.xinruzhishui.news.ui.LoadingPage;
import com.xinruzhishui.news.ui.LoadingPage.ResultState;
import com.xinruzhishui.news.utils.UIUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

public class PictureActivity extends BaseActivity {

	private LoadingPage loadingPage;
	private GridView view;
	private ArrayList<Picture> data;
	private PictureProtocol protocol;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		loadingPage = new LoadingPage(UIUtils.getContext()) {

			@Override
			public ResultState onLoad() {
				return PictureActivity.this.onLoad();
			}

			@Override
			public View onCreateSuccessView() {
				return PictureActivity.this.onCreateSuccessView();
			}
		}; 

		loadData();
		setContentView(loadingPage);
	}

	// 开始加载网络数据
	public void loadData() {
		if (loadingPage != null) {
			loadingPage.loadData();
		}
	}

		protected View onCreateSuccessView() {
			view = new GridView(UIUtils.getContext());
			view.setNumColumns(3);
			view.setAdapter(new PictureAdapter(data));
			return view;
		}

		protected ResultState onLoad() {
			protocol = new PictureProtocol();
			data = protocol.getData(20);
			return check(data);
		}

		class PictureAdapter extends MyBaseAdapter<Picture>{

			public PictureAdapter(ArrayList<Picture> data) {
				super(data);
			}

			@Override
			public BaseHolder<Picture> getHolder(int position) {

				return new PictureHolder();
			}

			@Override
			public ArrayList<Picture> onLoadMore() {
				ArrayList<Picture> moreData = protocol.getData(getListSize());
				return moreData;
			}

		}


	}

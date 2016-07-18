package com.xinruzhishui.news.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.xinruzhishui.news.activity.WebViewActivity;
import com.xinruzhishui.news.adapter.MyBaseAdapter;
import com.xinruzhishui.news.entity.News;
import com.xinruzhishui.news.holder.BaseHolder;
import com.xinruzhishui.news.holder.TopHolder;
import com.xinruzhishui.news.http.protocol.TopProtocol;
import com.xinruzhishui.news.ui.LoadingPage.ResultState;
import com.xinruzhishui.news.utils.UIUtils;

public class TopFragment extends BaseFragment{
	private ListView view;
	private ArrayList<News> mList;

	
	//成功显示的布局
	@Override
	public View onCreateSuccessView() {
		view = new ListView(UIUtils.getContext());
		NewsAdapter newsAdapter = new NewsAdapter(mList);
		view.setAdapter(newsAdapter);
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				News news = mList.get(position);
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("url", news.url);
				startActivity(intent);
			}
		});
		return view;
	}

	//工作在子线程中，加载数据
	@Override
	public ResultState onLoad() {
		TopProtocol protocol = new TopProtocol();
		mList = protocol.getData();
		return check(mList);//数据进行检验
	}
	
	class NewsAdapter extends MyBaseAdapter<News>{

		public NewsAdapter(ArrayList<News> data) {
			super(data);
		}

		@Override
		public BaseHolder<News> getHolder(int position) {
			return new TopHolder();
		}

		@Override
		public ArrayList<News> onLoadMore() {
			return null;
		}
		
		@Override
		public boolean hasMore() {
			return false;//没有更多数据
		}
		
	}
}

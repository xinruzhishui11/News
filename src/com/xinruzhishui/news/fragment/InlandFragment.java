package com.xinruzhishui.news.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.xinruzhishui.news.activity.WebViewActivity;
import com.xinruzhishui.news.adapter.MyBaseAdapter;
import com.xinruzhishui.news.entity.News;
import com.xinruzhishui.news.holder.BaseHolder;
import com.xinruzhishui.news.holder.InlandHolder;
import com.xinruzhishui.news.http.protocol.InlandProtocol;
import com.xinruzhishui.news.ui.LoadingPage.ResultState;
import com.xinruzhishui.news.utils.UIUtils;

public class InlandFragment extends BaseFragment{
	private ArrayList<News> data;

	@Override
	public View onCreateSuccessView() {
		ListView view = new ListView(UIUtils.getContext());
		view.setAdapter(new InlandAdapter(data));
		
		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				News news = data.get(position);
				Intent intent = new Intent(getActivity(),WebViewActivity.class);
				intent.putExtra("url", news.url);
				startActivity(intent);
			}
		});
		
		return view;
	}

	@Override
	public ResultState onLoad() {
		InlandProtocol protocol = new InlandProtocol();
		data = protocol.getData();
		return check(data);
	}
	
	class InlandAdapter extends MyBaseAdapter<News>{

		public InlandAdapter(ArrayList<News> data) {
			super(data);
		}

		@Override
		public BaseHolder<News> getHolder(int position) {
			// TODO Auto-generated method stub
			return new InlandHolder();
		}

		@Override
		public ArrayList<News> onLoadMore() {
			return null;
		}
		
		@Override
		public boolean hasMore() {
			return false;
		}
		
	}

}

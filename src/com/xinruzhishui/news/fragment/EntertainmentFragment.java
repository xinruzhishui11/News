package com.xinruzhishui.news.fragment;

import java.util.ArrayList;

import com.xinruzhishui.news.R;
import com.xinruzhishui.news.activity.WebViewActivity;
import com.xinruzhishui.news.adapter.MyBaseAdapter;
import com.xinruzhishui.news.entity.News;
import com.xinruzhishui.news.fragment.InternationFragment.InternationAdapter;
import com.xinruzhishui.news.holder.BaseHolder;
import com.xinruzhishui.news.holder.InlandHolder;
import com.xinruzhishui.news.http.protocol.EntertainmentProtocol;
import com.xinruzhishui.news.ui.LoadingPage.ResultState;
import com.xinruzhishui.news.utils.UIUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EntertainmentFragment extends BaseFragment{
	private ArrayList<News> data;

	@Override
	public View onCreateSuccessView() {
		ListView view = new ListView(UIUtils.getContext());
		view.setAdapter(new EntertainmentAdapter(data));
		
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
		EntertainmentProtocol protocol = new EntertainmentProtocol();
		data = protocol.getData();
		return check(data);
	}
	
	class EntertainmentAdapter extends MyBaseAdapter<News>{

		public EntertainmentAdapter(ArrayList<News> data) {
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

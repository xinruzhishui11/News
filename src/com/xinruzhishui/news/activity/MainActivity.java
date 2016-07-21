package com.xinruzhishui.news.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinruzhishui.news.PictureActivity;
import com.xinruzhishui.news.R;
import com.xinruzhishui.news.fragment.BaseFragment;
import com.xinruzhishui.news.fragment.FragmentFactory;
import com.xinruzhishui.news.ui.PagerTab;
import com.xinruzhishui.news.utils.UIUtils;


public class MainActivity extends BaseActivity implements OnClickListener{

	private PagerTab mPagerTab;
	private ViewPager mViewPager;
	private MyAdapter mAdapter;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mToggle;
	
	private TextView tvNews;
	private TextView tvPic;
	private ContentFragment contentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		tvNews = (TextView) findViewById(R.id.tv_left_news);
		tvPic = (TextView) findViewById(R.id.tv_left_pic);
		
		tvNews.setOnClickListener(this);
		tvPic.setOnClickListener(this);

		mPagerTab = (PagerTab) findViewById(R.id.pager_tab);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);

		LinearLayout llTab = (LinearLayout) findViewById(R.id.ll_tab);

		//点击的时候不会将点击事件传递ListView中
		llTab.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;//消费当前事件
			}
		});


		mAdapter = new MyAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);

		mPagerTab.setViewPager(mViewPager);// 将指针和viewpager绑定在一起

		mPagerTab.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BaseFragment fragment = FragmentFactory
						.createFragment(position);
				// 开始加载数据
				fragment.loadData();
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
		initActionBar();

	}



	/**
	 * 初始化actionbar
	 */
	private void initActionBar() {
		// 获取actionbar对象
		ActionBar actionBar = getSupportActionBar();
		// 左上角显示logo
		actionBar.setHomeButtonEnabled(false);
		// 左上角显示返回图标, 和侧边栏绑定后显示侧边栏图标
		actionBar.setDisplayHomeAsUpEnabled(true);

		// 初始化侧边栏开关
		mToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer_am, R.string.drawer_open,
				R.string.drawer_close);// 参2:DrawerLayout对象, 参3:侧边栏开关图标,
		// 参4:打开侧边栏文本描述;参5:关闭侧边栏文本描述
		// 调用当前同步方法，才可以将侧拉菜单和按钮的点击操作绑定起来
		mToggle.syncState();
	}

	// ActionBar上的按钮被点击后的回调方法
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:// 左上角logo处被点击
			mToggle.onOptionsItemSelected(item);//侧边栏收起或者关闭
			break;
		}

		return super.onOptionsItemSelected(item);
	}



	/**
	 * FragmentPagerAdapter是PagerAdapter的子类, 如果viewpager的页面是fragment的话,就继承此类
	 */
	class MyAdapter extends FragmentPagerAdapter {

		private String[] mTabNames;

		public MyAdapter(FragmentManager fm) {
			super(fm);
			mTabNames = UIUtils.getStringArray(R.array.tab_names);// 加载页面标题数组
		}

		// 返回页签标题
		@Override
		public CharSequence getPageTitle(int position) {
			return mTabNames[position];
		}

		// 返回当前页面位置的fragment对象
		@Override
		public Fragment getItem(int position) {
			BaseFragment fragment = FragmentFactory.createFragment(position);
			return fragment;
		}

		// fragment数量
		@Override
		public int getCount() {
			return mTabNames.length;
		}

	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_left_news:
			mDrawerLayout.closeDrawers();
			break;
		case R.id.tv_left_pic:
			contentFragment = new ContentFragment(); 
	        Bundle args = new Bundle();  
	        args.putString("text","图片");  
	        contentFragment.setArguments(args);  
	        android.app.FragmentManager fm = getFragmentManager();
	        FragmentTransaction transaction = fm.beginTransaction();
	        transaction.replace(R.id.content_frame, contentFragment);
	        transaction.addToBackStack(null);
	        transaction.commit();
	        mDrawerLayout.closeDrawers();
			break;
		}
	}
	
	
	public class ContentFragment extends android.app.Fragment {  
		  
	    private TextView textView;  
	      
	    @Override  
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
	            Bundle savedInstanceState) {  
	        View view = inflater.inflate(R.layout.fragment_content, container, false);  
	        textView = (TextView) view.findViewById(R.id.textView); 
	        GridView gridView = (GridView) view.findViewById(R.id.gridview);
	        gridView.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					return true;
				}
			});
	        
	        String text = getArguments().getString("text");  
	        textView.setText(text);  
	        return view;  
	    }  
	}  

//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.tv_left_news:
//			Intent intent = new Intent(this, MainActivity.class);
//			startActivity(intent);
//			break;
//		case R.id.tv_left_pic:
//			Intent intent1 = new Intent(this, PictureActivity.class);
//			startActivity(intent1);
//			break;
//
//		}
//		
//	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

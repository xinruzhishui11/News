package com.xinruzhishui.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xinruzhishui.news.R;

public class WebViewActivity extends BaseActivity {
	
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_web_view_activity);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(url);
		
		
	}
	
	@Override
	 public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
	   webView.goBack();// 返回前一个页面
	   return true;
	  }
	  return super.onKeyDown(keyCode, event);
	 }


}
